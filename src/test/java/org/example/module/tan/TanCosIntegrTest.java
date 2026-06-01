package org.example.module.tan;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Sin;
import org.example.trigFunc.Tan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TanCosIntegrTest {
    @Mock
    private Sin sin;
    private Tan tan;
    @BeforeEach
    void setUp() {
        Cos cos = new Cos();
        tan = new Tan(cos, sin);
    }
    @ParameterizedTest(name = "tan({0}) = {2}")
    @CsvSource({"-0.65676, -0.6105540635, -0.7709263388",
            "-2.45506, -0.6338591841, 0.8195234838",
            "-3.88118, 0.6739830589, -0.9123331219",
            "-5.53298, 0.6817895984, 0.9319800208"})
    void checkIntegrationPoints(double x, double sinValue, double expected) {
        when(sin.calculate(x, 1e-9)).thenReturn(sinValue);
        assertEquals(expected, tan.calculate(x, 1e-9), 1e-4, "x:" + x);
    }
}
