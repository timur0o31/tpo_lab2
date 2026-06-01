package org.example.module.log3;

import org.example.logFunc.Ln;
import org.example.logFunc.Log3;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Log3Test {
    private final Ln ln = new Ln();
    private final Log3 log3 = new Log3(ln);

    @ParameterizedTest(name = "log3({0}) = {1}")
    @CsvSource({"0.1, -2.0959",
            "0.5, -0.6309",
            "0.7854, -0.2198",
            "1, 0",
            "1.0472, 0.0419",
            "1.5708, 0.4110",
            "2, 0.6309",
            "3, 1",
            "3.1416, 1.0419",
            "9, 2",
            "27, 3"})
    void checkLog3(double x, double expected) {
        if (x <= 0) {
            assertThrows(ArithmeticException.class, () -> log3.calculate(x, 1e-9));
        } else {
            assertEquals(expected, log3.calculate(x, 1e-9), 0.0001, "x: " + x);
        }
    }
    @ParameterizedTest(name = "log3({0}) = {1}")
    @CsvSource({"0.1, -2.0959032743",
            "0.5, -0.6309297536",
            "2, 0.6309297536",
            "3, 1",
            "27, 3"})
    void checkIntegrationPoints(double x, double expected) {
        assertEquals(expected, log3.calculate(x, 1e-9), 0.0001, "x:" + x);
    }
}
