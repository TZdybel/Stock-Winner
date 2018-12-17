package stockwinner.logic;


import java.util.ArrayList;
import java.util.List;

public class Strategy {

    public enum LOGIC { OR, AND }

    private List<StrategyPart> parts;
    private LOGIC operator = LOGIC.AND;

    public Strategy(){
        parts = new ArrayList<>();
    }

    public void addPart(StrategyPart part){
        this.parts.add(part);
    }

    public void delPart(StrategyPart part){
        this.parts.remove(part);
    }

    public LOGIC getOperator(){
        return operator;
    }

    public void switchOperator(){
        operator = (operator == LOGIC.OR) ? LOGIC.AND : LOGIC.OR;
    }

}
