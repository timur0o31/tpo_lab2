package org.example.module.sin;

import org.example.trigFunc.Cos;
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
public class SinMockTest {
    private static final double EPS = 1e-9;
    @Mock
    private Cos cos;
    private Sin sin;
    @BeforeEach
    void setUp() {
        sin = new Sin(cos);
    }
    @ParameterizedTest(name = "sin({0}) = {1}")
    @CsvSource({"-0.65676, -0.6105540635",
            "-2.45506, -0.6338591841",
            "-3.88118, 0.6739830589",
            "-5.53298, 0.6817895984"})
    void checkIntegrationPoints(double x, double expected) {
        when(cos.calculate(Math.PI / 2 - x, EPS)).thenReturn(expected);
        assertEquals(expected, sin.calculate(x, EPS), 0.0001, "x:" + x);
    }
}
