package stockwinner.datadownload.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JSONGetter {
    private String jsonString;

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public void getJsonFromURL(String urlString, String filepath) {
        URL url = null;
        try {
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);                                               //set URL connection for output use
            connection.setInstanceFollowRedirects(false);                               //set to not follow redirects automatically
            connection.setRequestMethod("GET");                                         //set the URL request method to GET
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");                          //setting properties
            connection.connect();
            InputStream inStream = connection.getInputStream();
            jsonString = streamToString(inStream);
            saveJSONToFile(filepath);
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL");
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println("Connection cannot be set properly");
            System.out.println(e.toString());
        }
    }

    private void saveJSONToFile(String filepath) {
        try (PrintWriter out = new PrintWriter(filepath)) {
            out.println(jsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getJsonString() {
        return jsonString;
    }
}
