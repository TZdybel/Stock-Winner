package stockwinner.datadownload;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DownloaderTest {
    private String url = "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=USD&to_symbol=PLN&outputsize=compact&apikey=34Y9RPFG5IS4FCHH&datatype=%s";

    @AfterClass
    public static void tearDownClass() {
        File csv = new File("tempcsv.csv");
        File json = new File("tempjson.json");
        csv.delete();
        json.delete();
    }

    @Test
    public void shouldCreateFile() {
        Downloader.downloadFile("http://google.com", "tempcsv.csv");
        File file = new File("tempcsv.csv");
        assertTrue(file.exists());
    }

    @Test
    public void shouldCreateNotEmptyCSVFile() {
        Downloader.downloadFile(String.format(url, "csv"), "tempcsv.csv");
        try {
            BufferedReader br = new BufferedReader(new FileReader("tempcsv.csv"));
            assertNotNull(br.readLine());
            br.close();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void shouldCreateNotEmptyJSONFile() {
        Downloader.downloadFile(String.format(url, "json"), "tempjson.json");
        try {
            BufferedReader br = new BufferedReader(new FileReader("tempjson.json"));
            assertNotNull(br.readLine());
            br.close();
        } catch (IOException e) {
            fail();
        }
    }
}
