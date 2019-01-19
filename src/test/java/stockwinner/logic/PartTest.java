package stockwinner.logic;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PartTest {

    StrategyRule passRule = mock(StrategyRule.class);
    StrategyRule failRule = mock(StrategyRule.class);
    List<Double> inputs = Arrays.asList(1.0, 2.0);

    @BeforeClass
    void setup(){
        when(passRule.applies(inputs, 1)).thenReturn(true);
        when(failRule.applies(inputs, 1)).thenReturn(false);
    }

    @Test
    void orPartTest(){

        StrategyPart sp = new StrategyPart();
        sp.switchOperator();
        assertEquals(StrategyPart.LOGIC.OR, sp.getOperator());

        sp.addRule(passRule);
        sp.addRule(failRule);
        sp.setValue(7.0);

        assertEquals(sp.getValue(inputs, 1), 0.0);
    }

    @Test
    void andPartTest(){
        StrategyPart sp = new StrategyPart();

        sp.addRule(passRule);
        sp.addRule(failRule);
        sp.setValue(7.0);

        assertEquals(sp.getValue(inputs, 1), 7.0);
    }
}
