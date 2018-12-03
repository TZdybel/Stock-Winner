import java.io.*;
import java.util.*;

public class Parser {

    protected Map<String, Double> values;

    public Parser(){
        this.values = new LinkedHashMap<String, Double>();
    }

    public void showData(){
        for(Map.Entry<String, Double> entry: this.values.entrySet()){
            System.out.println("X: " + entry.getKey()+ " Y: " + entry.getValue());
        }
    }

    protected void parseValues(String filename, String attribute){};

    protected void csvParsing(String filename, int key, int val){};

    public static void main(String[] args) throws FileNotFoundException {

        AlphavantageParser alphavantageParser = new AlphavantageParser();
        alphavantageParser.parseValues("alphavantage\\kursy_dzienne_microsoft.json", "4. close");
        alphavantageParser.showData();

        System.out.println("****************");

        CSVParser csvParser = new CSVParser();
        csvParser.csvParsing("quandl_wse\\kursy_dzienne_pkobp.csv", 0,1);
        csvParser.showData();

        //alphavantageParser.csvParsing("quandl_wse\\kursy_dzienne_pkobp.csv", 0,1);
       // alphavantageParser.showData();


        //QuandlWseParser quandlWseParser = new QuandlWseParser();
        //quandlWseParser.parseValues("quandl_wse\\kursy_tygodniowe_pknorlen.json", "1");
        //quandlWseParser.showData();

        //quandlWseParser.csvParsing("quandl_wse\\kursy_dzienne_pkobp.csv", 0,1);
//        quandlWseParser.showData();

        //IextradingParser iextradingParser = new IextradingParser();
        //iextradingParser.parseValues("iextrading\\kursy_dzienne_apple.json", "close");
        //iextradingParser.showData();

    }
}