package org.example.module.tan;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Sin;
import org.example.trigFunc.Tan;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TanMockTest {
    private static final double EPS = 1e-9;
    @Mock
    private Cos cos;
    @Mock
    private Sin sin;
    @InjectMocks
    private Tan tan;
    @ParameterizedTest(name = "tan({0}) = {3}")
    @CsvSource({"-0.65676, -0.6105540635, 0.7919745801, -0.7709263388",
            "-2.45506, -0.6338591841, -0.7734484693, 0.8195234838",
            "-3.88118, 0.6739830589, -0.7387592421, -0.9123331219",
            "-5.53298, 0.6817895984, 0.7315489081, 0.9319800208"})
    void checkIntegrationPoints(double x, double sinValue, double cosValue, double expected) {
        when(sin.calculate(x, EPS)).thenReturn(sinValue);
        when(cos.calculate(x, EPS)).thenReturn(cosValue);
        assertEquals(expected, tan.calculate(x, EPS), 0.0001, "x:" + x);
    }
}
