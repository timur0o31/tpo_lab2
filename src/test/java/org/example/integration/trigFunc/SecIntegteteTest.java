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
public class SecIntegteteTest {
    private static MathFunction mathFunction;
    private static double eps = 1e-9;
    @Mock
    private Csc csc;
    @Mock
    private Ln ln;
    @Mock
    private Log3 log3;
    @BeforeEach
    void setUp() {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Cot cot = new Cot(cos,sin);
        Tan tan = new Tan(cos,sin);
        Sec sec = new Sec(cos);
        mathFunction = new Function(cos, sin,tan, cot, sec, csc, ln, log3);
    }
    @ParameterizedTest(name="x=({0}), y=({1})")
    @CsvFileSource(resources = "/integration/step5.csv", numLinesToSkip = 1)
    void testStage5Calculate(double x, double expected, double cscValue){
        when(csc.calculate(x, eps)).thenReturn(cscValue);
        double actual = mathFunction.calculate(x, eps);
        assertEquals(expected, actual, 1e-4, "x: " + x);
    }
}
