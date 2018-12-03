package stockwinner.parsing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class QuandlWseParser extends Parser {

    //private Map<String, Double> values;

    public QuandlWseParser() {
      //  this.values = new LinkedHashMap<String, Double>();
    }

    @Override
    public void parseValues(String filename, String attribute) {
        try {
            JsonElement jsonElement = new JsonParser().parse(new FileReader(filename));
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            int len = jsonObj.get("dataset").getAsJsonObject().get("data").getAsJsonArray().size();
            for (int i = 0; i < len; i++) {
                JsonArray jsonTmpArray = jsonObj.get("dataset").getAsJsonObject().get("data").getAsJsonArray().get(i).getAsJsonArray();
                super.values.put(jsonTmpArray.get(0).getAsString(), jsonTmpArray.get(Integer.parseInt(attribute)).getAsDouble());
                //System.out.println(jsonObj.get("dataset").getAsJsonObject().get("data").getAsJsonArray().get(i).getAsJsonArray().get(0));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
