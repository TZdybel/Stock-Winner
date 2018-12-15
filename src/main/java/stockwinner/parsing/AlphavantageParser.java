package stockwinner.parsing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AlphavantageParser extends Parser {

    public AlphavantageParser(){};

    @Override
    public void parseValues(String filename, String attribute){
        try{
            JsonElement jsonElement = new JsonParser().parse(new FileReader(filename));
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = jsonObj.entrySet();
            String attr = "";
            for(Map.Entry<String, JsonElement> entry: entries){
                attr = entry.getKey();
            }
            for(Map.Entry<String, JsonElement> entry : jsonObj.get(attr).getAsJsonObject().entrySet()){
                super.getValues().put(entry.getKey(), entry.getValue().getAsJsonObject().get(attribute).getAsDouble());
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void parseAttributes(String filename){
        try{
            JsonElement jsonElement = new JsonParser().parse(new FileReader(filename));
            JsonObject jsonObj = jsonElement.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = jsonObj.entrySet();
            String attr = "";
            for(Map.Entry<String, JsonElement> entry: entries){
                attr = entry.getKey();
            }
            entries = jsonObj.get(attr).getAsJsonObject().entrySet();
            String str = "";
            Map.Entry<String, JsonElement> entry = entries.iterator().next();
            str = entry.getValue().getAsJsonObject().toString();
            System.out.println(str);
            String[] split = str.split(",");
            String[] splitinner = split[0].split(":");
            super.getAttributes().add(splitinner[0].substring(2, splitinner[0].length()-1));
            for(int i=1; i<split.length; i++){
                splitinner = split[i].split(":");
                super.getAttributes().add(splitinner[0].substring(1, splitinner[0].length()-1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
