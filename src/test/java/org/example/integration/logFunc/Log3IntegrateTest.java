package org.example.integration.logFunc;

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

public class Log3IntegrateTest {
    private static MathFunction mathFunction;
    private static double eps = 1e-9;
    private Cos cos;
    private Sin sin;
    private Tan tan;
    private Cot cot;
    private Csc csc;
    private Sec sec;
    @BeforeEach
    void setUp() {
        cos = mock(Cos.class);
        sin = mock(Sin.class);
        tan = mock(Tan.class);
        cot = mock(Cot.class);
        csc = mock(Csc.class);
        sec = mock(Sec.class);
        Ln ln = new Ln();
        Log3 log3 = new Log3(ln);
        mathFunction = new Function(cos, sin,tan, cot, sec, csc, ln, log3);
    }
    @ParameterizedTest(name="x=({0}), y=({1})")
    @CsvFileSource(resources = "/integration/step8.csv", numLinesToSkip = 1)
    void testStage7calculate(double x, double expected){
        double actual = mathFunction.calculate(x, eps);
        assertEquals(expected, actual, 1e-4, "x: " + x);
    }
}
