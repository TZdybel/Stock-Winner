package stockwinner.datadownload.urlbuilders;

import org.junit.Before;
import org.junit.Test;
import stockwinner.datadownload.enums.QuandlWSEFunction;

import static org.junit.Assert.*;

public class QuandlWSETest {
    URLBuilder builder;

    @Before
    public void setUp() {
        builder = new QuandlWSEURLBuilder();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        builder.buildURL();
    }

    @Test
    public void shouldReturnProperURL() {
        builder.datatype("csv").function(QuandlWSEFunction.MONTHLY).symbol("PKOBP");
        assertEquals("https://www.quandl.com/api/v3/datasets/WSE/PKOBP.csv?collapse=monthly&api_key=WtRFB-TPkn_faykUtAzu",
                builder.buildURL());
    }
}