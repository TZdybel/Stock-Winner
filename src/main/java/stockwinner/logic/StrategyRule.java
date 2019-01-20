package stockwinner.logic;

import java.util.List;

public class StrategyRule {

    private int days;
    private double change;

    public StrategyRule(){
        this(1,0);
    }

    public StrategyRule(int days, double change){
        this.days = days;
        this.change = change;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) throws IllegalArgumentException {
        if(days < 1)
            throw new IllegalArgumentException("Liczba dni jest mniejsza niż 1");
        this.days = days;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }


    public boolean applies(List<Double> input, int offeset){
        // czy reguła spełniona
        
        double expected = input.get(offeset) * (1+ this.getChange());
        if(offeset + this.getDays() > input.size()){
            return false;
        }
        if (expected >= input.get(offeset+this.getDays())){
            return true;
        }
        return false;
        
        
        return false;
    }

}
