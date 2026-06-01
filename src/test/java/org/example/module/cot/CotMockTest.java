package org.example.module.cot;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Cot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CotMockTest {
    private static final double EPS = 1e-9;
    @Mock
    private Cos cos;
    private Cot cot;
    @BeforeEach
    void setUp() {
        cot = new Cot(cos);
    }
    @ParameterizedTest(name = "cot({0}) = {3}")
    @CsvSource({"-0.65676, -0.6105540635, 0.7919745801, -1.2971407898",
            "-2.45506, -0.6338591841, -0.7734484693, 1.2202212869",
            "-3.88118, 0.6739830589, -0.7387592421, -1.0960908641",
            "-5.53298, 0.6817895984, 0.7315489081, 1.0729843748"})
    void checkIntegrationPoints(double x, double sinValue, double cosValue, double expected) {
        when(cos.calculate(x, EPS)).thenReturn(cosValue);
        when(cos.calculate(Math.PI / 2 - x, EPS)).thenReturn(sinValue);
        assertEquals(expected, cot.calculate(x, EPS), 0.0001, "x:" + x);
    }
}