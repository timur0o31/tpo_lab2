package org.example.module.sec;

import org.example.trigFunc.Cos;
import org.example.trigFunc.Sec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecMockTest {
    private static final double EPS = 1e-9;
    @Mock
    private Cos cos;
    private Sec sec;
    @BeforeEach
    void setUp() {
        sec = new Sec(cos);
    }
    @ParameterizedTest(name = "sec({0}) = {2}")
    @CsvSource({"-0.65676, 0.7919745801, 1.2626667889",
            "-2.45506, -0.7734484693, -1.2929109561",
            "-3.88118, -0.7387592421, -1.3536438695",
            "-5.53298, 0.7315489081, 1.3669626035"})
    void checkIntegrationPoints(double x, double cosValue, double expected) {
        when(cos.calculate(x, EPS)).thenReturn(cosValue);
        assertEquals(expected, sec.calculate(x, EPS), 0.0001, "x:" + x);
    }
}
