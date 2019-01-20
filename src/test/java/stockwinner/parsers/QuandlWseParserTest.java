package stockwinner.parsers;

import org.junit.jupiter.api.Test;
import stockwinner.parsing.QuandlWseParser;

import static org.junit.jupiter.api.Assertions.*;

class QuandlWseParserTest {


    @Test
    public void shouldReturnJsonFirstOpenValue(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("data/quandl_wse/kursy_dzienne_pekao.json","Open");
        Object key = quandlWseParser.getValues().keySet().iterator().next();
        assertEquals(109.5, quandlWseParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonFirstCloseValue(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("data/quandl_wse/kursy_dzienne_pekao.json","Close");
        Object key = quandlWseParser.getValues().keySet().iterator().next();
        assertEquals(109.8, quandlWseParser.getValues().get(key).doubleValue());
    }


    @Test
    public void shouldReturnJsonFirstDate(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("data/quandl_wse/kursy_dzienne_pekao.json","Close");
        Object key = quandlWseParser.getValues().keySet().iterator().next();
        assertEquals("2018-11-30", key);
    }

    @Test
    public void shouldReturnJsonLastOpenValue(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("data/quandl_wse/kursy_dzienne_pekao.json","Open");
        Object key = null;
        for (Object o : quandlWseParser.getValues().keySet()) {
            key = o;
        }
        assertEquals(0.0, quandlWseParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastCloseValue(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("data/quandl_wse/kursy_dzienne_pekao.json","Close");
        Object key = null;
        for (Object o : quandlWseParser.getValues().keySet()) {
            key = o;
        }
        assertEquals(55.0, quandlWseParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastDate(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("data/quandl_wse/kursy_dzienne_pekao.json","Close");
        Object key = null;
        for (Object o : quandlWseParser.getValues().keySet()) {
            key = o;
        }
        assertEquals("1998-06-30", key);
    }

}
