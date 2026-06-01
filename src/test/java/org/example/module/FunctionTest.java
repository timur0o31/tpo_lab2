package org.example.module;

import org.example.Function;
import org.example.MathFunction;
import org.example.logFunc.Ln;
import org.example.logFunc.Log3;
import org.example.trigFunc.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionTest {
    private static MathFunction mathFunction;
    private static double eps = 1e-9;
    @BeforeAll
    static void setUp() {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Cot cot = new Cot(cos,sin);
        Tan tan = new Tan(cos,sin);
        Csc csc = new Csc(sin);
        Sec sec = new Sec(cos);
        Ln ln = new Ln();
        Log3 log3 = new Log3(ln);
        mathFunction = new Function(cos, sin,tan, cot, sec, csc, ln, log3);
    }
    @Test
    void checkBreakPoints() {
        assertThrows(ArithmeticException.class, () -> mathFunction.calculate(-Math.PI, 1e-9));
        assertThrows(ArithmeticException.class, () -> mathFunction.calculate(-Math.PI / 2, 1e-9));
        assertThrows(ArithmeticException.class, () -> mathFunction.calculate(-3 * Math.PI / 2, 1e-9));
        assertThrows(ArithmeticException.class, () -> mathFunction.calculate(0, 1e-9));
    }
}
