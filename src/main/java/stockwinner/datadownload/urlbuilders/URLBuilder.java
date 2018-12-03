package stockwinner.datadownload.urlbuilders;

import stockwinner.datadownload.enums.Function;

public interface URLBuilder {
    public String buildURL();
    public URLBuilder function(Function function);
    public URLBuilder datatype(String datatype);
    public URLBuilder symbol(String symbol);
    public default URLBuilder toSymbol(String symbol) {
        System.out.println("This builder cannot operate on currency exchanges");
        return this;
    };
}
