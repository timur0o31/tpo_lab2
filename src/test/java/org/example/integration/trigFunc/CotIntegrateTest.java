package org.example.integration.trigFunc;

import org.example.Function;
import org.example.MathFunction;
import org.example.logFunc.Ln;
import org.example.logFunc.Log3;
import org.example.trigFunc.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CotIntegrateTest {
    private static MathFunction mathFunction;
    private static double eps = 1e-9;
    @Mock
    private Tan tan;
    @Mock
    private Csc csc;
    @Mock
    private Sec sec;
    @Mock
    private Ln ln;
    @Mock
    private Log3 log3;
    @BeforeEach
    void setUp() {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Cot cot = new Cot(cos,sin);
        mathFunction = new Function(cos, sin,tan, cot, sec, csc, ln, log3);
    }
    @ParameterizedTest(name="x=({0}), y=({1})")
    @CsvFileSource(resources = "/integration/step3.csv", numLinesToSkip = 1)
    void testStage3Calculate(double x, double expected, double tanValue,
                             double cscValue, double secValue){
        when(tan.calculate(x, eps)).thenReturn(tanValue);
        when(csc.calculate(x, eps)).thenReturn(cscValue);
        when(sec.calculate(x, eps)).thenReturn(secValue);
        double actual = mathFunction.calculate(x, eps);
        assertEquals(expected, actual, 1e-4, "x: " + x);
    }
}
