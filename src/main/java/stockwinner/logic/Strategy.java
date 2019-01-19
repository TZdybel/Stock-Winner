package stockwinner.logic;

import java.util.ArrayList;
import java.util.List;

public class Strategy {

    private List<StrategyPart> parts = new ArrayList<>();
    private String name;

    public void addPart(StrategyPart part){
        parts.add(part);
    }

    public List<StrategyPart> getParts() {
        return parts;
    }

    public void delPart(int index) {
        parts.remove(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
