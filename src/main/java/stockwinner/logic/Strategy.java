package stockwinner.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.List;

abstract public class Strategy {

    protected List<Double> input;
    protected ObservableList<Pair<Double, Double>> output;

    public Strategy(List<Double> input){
        output = FXCollections.observableArrayList();
        this.input = input;
    }

    public ObservableList<Pair<Double, Double>> getOutput(){
        return output;
    }

    abstract public void startWork();
}
