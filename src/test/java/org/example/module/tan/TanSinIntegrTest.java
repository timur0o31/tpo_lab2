package org.example.module.tan;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Sin;
import org.example.trigFunc.Tan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TanSinIntegrTest {
    @Mock
    private Cos cos;
    private Tan tan;
    @BeforeEach
    void setUp() {
        Cos realCosForSin = new Cos();
        Sin sin = new Sin(realCosForSin);
        tan = new Tan(cos, sin);
    }
    @ParameterizedTest(name = "tan({0}) = {2}")
    @CsvSource({"-0.65676, 0.7919745801, -0.7709263388",
            "-2.45506, -0.7734484693, 0.8195234838",
            "-3.88118, -0.7387592421, -0.9123331219",
            "-5.53298, 0.7315489081, 0.9319800208"})
    void checkWithRealSin(double x, double cosValue, double expected) {
        when(cos.calculate(x, 1e-9)).thenReturn(cosValue);
        assertEquals(expected, tan.calculate(x, 1e-9), 1e-4, "x:" + x);
    }
}
