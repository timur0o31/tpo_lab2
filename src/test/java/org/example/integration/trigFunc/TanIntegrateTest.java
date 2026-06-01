package org.example.integration.trigFunc;

import org.example.Function;
import org.example.MathFunction;
import org.example.logFunc.Ln;
import org.example.logFunc.Log3;
import org.example.trigFunc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TanIntegrateTest {
    private static MathFunction mathFunction;
    private static double eps = 1e-9;
    private Csc csc;
    private Sec sec;
    private Ln ln;
    private Log3 log3;
    @BeforeEach
    void setUp() {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Cot cot = new Cot(cos,sin);
        Tan tan = new Tan(cos, sin);
        csc = mock(Csc.class);
        sec = mock(Sec.class);
        ln = mock(Ln.class);
        log3 = mock(Log3.class);
        mathFunction = new Function(cos, sin,tan, cot, sec, csc, ln, log3);
    }
    @ParameterizedTest(name="x=({0}), y=({1})")
    @CsvFileSource(resources = "/integration/step4.csv", numLinesToSkip = 1)
    void testStage4Calculate(double x, double expected,double cscValue, double secValue){
        when(csc.calculate(x, eps)).thenReturn(cscValue);
        when(sec.calculate(x, eps)).thenReturn(secValue);
        double actual = mathFunction.calculate(x, eps);
        assertEquals(expected, actual, 1e-2, "x: " + x);
    }
}
