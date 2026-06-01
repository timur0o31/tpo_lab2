package org.example.module;

import org.example.logFunc.Ln;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LnTest {
    private final Ln ln = new Ln();
    @ParameterizedTest(name = "ln({0}) = {1}")
    @CsvSource({
            "-1 , 7",
            "0.1, -2.3025",
            "0.5, -0.6931",
            "0.7854, -0.2416",
            "1, 0",
            "1.0472, 0.0461",
            "1.5708, 0.4516",
            "2, 0.6931",
            "2.71828, 1",
            "3, 1.0986",
            "3.1416, 1.1447"})
    void checkLn(double x, double realLn) {
        if(x<=0){
            assertThrows(IllegalArgumentException.class, () -> ln.calculate(x, 1e-9));
        } else {
            assertEquals(realLn, ln.calculate(x, 1e-9), 0.0001, "x: " + x);
        }
    }
    @ParameterizedTest(name = "ln({0}) = {1}")
    @CsvSource({"0.1, -2.3025850930",
            "0.5, -0.6931471806",
            "2, 0.6931471806",
            "3, 1.0986122887",
            "27, 3.295836866"})
    void checkIntegrationPoints(double x, double expected) {
        assertEquals(expected, ln.calculate(x, 1e-9), 0.0001, "x:" + x);
    }
}

