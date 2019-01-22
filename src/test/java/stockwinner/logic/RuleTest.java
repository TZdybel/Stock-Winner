package stockwinner.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleTest {

    @Test
    void simpleRuleTest(){
        List<Double> values = Arrays.asList(100.0, 50.0, 100.0, 200.0);

        StrategyRule sr1 = new StrategyRule();
        sr1.setChange(-50.0);
        sr1.setDays(1);

        StrategyRule sr2 = new StrategyRule();
        sr2.setChange(100.0);
        sr2.setDays(3);

        assertFalse(sr1.applies(values,0 ));
        assertTrue(sr1.applies(values,1 ));
        assertFalse(sr1.applies(values,2 ));
        assertFalse(sr1.applies(values,3 ));

        assertFalse(sr2.applies(values,0 ));
        assertFalse(sr2.applies(values,1 ));
        assertFalse(sr2.applies(values,2 ));
        assertTrue(sr2.applies(values,3 ));
    }

    @Test
    void smallIntervalTest(){
        // gdy za mało danych
        List<Double> values = Arrays.asList(100.0, 150.0);

        StrategyRule sr1 = new StrategyRule();
        sr1.setChange(-50.0);
        sr1.setDays(3);

        assertFalse(sr1.applies(values,0));
        assertFalse(sr1.applies(values,1));
        assertFalse(sr1.applies(values,2));
    }

    @Test
    void negativeDaysTest(){
        StrategyRule sr1 = new StrategyRule();
        try {
            sr1.setDays(-4);
            fail();
        } catch (IllegalArgumentException e){}
    }

    @Test
    void inequalityTest(){
        List<Double> values = Arrays.asList(199.0, 100.0, 49.0, 100.0, 199.0);

        StrategyRule sr1 = new StrategyRule();
        sr1.setChange(-50.0);
        sr1.setDays(1);

        StrategyRule sr2 = new StrategyRule();
        sr2.setChange(100.0);
        sr2.setDays(1);

        assertFalse(sr1.applies(values,1));
        assertTrue(sr1.applies(values,2));

        assertTrue(sr2.applies(values,3));
        assertFalse(sr2.applies(values,4));

    }

}
