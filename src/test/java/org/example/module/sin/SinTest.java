package org.example.module.sin;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Sin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private final Cos cos = new Cos();
    private final Sin sin = new Sin(cos);
    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvSource({"0, 0",
            "0.5235, 0.5",
            "0.7853, 0.7071",
            "1.0471, 0.866",
            "1.5707, 1.0", "3.1415, 0",
            "4.7123, -1.0", "6.2831, 0"})
    void checkSin(double x, double realSin) {
        assertEquals(realSin, sin.calculate(x, 1e-9), 0.0001,"x:"+x);
    }
    @ParameterizedTest(name = "sin({0}) = Math.sin({0})")
    @ValueSource(doubles = {-5.0, -2.5, -1.0, -0.5, 0.5, 1.0, 2.5, 5.0})
    void checkSinMath(double x) {
        assertEquals(Math.sin(x), sin.calculate(x, 1e-9), 0.0001);
    }
    @ParameterizedTest
    @CsvSource({"-3.1415, 0", "-1.5707, -1.0", "-0.7853, -0.7071"})
    void checkSinNegative(double x, double realSin) {
        assertEquals(realSin, sin.calculate(x, 1e-9), 0.0001);
    }
    @ParameterizedTest(name="cos({0}),k=({1})")
    @CsvFileSource(resources = "/modulePeriod/sinPeriodTest.csv", numLinesToSkip = 1)
    void checkPeriod(double x, int k){
        assertEquals(sin.calculate(x,1e-9), sin.calculate(x+k*2*Math.PI,1e-9),0.0001, "x:"+x);
    }
    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvSource({"-0.65676, -0.6105540635",
            "-2.45506, -0.6338591841",
            "-3.88118, 0.6739830589",
            "-5.53298, 0.6817895984"})
    void checkIntegrationPoints(double x, double expected) {
        assertEquals(expected, sin.calculate(x, 1e-9), 0.0001, "x:" + x);
    }
    @Test
    void checkMax() {
        assertAll(()-> assertEquals(1, sin.calculate(Math.PI/2,1e-9),0.0001),
                ()-> assertEquals(1, sin.calculate(Math.PI/2+2*Math.PI,1e-9),0.0001),
                ()-> assertEquals(1, sin.calculate(Math.PI/2-2*Math.PI,1e-9),0.0001));
    }
    @Test
    void checkMin() {
        assertAll(()-> assertEquals(-1, sin.calculate(-Math.PI/2, 1e-9),0.0001),
                ()-> assertEquals(-1, sin.calculate(3*Math.PI/2, 1e-9),0.0001),
                ()-> assertEquals(-1, sin.calculate(-Math.PI/2-2*Math.PI, 1e-9),0.0001));
    }
    @Test
    void checkInflectionPoints() {
        assertAll(()-> assertEquals(0, sin.calculate(0, 1e-9), 0.0001),
                ()-> assertEquals(0, sin.calculate(Math.PI, 1e-9), 0.0001),
                ()-> assertEquals(0, sin.calculate(-Math.PI, 1e-9), 0.0001),
                ()-> assertEquals(0, sin.calculate(2 * Math.PI, 1e-9), 0.0001)
        );
    }
}
