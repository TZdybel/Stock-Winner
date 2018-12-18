package stockwinner.parsers;

import org.junit.jupiter.api.Test;
import stockwinner.parsing.IextradingParser;

import static org.junit.jupiter.api.Assertions.*;

class IextradingParserTest {

    @Test
    public void shouldReturnJsonFirstOpenValue(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("data/iextrading/kursy_dzienne_apple.json", "open");
        Object key = iextradingParser.getValues().keySet().iterator().next();
        assertEquals(73.1596, iextradingParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonFirstCloseValue(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("data/iextrading/kursy_dzienne_apple.json", "close");
        Object key = iextradingParser.getValues().keySet().iterator().next();
        assertEquals(72.272, iextradingParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonFirstDate(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("data/iextrading/kursy_dzienne_apple.json", "close");
        Object key = iextradingParser.getValues().keySet().iterator().next();
        assertEquals("2013-12-02", key);
    }

    @Test
    public void shouldReturnJsonLastOpenValue(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("data/iextrading/kursy_dzienne_apple.json", "open");
        Object key = null;
        for (Object o : iextradingParser.getValues().keySet()) {
            key = o;
        }
        assertEquals(180.29 , iextradingParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastCloseValue(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("data/iextrading/kursy_dzienne_apple.json", "close");
        Object key = null;
        for (Object o : iextradingParser.getValues().keySet()) {
            key = o;
        }
        assertEquals(178.58 , iextradingParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastDate(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("data/iextrading/kursy_dzienne_apple.json", "close");
        Object key = null;
        for (Object o : iextradingParser.getValues().keySet()) {
            key = o;
        }
        assertEquals("2018-11-30", key);
    }

}