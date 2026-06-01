package org.example.module.cot;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Cot;
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
public class CotSinIntegrTest {
    @Mock
    private Cos cos;
    private Cot cot;
    @BeforeEach
    void setUp() {
        Cos realCosForSin = new Cos();
        Sin sin = new Sin(realCosForSin);
        cot = new Cot(cos, sin);
    }
    @ParameterizedTest(name = "cot({0}) = {2}")
    @CsvSource({"-0.65676, 0.7919745801, -1.2971407898",
            "-2.45506, -0.7734484693, 1.2202212869",
            "-3.88118, -0.7387467432, -1.0960908641",
            "-5.53298,  0.7315489081, 1.0729843748"})
    void checkWithRealSin(double x, double cosValue, double expected) {
        when(cos.calculate(x, 1e-9)).thenReturn(cosValue);
        assertEquals(expected, cot.calculate(x, 1e-9), 1e-4, "x:" + x);
    }
}
