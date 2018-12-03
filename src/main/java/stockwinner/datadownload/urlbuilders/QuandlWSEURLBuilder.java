package stockwinner.datadownload.urlbuilders;

import stockwinner.datadownload.enums.DataProvider;
import stockwinner.datadownload.enums.Function;

public class QuandlWSEURLBuilder implements URLBuilder {
    private String header = DataProvider.QUANDLWSE.getAddress();
    private String symbol = "";
    private String function = "";
    private String datatype = ".json";
    private String apikey = "&api_key=WtRFB-TPkn_faykUtAzu";

    @Override
    public URLBuilder function(Function function) {
        this.function = "?collapse=" + function.toString().toLowerCase();
        return this;
    }

    @Override
    public URLBuilder symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    @Override
    public URLBuilder datatype(String datatype) {
        if (datatype.equals("csv") || datatype.equals("json")) {
            this.datatype = "." + datatype;
        } else System.out.println("Data type can have only \"json\" or \"csv\" value");
        return this;
    }

    @Override
    public String buildURL() {
        if (symbol.equals("") || function.equals("")) throw new IllegalArgumentException();
        return String.format("%s%s%s%s%s", header, symbol, datatype, function, apikey);
    }
}
