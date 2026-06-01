package org.example.module.tan;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Sin;
import org.example.trigFunc.Tan;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TanFullIntegrationTest {
    private Cos cos = new Cos();
    private Sin sin = new Sin(cos);
    private Tan tan = new Tan(cos, sin);

    @ParameterizedTest(name = "tan({0}) = {1}")
    @CsvSource({
            "-0.65676, -0.7709263388",
            "-2.45506, 0.8195234838",
            "-3.88118, -0.9123331219",
            "-5.53298, 0.9319800208"})
    void checkFullIntegration(double x, double expected) {
        assertEquals(expected, tan.calculate(x, 1e-9), 1e-4, "x:" + x);
    }
}