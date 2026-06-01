package org.example.module.cot;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Cot;
import org.example.trigFunc.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CotTest {
    private final Cos cos = new Cos();
    private final Sin sin = new Sin(cos);
    private final Cot cot = new Cot(cos,sin);
    @ParameterizedTest(name = "cot({0}) = {1}")
    @CsvSource({"0.78539, 1",
            "1.04719, 0.57735",
            "1.57079, 0",
            "2.09439, -0.5773",
            "2.35619, -1",
            "3.92699, 1",
            "4.71238, 0"})
    void checkCot(double x, double realCot) {
        if (Double.isInfinite(realCot)) assertThrows(ArithmeticException.class, () -> cot.calculate(x, 1e-9));
        else assertEquals(realCot, cot.calculate(x, 1e-9), 0.0001, "x: " + x);

    }
    @ParameterizedTest(name="cos({0}),k=({1})")
    @CsvFileSource(resources = "/modulePeriod/cotPeriodTest.csv", numLinesToSkip = 1)
    void checkPeriod(double x, int k){
        assertEquals(cot.calculate(x,1e-9), cot.calculate(x+k*Math.PI,1e-9),0.0001, "x:"+x);
    }
    @Test
    void checkCot() {
        Cos cos = new Cos();
        Cot cot = new Cot(cos,sin);
        double x = -2.45506;
        assertEquals(1.2202212869, cot.calculate(x, 1e-9), 1e-4);
    }
    @ParameterizedTest(name = "cos({0}) = {1}")
    @CsvSource({"-0.65676, -1.2971407898",
            "-2.45506, 1.2202212869",
            "-3.88118, -1.0960908641",
            "-5.53298, 1.0729843748"})
    void checkIntegrationPoints(double x, double expected) {
        assertEquals(expected, cot.calculate(x, 1e-9), 0.0001, "x:" + x);
    }
    @Test
    void shouldCalculateZeros() {
        assertAll(()-> assertEquals(0, cot.calculate(Math.PI/2, 1e-9),0.0001),
                ()-> assertEquals(0, cot.calculate(-Math.PI/2, 1e-9),0.0001),
                ()-> assertEquals(0, cot.calculate(3 * Math.PI/2, 1e-9),0.0001)
        );
    }
    @Test
    void shouldThrowWhenSinIsZero() {
        assertAll(()-> assertThrows(ArithmeticException.class, ()-> cot.calculate(0,1e-9)),
                ()-> assertThrows(ArithmeticException.class, ()-> cot.calculate(Math.PI,1e-9)),
                ()-> assertThrows(ArithmeticException.class, ()-> cot.calculate(-Math.PI,1e-9)),
                ()-> assertThrows(ArithmeticException.class, ()-> cot.calculate(2*Math.PI,1e-9))
        );
    }
}
