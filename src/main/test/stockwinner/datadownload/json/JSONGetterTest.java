package stockwinner.datadownload.json;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class JSONGetterTest {
    private JSONGetter getter = new JSONGetter();

    @AfterClass
    public static void tearDownClass() {
        File file = new File("123.json");
        file.delete();
    }

    @Test
    public void shouldCreateFileNamed123() {
        getter.getJsonFromURL("http://google.com", "123.json");
        File f = new File("123.json");
        assertTrue(f.exists());
    }

    @Test
    public void shouldCreateNotEmptyFile() {
        getter.getJsonFromURL("https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_DAILY&symbol=BTC" +
                                "&market=PLN&outputsize=full&apikey=34Y9RPFG5IS4FCHH&datatype=json", "123.json");
        try {
            BufferedReader br = new BufferedReader(new FileReader("123.json"));
            assertNotNull(br.readLine());
            br.close();
        } catch (IOException e) {
            fail();
        }
    }
}
