import datadownload.csv.CSVGetter;
import datadownload.enums.AlphaVantageFunction;
import datadownload.json.JSONGetter;
import datadownload.urlbuilders.AlphaVantageURLBuilder;
import datadownload.urlbuilders.URLBuilder;

public class Main {
    public static void main(String[] args) {
        URLBuilder builder = new AlphaVantageURLBuilder();
        builder.function(AlphaVantageFunction.DIGITAL_CURRENCY_DAILY).symbol("BTC").toSymbol("PLN").datatype("json");
        String result = builder.buildURL();
        System.out.println(result);
//        CSVGetter.downloadCSV(result, "csvcsv.csv");
        JSONGetter jsonGetter = new JSONGetter();
        jsonGetter.getJsonFromURL(result, "123.json");
    }
}
