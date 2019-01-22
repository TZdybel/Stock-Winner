package stockwinner.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PartTest {

    static StrategyRule passRule = mock(StrategyRule.class);
    static StrategyRule failRule = mock(StrategyRule.class);

    @BeforeAll
    static void setup(){
        when(passRule.applies(null, 1)).thenReturn(true);
        when(failRule.applies(null, 1)).thenReturn(false);
    }

    @Test
    void orPartTest(){

        StrategyPart sp = new StrategyPart();
        sp.switchOperator();
        assertEquals(StrategyPart.LOGIC.OR, sp.getOperator());

        sp.addRule(failRule);
        sp.setValue(7.0);

        assertEquals(sp.getResult(null, 1), 0.0);

        sp.addRule(passRule);
        assertEquals(sp.getResult(null, 1), 7.0);
    }

    @Test
    void andPartTest(){
        StrategyPart sp = new StrategyPart();

        sp.addRule(passRule);
        sp.addRule(passRule);
        sp.setValue(7.0);

        assertEquals(passRule.applies(null,1), true);

        assertEquals(sp.getResult(null, 1), 7.0);
        sp.addRule(failRule);
        assertEquals(sp.getResult(null, 1), 0.0);
    }
}
