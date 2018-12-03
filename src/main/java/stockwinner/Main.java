package stockwinner;

import stockwinner.datadownload.enums.AlphaVantageFunction;
import stockwinner.datadownload.json.JSONGetter;
import stockwinner.datadownload.urlbuilders.AlphaVantageURLBuilder;
import stockwinner.datadownload.urlbuilders.URLBuilder;

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
