package stockwinner.datadownload.urlbuilders;

import org.junit.Before;
import org.junit.Test;
import stockwinner.datadownload.enums.IEXTradingFunction;

import static org.junit.Assert.*;

public class IEXTest {
    URLBuilder builder;

    @Before
    public void setUp() {
        builder = new IEXTradingURLBuilder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        builder.buildURL();
    }

    @Test
    public void shouldReturnProperURL() {
        builder.datatype("csv").function(IEXTradingFunction.MONTHLY).symbol("AAPL");
        assertEquals("https://api.iextrading.com/1.0/stock/AAPL/chart/5y/batch?chartInterval=21&format=csv",
                    builder.buildURL());
    }
}
