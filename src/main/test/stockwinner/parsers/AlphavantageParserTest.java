package stockwinner.parsers;

import org.junit.jupiter.api.Test;
import stockwinner.parsing.AlphavantageParser;

import static org.junit.jupiter.api.Assertions.*;

class AlphavantageParserTest {

    @Test
    public void shouldReturnJsonFirstOpenValue(){
        AlphavantageParser alphavantageParser = new AlphavantageParser();
        alphavantageParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.json","1. open");
        Object key = alphavantageParser.getValues().keySet().iterator().next();
        assertEquals(110.7000,alphavantageParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonFirstCloseValue(){
        AlphavantageParser alphavantageParser = new AlphavantageParser();
        alphavantageParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.json", "4. close");
        Object key = alphavantageParser.getValues().keySet().iterator().next();
        assertEquals(110.8900, alphavantageParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonFirstDate(){
        AlphavantageParser alphavantageParser = new AlphavantageParser();
        alphavantageParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.json","1. open");
        Object key = alphavantageParser.getValues().keySet().iterator().next();
        assertEquals("2018-11-30", key);
    }

    @Test
    public void shouldReturnJsonLastOpenValue(){
        AlphavantageParser alphavantageParser = new AlphavantageParser();
        alphavantageParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.json","1. open");
        Object key = null;
        for (Object o : alphavantageParser.getValues().keySet()) {
            key = o;
        }
        assertEquals(129.6300 ,alphavantageParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastCloseValue(){
        AlphavantageParser alphavantageParser = new AlphavantageParser();
        alphavantageParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.json", "4. close");
        Object key = null;
        for (Object o : alphavantageParser.getValues().keySet()) {
            key = o;
        }
        assertEquals(131.1300,alphavantageParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastDate(){
        AlphavantageParser alphavantageParser = new AlphavantageParser();
        alphavantageParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.json", "1. open");
        Object key = null;
        for (Object o : alphavantageParser.getValues().keySet()) {
            key = o;
        }
        assertEquals("1998-01-02", key);
    }
}