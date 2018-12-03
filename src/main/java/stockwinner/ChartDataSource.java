package stockwinner;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.util.Comparator;

public class ChartDataSource {

    private ObservableList<XYChart.Series<Number, Number>> seriesList;
    private XYChart.Series<Number, Number> inputValues;
    private XYChart.Series<Number, Number> strategyValues = new XYChart.Series<>();

    private DoubleProperty maxHeight = new SimpleDoubleProperty(1.0);

    public ChartDataSource(XYChart.Series<Number, Number> inputValues){
        this.inputValues = inputValues;
        seriesList = FXCollections.observableArrayList(inputValues, strategyValues);
        maxHeight.set(inputValues.getData().stream()
                .map(data -> (double)data.getYValue())
                .max(Comparator.naturalOrder())
                .orElse(1.0)
                * 1.1);

        strategyValues.getData().addListener((ListChangeListener<XYChart.Data<Number, Number>>) c -> {
            while (c.next()){
                if(c.wasAdded()){
                    for(XYChart.Data<Number, Number> p : c.getAddedSubList())
                        if(p.getYValue().doubleValue() > maxHeight.get() )
                            maxHeight.setValue(p.getYValue().doubleValue());
                }
            }
        });
    }

    public void addNextStrategyValue(Number x, Number y){
        XYChart.Data<Number, Number> datapoint = new XYChart.Data<>(x,y);
        strategyValues.getData().add(datapoint);
    }


    public ObservableList<XYChart.Series<Number, Number>> getSeriesList() {
        return seriesList;
    }

    public double getWidth() {
        ObservableList<XYChart.Data<Number, Number>> list = inputValues.getData();
        if(list.size() == 0) return 1.0;
        return (double)list.get(list.size()-1).getXValue();
    }

    public DoubleProperty getHeightProperty() {
        return maxHeight;
    }

    public XYChart.Series<Number, Number> getStrategyList() {
        return strategyValues;
    }
}
