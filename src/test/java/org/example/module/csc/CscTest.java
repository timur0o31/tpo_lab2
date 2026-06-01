package org.example.module.csc;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Csc;
import org.example.trigFunc.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CscTest {
    private final Cos cos = new Cos();
    private final Sin sin = new Sin(cos);
    private final Csc csc = new Csc(sin);
    @ParameterizedTest(name = "csc({0}) = {1}")
    @CsvSource({"-1.5707, -1.0",
            "-1.0471, -1.1547",
            "-0.7853, -1.4143",
            "-0.5235, -2.0003",
            "0.52359, 2.0",
            "0.78539, 1.4142",
            "1.04719, 1.1547",
            "1.57079, 1.0",
            "4.71238, -1.0"})
    void checkCsc(double x, double realCsc) {
        if (Double.isInfinite(realCsc)) {
            assertThrows(ArithmeticException.class, () -> csc.calculate(x, 1e-9));
        } else {
            assertEquals(realCsc, csc.calculate(x, 1e-9), 0.0001, "x: " + x);
        }
    }
    @ParameterizedTest(name="cos({0}),k=({1})")
    @CsvFileSource(resources = "/modulePeriod/cscPeriodTest.csv", numLinesToSkip = 1)
    void checkPeriod(double x, int k){
        assertEquals(csc.calculate(x,1e-9), csc.calculate(x+k*2*Math.PI,1e-9),0.0001, "x:"+x);
    }
    @ParameterizedTest(name = "csc({0}) = {1}")
    @CsvSource({"-0.65676, -1.6378565958",
            "-2.45506, -1.5776374707",
            "-3.88118, 1.4837166786",
            "-5.53298, 1.4667295145"})
    void checkIntegrationPoints(double x, double expected) {
        assertEquals(expected, csc.calculate(x, 1e-9), 0.0001, "x:" + x);
    }
    @Test
    void shouldCalculateExtremumPoints() {
        assertAll(
                ()-> assertEquals(1, csc.calculate(Math.PI/2, 1e-9), 1e-4),
                ()-> assertEquals(-1, csc.calculate(-Math.PI/2, 1e-9), 1e-4),
                ()->assertEquals(-1, csc.calculate(3*Math.PI/2, 1e-9), 1e-4)
        );
    }
    @Test
    void shouldThrowWhenSinIsZero() {
        assertAll(() ->assertThrows(ArithmeticException.class, () -> csc.calculate(0, 1e-9)),
                () ->assertThrows(ArithmeticException.class, () -> csc.calculate(Math.PI, 1e-9)),
                () ->assertThrows(ArithmeticException.class, () -> csc.calculate(-Math.PI, 1e-9)),
                () ->assertThrows(ArithmeticException.class, () -> csc.calculate(2*Math.PI, 1e-9))
        );
    }
}
