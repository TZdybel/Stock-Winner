package stockwinner.parsing;

import com.google.gson.*;

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
                super.getValues().put(jsonTmpArray.get(0).getAsString(), jsonTmpArray.get(super.getAttributes().indexOf(attribute)).getAsDouble());
                //System.out.println(jsonObj.get("dataset").getAsJsonObject().get("data").getAsJsonArray().get(i).getAsJsonArray().get(0));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseAttributes(String filename){
        try{
            JsonElement jsonElement = new JsonParser().parse(new FileReader(filename));
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            String[] split = jsonObj.get("dataset").getAsJsonObject().get("column_names").toString().replaceAll("[\"\\[\\]]", "").split(",");
            for(int i=0; i<split.length; i++){
                super.getAttributes().add(split[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


}
