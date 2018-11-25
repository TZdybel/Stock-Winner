package datadownload.urlbuilders;

import datadownload.enums.AlphaVantageFunction;
import datadownload.enums.DataProvider;
import datadownload.enums.Function;

public class AlphaVantageURLBuilder implements URLBuilder {
    private String header = DataProvider.ALPHAVANTAGE.getAddress();
    private String function = "";
    private String symbol = "";
    private String toSymbol = "";
    private String outputsize = "&outputsize=full";
    private String datatype = "&datatype=json";
    private String apikey = "&apikey=34Y9RPFG5IS4FCHH";

    @Override
    public AlphaVantageURLBuilder function(Function function) {
        this.function = function.toString();
        return this;
    }

    @Override
    public AlphaVantageURLBuilder symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    @Override
    public AlphaVantageURLBuilder toSymbol(String symbol) {
        this.toSymbol = symbol;
        return this;
    }

    @Override
    public AlphaVantageURLBuilder datatype(String datatype) {
        if (datatype.equals("csv") || datatype.equals("json")) {
            this.datatype = "&datatype=" + datatype;
        } else System.out.println("Data type can have only \"json\" or \"csv\" value");
        return this;
    }

    @Override
    public String buildURL() {
        if (function.equals("") || symbol.equals("")) throw new IllegalArgumentException();
        if (function.equals(AlphaVantageFunction.TIME_SERIES_DAILY.toString()) ||
                function.equals(AlphaVantageFunction.TIME_SERIES_MONTHLY.toString()) ||
                function.equals(AlphaVantageFunction.TIME_SERIES_WEEKLY.toString()))
            return String.format("%s%s&symbol=%s%s%s%s", header, function, symbol, outputsize, datatype, apikey);
        else if (function.equals(AlphaVantageFunction.FX_DAILY.toString()) ||
                function.equals(AlphaVantageFunction.FX_MONTHLY.toString()) ||
                function.equals(AlphaVantageFunction.FX_WEEKLY.toString()))
            return String.format("%s%s&from_symbol=%s&to_symbol=%s%s%s%s", header, function, symbol, toSymbol, outputsize, apikey, datatype);
        else return String.format("%s%s&symbol=%s&market=%s%s%s%s", header, function, symbol, toSymbol, outputsize, apikey, datatype);
    }
}
