import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IextradingParserTest {

    @Test
    public void shouldReturnJsonFirstOpenValue(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("iextrading\\kursy_dzienne_apple.json", "open");
        Object key = iextradingParser.values.keySet().iterator().next();
        assertEquals(73.1596, iextradingParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonFirstCloseValue(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("iextrading\\kursy_dzienne_apple.json", "close");
        Object key = iextradingParser.values.keySet().iterator().next();
        assertEquals(72.272, iextradingParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastOpenValue(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("iextrading\\kursy_dzienne_apple.json", "open");
        Object key = null;
        while(iextradingParser.values.keySet().iterator().hasNext()){
            key = iextradingParser.values.keySet().iterator().next();
        }
        assertEquals(180.29 , iextradingParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastCloseValue(){
        IextradingParser iextradingParser = new IextradingParser();
        iextradingParser.parseValues("iextrading\\kursy_dzienne_apple.json", "close");
        Object key = null;
        while(iextradingParser.values.keySet().iterator().hasNext()){
            key = iextradingParser.values.keySet().iterator().next();
        }
        assertEquals(178.58 , iextradingParser.values.get(key).doubleValue());
    }

}