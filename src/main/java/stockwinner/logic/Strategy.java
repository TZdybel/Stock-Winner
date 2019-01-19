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

    public List<Double> getResults(List<Double> input, double startingCash){
        // decyzja strategii jest procentem naszych środków które zainwestujemy/sprzedamy
        // ale gdyby zrobić obiekt "portfel" to miałby jedną metodę i zero pól
        // więc strategia od razu symuluje środki w naszym portfelu i zwraca $$$

        return new ArrayList<>();
    }

}
