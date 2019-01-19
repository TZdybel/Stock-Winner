package stockwinner;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class ChartDataSource {

    private ObservableList<XYChart.Series<Long, Double>> seriesList;
    private XYChart.Series<Long, Double> inputValues;
    private List<XYChart.Series<Long, Double>> strategyValues = new ArrayList<>();

    private DoubleProperty maxHeight = new SimpleDoubleProperty(1.0);
    private long minX;
    private long maxX;

    public ChartDataSource(XYChart.Series<Long, Double> inputValues){
        this.inputValues = inputValues;
        seriesList = FXCollections.observableArrayList(inputValues);
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

    }

    public void addStrategySeries(XYChart.Series<Long, Double> series){
        strategyValues.add(series);
        seriesList.add(series);
    }

    public void addStrategyResults(ArrayList<Pair<Long, Double>> results){

        XYChart.Series<Long, Double> series = new XYChart.Series<>();

        for(Pair<Long, Double> p : results){
            XYChart.Data<Long, Double> datapoint = new XYChart.Data<>(p.getKey(), p.getValue());
            series.getData().add(datapoint);
        }

        addStrategySeries(series);
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

    public void clearStrategies() {
        seriesList = FXCollections.observableArrayList(inputValues);
        strategyValues.clear();
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


    public static ChartDataSource fromResults(Map<Long, Double> results) throws ParseException {

        XYChart.Series<Long, Double> convertedResults = new XYChart.Series<>();
        for(Map.Entry<Long, Double> in : results.entrySet()){
            XYChart.Data<Long, Double> point = new XYChart.Data<>(in.getKey(), in.getValue());
            convertedResults.getData().add( point );
        }

        ChartDataSource cds = new ChartDataSource(convertedResults);
        return cds;
    }

}
