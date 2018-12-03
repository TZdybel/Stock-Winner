package stockwinner;

import javafx.beans.property.DoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    @FXML
    public Text hoverValue;

    @FXML
    public Button tmpButton;

    @FXML
    private ComboBox<String> valueSource;

    @FXML
    private LineChart<Number, Number> valueChart;

    private Map<String, ChartDataSource> allSeries = new HashMap<>();

    @FXML
    private NumberAxis valueXAxis;
    @FXML
    private NumberAxis valueYAxis;


    private double lastMouseX;
    private double lastMouseY;

    private double maxChartWidth;
    private DoubleProperty maxChartHeight;

    void addDataSource(String name, ChartDataSource source) {
        valueSource.getItems().add(name);
        allSeries.put(name, source);
    }

    @FXML
    public void handleDataSourceChange(ActionEvent actionEvent) {
        showDataSource(allSeries.get(valueSource.getValue()));
    }

    public void showDataSource(ChartDataSource ds){
        valueChart.setData(ds.getSeriesList());

        maxChartHeight = ds.getHeightProperty();
        maxChartWidth = ds.getWidth();

        ds.getStrategyList().getData().addListener((ListChangeListener<XYChart.Data<Number, Number>>) c -> {
            while(c.next()) {
                for(XYChart.Data<Number, Number> datapoint : c.getAddedSubList()){
                    Node defaultNode = datapoint.getNode();
                    if (defaultNode != null) {
                        defaultNode.setOnMouseEntered(event -> {
                            hoverValue.setText(datapoint.getXValue() + ": " + datapoint.getYValue());
                        });
                        defaultNode.setOnMouseExited(event -> {
                            hoverValue.setText("");
                        });
                    }
                }
            }
        });

        valueXAxis.setLowerBound(0);
        valueYAxis.setLowerBound(0);
        valueXAxis.setUpperBound(ds.getWidth());
        valueYAxis.setUpperBound(maxChartHeight.get());
    }


    public void fillChart() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < 50; i++)
            series.getData().add(new XYChart.Data(i, Math.exp(i / 10.0)));
        valueChart.getData().add(series);

        valueXAxis.setAutoRanging(true);
        valueYAxis.setAutoRanging(true);
    }


    @FXML
    protected void handleValueChartScroll(ScrollEvent event) {
        valueXAxis.setAutoRanging(false);
        valueYAxis.setAutoRanging(false);

        // scroll w dol => delta dodatnia
        double scrollFactor = -1 * event.getDeltaY() / 800.0;
        scrollFactor = Math.min( 0.1, Math.max(-0.1, scrollFactor));

        double zoomCenterX = valueXAxis.getValueForDisplay(event.getX()).doubleValue();
        double zoomCenterY = valueYAxis.getValueForDisplay(event.getY()).doubleValue();

        double xLeftDelta =  (zoomCenterX - valueXAxis.getLowerBound()) * scrollFactor;
        double xRightDelta = (valueXAxis.getUpperBound() - zoomCenterX) * scrollFactor;
        double yDownDelta =  (zoomCenterY - valueYAxis.getLowerBound()) * scrollFactor;
        double yUpDelta =    (valueYAxis.getUpperBound() - zoomCenterY) * scrollFactor;

        double newXLowerBound = Math.max(0, valueXAxis.getLowerBound() + xLeftDelta);
        double newXUpperBound = Math.min(maxChartWidth, valueXAxis.getUpperBound() - xRightDelta);

        double newYLowerBound = Math.max(0, valueYAxis.getLowerBound() + yDownDelta);
        double newYUpperBound = Math.min(maxChartHeight.get(), valueYAxis.getUpperBound() - yUpDelta);

        if( newXLowerBound >= newXUpperBound || newYLowerBound >= newYUpperBound )
            return;

        valueXAxis.setLowerBound( newXLowerBound );
        valueXAxis.setUpperBound( newXUpperBound );

        valueYAxis.setLowerBound( newYLowerBound );
        valueYAxis.setUpperBound( newYUpperBound );
    }


    @FXML
    public void handleValueChartMouseDragStart(MouseEvent dragEvent) {
        lastMouseX = dragEvent.getX();
        lastMouseY = dragEvent.getY();
    }


    @FXML
    public void handleValueChartMouseDrag(MouseEvent mouseEvent) {
        double deltaX = lastMouseX -  mouseEvent.getX();
        double deltaY = mouseEvent.getY() - lastMouseY;

        double currentWidth = valueXAxis.getUpperBound() - valueXAxis.getLowerBound();
        double currentHeight = valueYAxis.getUpperBound() - valueYAxis.getLowerBound();

        deltaX *= currentWidth / (maxChartWidth + maxChartHeight.get());
        deltaY *= currentHeight / (maxChartWidth + maxChartHeight.get());

        lastMouseX = mouseEvent.getX();
        lastMouseY = mouseEvent.getY();

        if(deltaX + valueXAxis.getLowerBound() < 0 || deltaX + valueXAxis.getUpperBound() > maxChartWidth)
            deltaX = 0;
        if(deltaY + valueYAxis.getLowerBound() < 0 || deltaY + valueYAxis.getUpperBound() > maxChartHeight.get())
            deltaY = 0;

        valueXAxis.setLowerBound( valueXAxis.getLowerBound() + deltaX );
        valueXAxis.setUpperBound( valueXAxis.getUpperBound() + deltaX );

        valueYAxis.setLowerBound( valueYAxis.getLowerBound() + deltaY );
        valueYAxis.setUpperBound( valueYAxis.getUpperBound() + deltaY );
    }

    public void onZoomReset(ActionEvent actionEvent) {
        valueXAxis.setAutoRanging(true);
        valueYAxis.setAutoRanging(true);
    }
}
