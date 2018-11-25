package datadownload.urlbuilders;

import datadownload.enums.DataProvider;
import datadownload.enums.Function;
import datadownload.enums.IEXTradingFunction;

public class IEXTradingURLBuilder implements URLBuilder {
    private String header = DataProvider.IEXTRADING.getAddress();
    private String symbol = "";
    private String function = "/chart/5y";
    private String interval = "";
    private String datatype = "&format=json";

    @Override
    public IEXTradingURLBuilder symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    @Override
    public IEXTradingURLBuilder function(Function function) {
        this.interval = "/batch?chartInterval=" + ((IEXTradingFunction)function).getInterval();
        return this;
    }

    @Override
    public URLBuilder datatype(String datatype) {
        if (datatype.equals("csv") || datatype.equals("json")) {
            this.datatype = "&format=" + datatype;
        } else System.out.println("Data type can have only \"json\" or \"csv\" value");
        return this;
    }

    @Override
    public String buildURL() {
        if (symbol.equals("") || function.equals("")) throw new IllegalArgumentException();
        return String.format("%s%s%s%s%s", header, symbol, function, interval, datatype);
    }
}
