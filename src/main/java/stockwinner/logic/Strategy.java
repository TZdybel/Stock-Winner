package stockwinner.logic;


import java.util.ArrayList;
import java.util.List;

public class Strategy {

    public enum LOGIC { OR, AND }

    private List<StrategyPart> parts;

    private double value = 0.0;
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

    public void delPart(int index){
        this.parts.remove(index);
    }

    public void switchOperator(){
        operator = (operator == LOGIC.OR) ? LOGIC.AND : LOGIC.OR;
    }

    public LOGIC getOperator(){
        return operator;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
