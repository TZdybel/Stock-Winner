package datadownload.enums;

public enum IEXTradingFunction implements Function{
    DAILY("1"),
    WEEKLY("5"),
    MONTHLY("21");

    private String interval;
    IEXTradingFunction(String interval) {
        this.interval = interval;
    }

    public String getInterval() {
        return interval;
    }
}
