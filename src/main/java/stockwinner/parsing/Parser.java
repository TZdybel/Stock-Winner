package stockwinner.parsing;

import java.util.*;

public abstract class Parser {

    private Map<String, Double> values;
    private List<String> attributes;

    public Parser(){
        this.values = new LinkedHashMap<String, Double>();
        this.attributes = new ArrayList<String>();
    }

    public Map<String, Double> getValues(){
        return values;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public abstract void parseValues(String filename, String attribute);
    public abstract void parseAttributes(String filename);

    public void parseValues(String filename, int val){
        parseValues(filename, String.valueOf(val));
    }

}