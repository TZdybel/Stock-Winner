package stockwinner.parsing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class IextradingParser extends Parser {

    public IextradingParser(){};

    @Override
    public void parseValues(String filename, String attribute){
        super.getValues().clear();
        try{
            JsonElement jsonElement = new JsonParser().parse(new FileReader(filename));
            JsonArray jsonArr = jsonElement.getAsJsonArray();
            for(int i=0; i<jsonArr.size(); i++){
                super.getValues().put(jsonArr.get(i).getAsJsonObject().get("date").getAsString(), jsonArr.get(i).getAsJsonObject().get(attribute).getAsDouble());
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void parseAttributes(String filename){
        super.getAttributes().clear();
        try{
            JsonElement jsonElement = new JsonParser().parse(new FileReader(filename));
            JsonArray jsonArr = jsonElement.getAsJsonArray();
            String[] split = jsonArr.get(0).toString().split(",");
            for(int i=1; i<split.length; i++){
                String[] splitinner = split[i].split(":");
                super.getAttributes().add(splitinner[0].substring(1, splitinner[0].length()-1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

