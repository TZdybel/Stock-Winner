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

    public List<Double> getResults(List<Double> input, double cash){
        // decyzja strategii jest procentem naszych środków które zainwestujemy/sprzedamy
        // ale gdyby zrobić obiekt "portfel" to miałby jedną metodę i zero pól
        // więc strategia od razu symuluje środki w naszym portfelu i zwraca $$$
        //StrategyPart - parts

        double invested = 0;
        ArrayList<Double> results = new ArrayList<>();
        results.add(cash); // dzień 0
        for(int i = 1; i < input.size(); i++){

            double decision = 0.0;
            for(int j = 0; j < parts.size(); j++){
                decision += parts.get(j).getResult(input, i);
            }

            // zainwestowane środki rosną/maleją
            invested *= input.get(i) / input.get(i-1);

            decision = Double.max(-100.0, decision);
            decision = Double.min(100.0, decision);

            // a potem dodajemy/odejmujemy od nich na podstawie decyzji
            double delta = 0.0;
            if( decision > 0 ){
                delta = cash * decision/100.0;
            } else {
                delta = invested * decision/100.0;
            }
            cash -= delta;
            invested += delta;

            results.add(cash + invested);
        }
        return results;
    }

}
