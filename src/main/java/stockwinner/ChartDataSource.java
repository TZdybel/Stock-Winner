package stockwinner;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

import java.util.Comparator;

public class ChartDataSource {

    private ObservableList<XYChart.Series<Long, Double>> seriesList;
    private XYChart.Series<Long, Double> inputValues;
    private XYChart.Series<Long, Double> strategyValues = new XYChart.Series<>();

    private DoubleProperty maxHeight = new SimpleDoubleProperty(1.0);
    private long minX;
    private long maxX;

    public ChartDataSource(XYChart.Series<Long, Double> inputValues){
        this.inputValues = inputValues;
        seriesList = FXCollections.observableArrayList(inputValues, strategyValues);
        minX = inputValues.getData().stream()
                .map(XYChart.Data::getXValue)
                .min(Long::compare).orElse(0L);
        maxX = inputValues.getData().stream()
                .map(XYChart.Data::getXValue)
                .max(Long::compare).orElse(0L);
        maxHeight.set(inputValues.getData().stream()
                .map(data -> (double)data.getYValue())
                .max(Comparator.naturalOrder())
                .orElse(1.0)
                * 1.1);

        inputValues.getData().stream()
                .forEach(d ->
                        d.getNode()
                        );

        strategyValues.getData().addListener((ListChangeListener<XYChart.Data<Long, Double>>) c -> {
            while (c.next()){
                if(c.wasAdded()){
                    for(XYChart.Data<Long, Double> p : c.getAddedSubList())
                        if(p.getYValue().doubleValue() > maxHeight.get() )
                            maxHeight.setValue(p.getYValue().doubleValue());
                }
            }
        });
    }

    public void addNextStrategyValue(Long x, Double y){
        XYChart.Data<Long, Double> datapoint = new XYChart.Data<>(x,y);
        strategyValues.getData().add(datapoint);
    }


    public ObservableList<XYChart.Series<Long, Double>> getSeriesList() {
        return seriesList;
    }

    public double getWidth() {
        return maxX - minX;
    }

    public DoubleProperty getHeightProperty() {
        return maxHeight;
    }

    public XYChart.Series<Long, Double> getStrategyList() {
        return strategyValues;
    }

    public long getMinX() {
        return minX;
    }

    public long getMaxX() {
        return maxX;
    }

    public XYChart.Series<Long, Double> getInputValues() {
        return inputValues;
    }
}
