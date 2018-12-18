package stockwinner.parsers;

import org.junit.jupiter.api.Test;
import stockwinner.parsing.CSVParser;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CSVParserTest {

    @Test
    public void shouldReturnCSVFirstOpenValue(){
        CSVParser csvParser = new CSVParser();
        csvParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.csv", 1);
        Object key = csvParser.getValues().keySet().iterator().next();
        assertEquals(110.7000, csvParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnCSVFirstCloseValue(){
        CSVParser csvParser = new CSVParser();
        csvParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.csv", 4);
        Object key = csvParser.getValues().keySet().iterator().next();
        assertEquals(110.8900, csvParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnCSVFirstDate(){
        CSVParser csvParser = new CSVParser();
        csvParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.csv", 4);
        Object key = csvParser.getValues().keySet().iterator().next();
        assertEquals("2018-11-30", key);
    }

    @Test
    public void shouldReturnCSVLastOpenValue(){
        CSVParser csvParser = new CSVParser();
        csvParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.csv", 1);
        Object key = null;
        for (Object o : csvParser.getValues().keySet()) {
            key = o;
        }
        assertEquals(129.6300,csvParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnCSVLastCloseValue(){
        CSVParser csvParser = new CSVParser();
        csvParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.csv", 4);
        Object key = null;
        for (Object o : csvParser.getValues().keySet()) {
            key = o;
        }
        assertEquals(131.1300,csvParser.getValues().get(key).doubleValue());
    }

    @Test
    public void shouldReturnCSVLastDate(){
        CSVParser csvParser = new CSVParser();
        csvParser.parseValues("data/alphavantage/kursy_dzienne_microsoft.csv", 4);
        Object key = null;
        for (Object o : csvParser.getValues().keySet()) {
            key = o;
        }
        assertEquals("1998-01-02", key);
    }

}
