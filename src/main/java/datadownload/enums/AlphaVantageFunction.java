package datadownload.enums;

public enum AlphaVantageFunction implements Function {
    //stock
    TIME_SERIES_DAILY,
    TIME_SERIES_WEEKLY,
    TIME_SERIES_MONTHLY,

    //currencies
    FX_DAILY,
    FX_WEEKLY,
    FX_MONTHLY,

    //cryptocurrencies
    DIGITAL_CURRENCY_DAILY,
    DIGITAL_CURRENCY_WEEKLY,
    DIGITAL_CURRENCY_MONTHLY;
}
