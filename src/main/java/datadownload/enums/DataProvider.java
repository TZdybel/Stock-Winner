package datadownload.enums;

public enum DataProvider {
    ALPHAVANTAGE("https://www.alphavantage.co/query?function="),
    IEXTRADING("https://api.iextrading.com/1.0/stock/"),
    QUANDLWSE("https://www.quandl.com/api/v3/datasets/WSE/");

    private String address;
    DataProvider(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
