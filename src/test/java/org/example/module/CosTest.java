package org.example.module;

import org.example.trigFunc.Cos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CosTest {
    private final Cos cos = new Cos();
    @ParameterizedTest(name = "cos({0})")
    @CsvFileSource(resources="/cosTest.csv",numLinesToSkip = 1)
    void checkCos(double x, double expected) {
        assertEquals(expected, cos.calculate(x, 1e-9), 0.0001,"x:"+x);
    }
    @ParameterizedTest(name="cos({0})")
    @CsvFileSource(resources="/cosBigValueTest.csv", numLinesToSkip = 1)
    void checkBigValue(double x) {
        assertEquals(Math.cos(x), cos.calculate(x, 1e-9), 0.0001,"x:"+x);
    }
    @ParameterizedTest(name="cos({0}),k=({1})")
    @CsvFileSource(resources = "/modulePeriod/cosPeriodTest.csv", numLinesToSkip = 1)
    void checkPeriod(double x, int k){
        assertEquals(cos.calculate(x,1e-9), cos.calculate(x+k*2*Math.PI,1e-9),0.0001, "x:"+x);
    }
    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void shouldThrow(double x) {
        assertThrows(IllegalArgumentException.class, () -> {cos.calculate(x, 1e-9);});
    }
    @ParameterizedTest(name = "cos({0}) = {1}")
    @CsvSource({"-0.65676, 0.7919745801",
            "-2.45506, -0.7734484693",
            "-3.88118, -0.7387592421",
            "-5.53298, 0.7315489081"})
    void checkIntegrationPoints(double x, double expected) {
        assertEquals(expected, cos.calculate(x, 1e-4), 0.0001, "x:" + x);
    }
    @Test
    void checkMax(){
        assertAll(()->assertEquals(1, cos.calculate(0, 1e-9), 0.0001),
                ()-> assertEquals(1, cos.calculate(2*Math.PI, 1e-9), 0.0001),
                ()-> assertEquals(1, cos.calculate(-2*Math.PI, 1e-9), 0.0001)
        );
    }
    @Test
    void checkMin(){
        assertEquals(-1, cos.calculate(Math.PI, 1e-9), 0.0001);
        assertEquals(-1, cos.calculate(-Math.PI, 1e-9), 0.0001);
    }
    @Test
    void checkInflectionPoints(){
        assertEquals(0, cos.calculate(Math.PI/2, 1e-9), 0.0001);
        assertEquals(0, cos.calculate(-Math.PI/2, 1e-9), 0.0001);
        assertEquals(0, cos.calculate(3*Math.PI/2, 1e-9), 0.0001);
    }

}
