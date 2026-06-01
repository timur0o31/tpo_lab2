package org.example.module.log3;

import org.example.logFunc.Ln;
import org.example.logFunc.Log3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Log3MockTest {
    private static final double EPS = 1e-9;
    @Mock
    private Ln ln;
    private Log3 log3;
    @BeforeEach
    void setUp() {
        log3 = new Log3(ln);
    }
    @ParameterizedTest(name = "log3({0}) = {2}")
    @CsvSource({"0.1, -2.3025850930, -2.0959032743",
            "0.5, -0.6931471806, -0.6309297536",
            "2, 0.6931471806, 0.6309297536",
            "3, 1.0986122887, 1.0000000000",
            "10, 2.3025850930, 2.0959032743"})
    void checkIntegrationPoints(double x, double lnValue, double expected) {
        when(ln.calculate(x, EPS)).thenReturn(lnValue);
        when(ln.calculate(3, EPS)).thenReturn(1.0986122887);
        assertEquals(expected, log3.calculate(x, EPS), 0.0001, "x:" + x);
    }
}
