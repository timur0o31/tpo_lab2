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
public class CosIntegrateTest {
    private static double eps = 1e-6;
    @Mock
    private Sin sin;
    @Mock
    private Tan tan;
    @Mock
    private Cot cot;
    @Mock
    private Csc csc;
    @Mock
    private Sec sec;
    @Mock
    private Ln ln;
    @Mock
    private Log3 log3;
    private static MathFunction mathFunction;
    @BeforeEach
    void setUp() {
        Cos cos = new Cos();
        mathFunction = new Function(cos, sin,tan, cot, sec, csc, ln, log3);
    }
    @ParameterizedTest(name="x=({0}), y=({1})")
    @CsvFileSource(resources = "/integration/step1.csv", numLinesToSkip = 1)
    void testStage1Calculate(double x,double expected, double sinValue,
                             double tanValue, double cotValue,
                             double cscValue, double secValue){
        when(sin.calculate(x, eps)).thenReturn(sinValue);
        when(tan.calculate(x, eps)).thenReturn(tanValue);
        when(cot.calculate(x, eps)).thenReturn(cotValue);
        when(csc.calculate(x, eps)).thenReturn(cscValue);
        when(sec.calculate(x, eps)).thenReturn(secValue);
        double actual = mathFunction.calculate(x, eps);
        assertEquals(expected, actual, 1e-4, "x: " + x);
    }
}
