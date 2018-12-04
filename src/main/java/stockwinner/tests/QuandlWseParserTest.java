import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuandlWseParserTest {


    @Test
    public void shouldReturnJsonFirstOpenValue(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("quandl_wse\\kursy_dzienne_pekao.json","1");
        Object key = quandlWseParser.values.keySet().iterator().next();
        assertEquals(109.5, quandlWseParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonFirstCloseValue(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("quandl_wse\\kursy_dzienne_pekao.json","4");
        Object key = quandlWseParser.values.keySet().iterator().next();
        assertEquals(109.8, quandlWseParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastOpenValue(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("quandl_wse\\kursy_dzienne_pekao.json","1");
        Object key = null;
        while(quandlWseParser.values.keySet().iterator().hasNext()){
            key = quandlWseParser.values.keySet().iterator().next();
        }
        assertEquals(0.0, quandlWseParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnJsonLastCloseValue(){
        QuandlWseParser quandlWseParser = new QuandlWseParser();
        quandlWseParser.parseValues("quandl_wse\\kursy_dzienne_pekao.json","4");
        Object key = null;
        while(quandlWseParser.values.keySet().iterator().hasNext()){
            key = quandlWseParser.values.keySet().iterator().next();
        }
        assertEquals(55.0, quandlWseParser.values.get(key).doubleValue());
    }

}