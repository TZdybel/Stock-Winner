import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class AlphavantageParser extends Parser {

    public AlphavantageParser(){};

    @Override
    public void parseValues(String filename, String attribute){
        try{
            JsonElement jsonElement = new JsonParser().parse(new FileReader(filename));
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            for(Map.Entry<String, JsonElement> entry : jsonObj.get("Time Series (Daily)").getAsJsonObject().entrySet()){
                super.values.put(entry.getKey(), entry.getValue().getAsJsonObject().get(attribute).getAsDouble());
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }



}
