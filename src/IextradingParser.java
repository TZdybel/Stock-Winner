import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class IextradingParser extends Parser {

    public IextradingParser(){};

    @Override
    public void parseValues(String filename, String attribute){
        try{
            JsonElement jsonElement = new JsonParser().parse(new FileReader(filename));
            JsonArray jsonArr = jsonElement.getAsJsonArray();
            for(int i=0; i<jsonArr.size(); i++){
                super.values.put(jsonArr.get(i).getAsJsonObject().get("date").getAsString(), jsonArr.get(i).getAsJsonObject().get(attribute).getAsDouble());
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}

