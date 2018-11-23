package stockwinner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.Comparator;

public class ChartDataSource {

    private ObservableList<XYChart.Series<Number, Number>> seriesList;
    private XYChart.Series<Number, Number> inputValues;
    private XYChart.Series<Number, Number> strategyValues = new XYChart.Series<>();

    private double maxHeight = 0.0;

    public ChartDataSource(XYChart.Series<Number, Number> inputValues){
        this.inputValues = inputValues;
        seriesList = FXCollections.observableArrayList(inputValues, strategyValues);
        maxHeight = inputValues.getData().stream()
                .map(data -> (double)data.getYValue())
                .max(Comparator.naturalOrder())
                .orElse(1.0);
    }

    public void addNextStrategyValue(Number x, Number y){
        if( (double) y > maxHeight )
            maxHeight = (double)y;
        XYChart.Data<Number, Number> datapoint = new XYChart.Data<>(x,y);
        //datapoint.setNode(...); // soon
        strategyValues.getData().add(datapoint);
    }


    public ObservableList<XYChart.Series<Number, Number>> getSeriesList() {
        return seriesList;
    }

    public double getHeight(){
        return maxHeight;
    }

    public double getWidth() {
        ObservableList<XYChart.Data<Number, Number>> list = inputValues.getData();
        if(list.size() == 0) return 1.0;
        return (double)list.get(list.size()-1).getXValue();
    }
}
