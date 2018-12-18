package stockwinner.datadownload.urlbuilders;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import stockwinner.datadownload.enums.AlphaVantageFunction;

public class AlphaVantageTest {
    URLBuilder builder;

    @Before
    public void setUp() {
        builder = new AlphaVantageURLBuilder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowError() {
        builder.buildURL();
    }

    @Test
    public void shouldReturnURLWithDefaultValues() {
        builder.function(AlphaVantageFunction.TIME_SERIES_DAILY).symbol("AAPL");
        assertEquals("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=AAPL&outputsize=full&datatype=json&apikey=34Y9RPFG5IS4FCHH",
                    builder.buildURL());
    }

    @Test
    public void shouldReturnURLWithAllValuesSet() {
        builder.function(AlphaVantageFunction.FX_DAILY).symbol("USD").toSymbol("PLN").datatype("csv");
        assertEquals("https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=USD&to_symbol=PLN&outputsize=full&apikey=34Y9RPFG5IS4FCHH&datatype=csv",
                builder.buildURL());
    }
}
