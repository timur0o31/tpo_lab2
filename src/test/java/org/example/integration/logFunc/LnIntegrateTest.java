package org.example.integration.logFunc;

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
public class LnIntegrateTest {
    private static double eps = 1e-9;
    private static MathFunction mathFunction;
    @Mock
    private Cos cos;
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
    private Log3 log3;

    @BeforeEach
    void setUp() {
        Ln ln = new Ln();
        mathFunction = new Function(cos, sin,tan, cot, sec, csc, ln, log3);
    }
    @ParameterizedTest(name="x=({0}), y=({1})")
    @CsvFileSource(resources = "/integration/step7.csv", numLinesToSkip = 1)
    void testStage7Calculate(double x, double expected, double log3Value){
        when(log3.calculate(x, eps)).thenReturn(log3Value);
        double actual = mathFunction.calculate(x, eps);
        assertEquals(expected, actual, 1e-4, "x: " + x);
    }
}
