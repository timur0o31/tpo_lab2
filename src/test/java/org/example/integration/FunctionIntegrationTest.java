package org.example.integration;

import org.example.Function;
import org.example.MathFunction;
import org.example.logFunc.Ln;
import org.example.logFunc.Log3;
import org.example.trigFunc.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionIntegrationTest {
    private static MathFunction mathFunction;
    private static double eps = 1e-9;
    @BeforeAll
    static void setUp() {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Cot cot = new Cot(cos);
        Tan tan = new Tan(cos,sin);
        Csc csc = new Csc(sin);
        Sec sec = new Sec(cos);
        Ln ln = new Ln();
        Log3 log3 = new Log3(ln);
        mathFunction = new Function(cos, sin,tan, cot, sec, csc, ln, log3);
    }
    @ParameterizedTest(name="x=({0}), y=({1})")
    @CsvFileSource(resources = "/integration/funcValueTest.csv", numLinesToSkip = 1)
    void testStage3Calculate(double x, double expected){
        double actual = mathFunction.calculate(x, eps);
        assertEquals(expected, actual, 1e-4, "x: " + x);
    }
}
