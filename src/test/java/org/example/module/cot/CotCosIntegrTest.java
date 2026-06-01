package org.example.module.cot;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Cot;
import org.example.trigFunc.Sin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CotCosIntegrTest {
    @Mock
    private Sin sin;
    private Cos cos;
    private Cot cot;
    @BeforeEach
    void setUp() {
        cos = new Cos();
        cot = new Cot(cos, sin);
    }
    @ParameterizedTest(name = "cot({0}) = {2}")
    @CsvSource({"-0.65676, -0.6105540635, -1.2971407898",
            "-2.45506, -0.6338591841, 1.2202212869",
            "-3.88118, 0.6739830589, -1.0960908641",
            "-5.53298, 0.6817895984, 1.0729843748"})
    void checkIntegrationPoints(double x, double sinValue, double expected) {
        when(sin.calculate(x, 1e-9)).thenReturn(sinValue);
        assertEquals(expected, cot.calculate(x, 1e-9), 1e-4, "x:" + x);
    }
}
