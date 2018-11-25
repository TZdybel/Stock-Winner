package datadownload.csv;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CSVGetter {
    public static void downloadCSV(String url, String filepath) {
        InputStream input = null;
        try {
            input = new URL(url).openStream();
            File file = new File(filepath);
            FileUtils.copyInputStreamToFile(input, file);
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL");
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println("Something went wrong with connection");
            System.out.println(e.toString());
        }
    }
}
