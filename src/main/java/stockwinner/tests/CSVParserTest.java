import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVParserTest {

    @Test
    public void shouldReturnCSVFirstOpenValue(){
        CSVParser csvParser = new CSVParser();
        csvParser.csvParsing("alphavantage\\kursy_dzienne_microsoft.csv", 0,1);
        Object key = csvParser.values.keySet().iterator().next();
        assertEquals(110.7000, csvParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnCSVFirstCloseValue(){
        CSVParser csvParser = new CSVParser();
        csvParser.csvParsing("alphavantage\\kursy_dzienne_microsoft.csv", 0,4);
        Object key = csvParser.values.keySet().iterator().next();
        assertEquals(110.8900, csvParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnCSVFirstDate(){
        CSVParser csvParser = new CSVParser();
        csvParser.csvParsing("alphavantage\\kursy_dzienne_microsoft.csv", 0,4);
        Object key = csvParser.values.keySet().iterator().next();
        assertEquals("2018-11-30", key);
    }

    @Test
    public void shouldReturnCSVLastOpenValue(){
        CSVParser csvParser = new CSVParser();
        csvParser.csvParsing("alphavantage\\kursy_dzienne_microsoft.csv", 0,1);
        Object key = null;
        while(csvParser.values.keySet().iterator().hasNext()){
            key = csvParser.values.keySet().iterator().next();
            System.out.println("DA");
        }
        assertEquals(129.6300,csvParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnCSVLastCloseValue(){
        CSVParser csvParser = new CSVParser();
        csvParser.csvParsing("alphavantage\\kursy_dzienne_microsoft.csv", 0,4);
        Object key = null;
        while(csvParser.values.keySet().iterator().hasNext()){
            key = csvParser.values.keySet().iterator().next();
        }
        assertEquals(131.1300,csvParser.values.get(key).doubleValue());
    }

    @Test
    public void shouldReturnCSVLastDate(){
        CSVParser csvParser = new CSVParser();
        csvParser.csvParsing("alphavantage\\kursy_dzienne_microsoft.csv", 0,4);
        Object key = null;
        while(csvParser.values.keySet().iterator().hasNext()){
            key = csvParser.values.keySet().iterator().next();
        }
        assertEquals("1998-01-02", key);
    }

}