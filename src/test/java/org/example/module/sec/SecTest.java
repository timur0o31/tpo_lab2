package org.example.module.sec;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Sec;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class SecTest {
    private final Cos cos = new Cos();
    private final Sec sec = new Sec(cos);

    @ParameterizedTest(name = "sec({0}) = {1}")
    @CsvSource({"-1.0471, 1.9996",
            "-0.7853, 1.4140",
            "-0.5235, 1.1547",
            "0.0, 1.0",
            "0.52359, 1.1547",
            "0.78539, 1.4142",
            "1.04719, 2.0",
            "3.14159, -1.0"})
    void checkSec(double x, double realSec) {
        assertEquals(realSec, sec.calculate(x, 1e-9), 0.0001, "x: " + x);
    }
    @ParameterizedTest(name="cos({0}),k=({1})")
    @CsvFileSource(resources = "/modulePeriod/secPeriodTest.csv", numLinesToSkip = 1)
    void checkPeriod(double x, int k){
        assertEquals(sec.calculate(x,1e-9), sec.calculate(x+k*2*Math.PI,1e-9),0.0001, "x:"+x);
    }
    @ParameterizedTest(name = "sec({0}) = {1}")
    @CsvSource({"-0.65676, 1.2626667889",
            "-2.45506, -1.2929109561",
            "-3.88118, -1.3536438695",
            "-5.53298, 1.3669626035"})
    void checkIntegrationPoints(double x, double expected) {
        assertEquals(expected, sec.calculate(x, 1e-9), 0.0001, "x:" + x);
    }
    @Test
    void shouldCalculateExtremumPoints() {
        assertAll(()-> assertEquals(1, sec.calculate(0, 1e-9), 1e-4),
                ()-> assertEquals(-1, sec.calculate(Math.PI, 1e-9), 1e-4),
                ()-> assertEquals(-1, sec.calculate(-Math.PI, 1e-9), 1e-4),
                ()-> assertEquals(1, sec.calculate(2*Math.PI, 1e-9), 1e-4)
        );
    }
    @Test
    void shouldThrowWhenCosIsZero() {
        assertAll(() -> assertThrows(ArithmeticException.class, () -> sec.calculate(Math.PI/2, 1e-9)),
                () -> assertThrows(ArithmeticException.class, () -> sec.calculate(-Math.PI/2, 1e-9)),
                () -> assertThrows(ArithmeticException.class, () -> sec.calculate(3*Math.PI/2, 1e-9)));
    }
}
