package org.example.module.csc;

import org.example.trigFunc.Csc;
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
public class CscMockTest {
    private static final double EPS = 1e-9;
    @Mock
    private Sin sin;
    private Csc csc;
    @BeforeEach
    void setUp() {
        csc = new Csc(sin);
    }
    @ParameterizedTest(name = "csc({0}) = {2}")
    @CsvSource({"-0.65676, -0.6105540635, -1.6378565958",
            "-2.45506, -0.6338591841, -1.5776374707",
            "-3.88118, 0.6739830589, 1.4837166786",
            "-5.53298, 0.6817895984, 1.4667295145"})
    void checkIntegrationPoints(double x, double sinValue, double expected) {
        when(sin.calculate(x, EPS)).thenReturn(sinValue);
        assertEquals(expected, csc.calculate(x, EPS), 0.0001, "x:" + x);
    }
}
