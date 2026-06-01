package org.example.module.tan;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Sin;
import org.example.trigFunc.Tan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TanTest {
    private final Cos cos = new Cos();
    private final Sin sin = new Sin(cos);
    private final Tan tan = new Tan(cos,sin);
    @ParameterizedTest(name = "tan({0}) = {1}")
    @CsvSource({"0, 0",
            "0.7853, 0.9998",
            "1.04719, 1.7320",
            "1.5707963267, Infinity",
            "2.09439, -1.73205",
            "2.35619, -1",
            "3.14159, 0",
            "3.92699, 1",
            "6.28318, 0"})
    void checkTan(double x, double realTan) {
        if (Double.isInfinite(realTan)) {
            assertThrows(ArithmeticException.class, () -> tan.calculate(x, 1e-9));
        } else {
            assertEquals(realTan, tan.calculate(x, 1e-9), 0.0001, "x: " + x);
        }
    }
    @ParameterizedTest(name="cos({0}),k=({1})")
    @CsvFileSource(resources = "/modulePeriod/tanPeriodTest.csv", numLinesToSkip = 1)
    void checkPeriod(double x, int k){
        assertEquals(tan.calculate(x,1e-9), tan.calculate(x+k*Math.PI,1e-9),0.0001, "x:"+x);
    }
    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvSource({"-0.65676, -0.7709263388",
            "-2.45506, 0.8195234838",
            "-3.88118, -0.9123331219",
            "-5.53298, 0.9319800208"})
    void checkIntegrationPoints(double x, double expected) {
        assertEquals(expected, tan.calculate(x,1e-9), 0.0001,"x:" + x);
    }
    @Test
    void shouldCalculateZeros() {
        assertAll(()->assertEquals(0, tan.calculate(0,1e-9),0.0001),
                ()->assertEquals(0, tan.calculate(Math.PI,1e-9),0.0001),
                ()->assertEquals(0, tan.calculate(-Math.PI,1e-9),0.0001),
                ()->assertEquals(0, tan.calculate(2*Math.PI,1e-9),0.0001)
        );
    }
    @Test
    void checkDiscontinuityPoints() {
        assertAll(()->assertThrows(ArithmeticException.class,()->tan.calculate(Math.PI/2,1e-9)),
                ()->assertThrows(ArithmeticException.class,()->tan.calculate(-Math.PI/2,1e-9)),
                ()->assertThrows(ArithmeticException.class,()->tan.calculate(3*Math.PI/2,1e-9))
        );
    }
}
