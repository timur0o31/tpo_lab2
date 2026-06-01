package org.example.module.cot;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Cot;
import org.example.trigFunc.Sin;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CotFullIntegrationTest {
    private Cos cos = new Cos();
    private Sin sin = new Sin(cos);
    private Cot cot = new Cot(cos, sin);

    @ParameterizedTest(name = "cot({0}) = {1}")
    @CsvSource({
            "-0.65676, -1.2971407898",
            "-2.45506, 1.2202212869",
            "-3.88118, -1.0960908641",
            "-5.53298, 1.0729843748"
    })
    void checkFullIntegration(double x, double expected) {
        assertEquals(expected, cot.calculate(x, 1e-9), 1e-4, "x:" + x);
    }
}