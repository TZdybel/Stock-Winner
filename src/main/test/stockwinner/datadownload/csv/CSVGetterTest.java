package stockwinner.datadownload.csv;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class CSVGetterTest {
    @AfterClass
    public static void tearDownClass() {
        File file = new File("123.csv");
        file.delete();
    }

    @Test
    public void shouldCreateAFileNamed123() {
        CSVGetter.downloadCSV("http://google.com/", "123.csv");
        File file = new File("123.csv");
        assertTrue(file.exists());
    }

    @Test
    public void shouldCreateNotEmptyFile() {
        CSVGetter.downloadCSV("http://google.com/", "123.csv");
        try {
            BufferedReader br = new BufferedReader(new FileReader("123.csv"));
            assertNotNull(br.readLine());
            br.close();
        } catch (IOException e) {
            fail();
        }
    }
}
