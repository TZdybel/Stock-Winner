package stockwinner.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StrategyTest {

    static StrategyPart alwaysBuy = mock(StrategyPart.class);

    @BeforeAll
    static void setup(){
        when(alwaysBuy.getResult(anyListOf(Double.class), anyInt())).thenReturn(100.0);
    }

    @Test
    void simpleLossTest(){
        StrategyRule sr1 = new StrategyRule();
        sr1.setChange(10);
        sr1.setDays(1);

        StrategyPart sp1 = new StrategyPart();
        sp1.addRule(sr1);
        sp1.setValue(100);

        Strategy s = new Strategy();
        s.addPart(sp1);

        List<Double> values = Arrays.asList(1.0, 1.1, 0.55);
        List<Double> results = Arrays.asList(100.0, 100.0, 50.0);
        assertEquals(s.getResults(values, 100), results);
    }

    @Test
    void alwaysBuyingTest(){
        Strategy s = new Strategy();
        s.addPart(alwaysBuy);

        List<Double> values =  Arrays.asList(1.0, 0.5, 2.0, 4.0, 32.0);
        List<Double> results = Arrays.asList(1.0, 1.0, 4.0, 8.0, 64.0);
        assertEquals(s.getResults(values, 1), results);
    }

}
