package stockwinner.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyTest {

    @Test
    void simpleLossCase(){
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

}
