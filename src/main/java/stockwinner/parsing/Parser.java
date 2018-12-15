package stockwinner.parsing;

import java.io.*;
import java.util.*;

public class Parser {

    private Map<String, Double> values;
    private List<String> attributes;

    public Parser(){

        this.values = new LinkedHashMap<String, Double>();
        this.attributes = new List<String>();

    }

    public void showData(){
        for(Map.Entry<String, Double> entry: this.values.entrySet()){
            System.out.println("X: " + entry.getKey()+ " Y: " + entry.getValue());
        }
    }

    public Map<String, Double> getValues(){
        return values;
    }

    public List<String> getAttributes() { return attributes; }

    public void parseValues(String filename, String attribute){};

    public void parseAttributes(String filename){};

    protected void csvParsing(String filename, int key, int val){};

    public static void main(String[] args) throws FileNotFoundException {

//        AlphavantageParser alphavantageParser = new AlphavantageParser();
//        //alphavantageParser.parseAttributes("data\\\\alphavantage\\\\kursy_dzienne_microsoft.json");
//        alphavantageParser.parseValues("data\\alphavantage\\dzienne_usd_na_pln.json", "4. close");
//        alphavantageParser.showData();
////
//        System.out.println("****************");
//
//        CSVParser csvParser = new CSVParser();
//        csvParser.csvParsing("data\\quandl_wse\\kursy_dzienne_pkobp.csv", 0,1);
//        csvParser.showData();
//
//        //alphavantageParser.csvParsing("quandl_wse\\kursy_dzienne_pkobp.csv", 0,1);
//       // alphavantageParser.showData();
//
//        //QuandlWseParser quandlWseParser = new QuandlWseParser();
//        //quandlWseParser.parseValues("quandl_wse\\kursy_tygodniowe_pknorlen.json", "1");
//        //quandlWseParser.showData();
//
//        //quandlWseParser.csvParsing("quandl_wse\\kursy_dzienne_pkobp.csv", 0,1);
////        quandlWseParser.showData();
//
            IextradingParser iextradingParser = new IextradingParser();
            //iextradingParser.parseValues("iextrading\\kursy_dzienne_apple.json", "close");
            iextradingParser.parseAttributes("data\\iextrading\\kursy_dzienne_apple.json");
// iextradingParser.showData();

    }
}
