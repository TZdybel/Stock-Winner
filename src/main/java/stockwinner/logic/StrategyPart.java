package stockwinner.logic;


import java.util.ArrayList;
import java.util.List;

public class StrategyPart {

    public enum LOGIC { OR, AND }

    private List<StrategyRule> rules;

    private double value = 0.0;
    private LOGIC operator = LOGIC.AND;

    public StrategyPart(){
        rules = new ArrayList<>();
    }

    public void addRule(StrategyRule rule){
        this.rules.add(rule);
    }

    public void delRule(StrategyRule rule){
        this.rules.remove(rule);
    }

    public void delRule(int index){
        this.rules.remove(index);
    }

    public void switchOperator(){
        operator = (operator == LOGIC.OR) ? LOGIC.AND : LOGIC.OR;
    }

    public LOGIC getOperator(){
        return operator;
    }

    public double getResult() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public double getResult(List<Double> input, int offset){
        // input -- dane z giełdy
        // offset -- obecny dzień

        // gdy reguły nie spełnione
        // lub gdy offset jest za mały ( np. 1 a jedna z reguł wymaga znajomości wartości sprzed 4 dni )
        // to return 0.0

        boolean decision = false;
        for (int i = 0; i < rules.size(); i++) {
            boolean applies = rules.get(i).applies(input, offset);
            if (operator == LOGIC.AND && !applies)
                return 0.0;
            if (applies) {
                decision = true;
                if(operator == LOGIC.OR)
                    break;
            }
        }
        if (!decision)
            return 0.0;

        return this.value;
    }

}
