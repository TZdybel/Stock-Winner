package stockwinner.datadownload;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    public static boolean downloadFile(String url, String filepath) {
        InputStream input = null;
        try {
            input = new URL(url).openStream();
            File file = new File(filepath);
            FileUtils.copyInputStreamToFile(input, file);
            return true;
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL");
            System.out.println(e.toString());
            return false;
        } catch (IOException e) {
            System.out.println("Something went wrong with connection");
            System.out.println(e.toString());
            return false;
        }
    }
}
