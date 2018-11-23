package stockwinner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.HashMap;
import java.util.Map;

public class ChartController {

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
    private double maxChartHeight;


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
        this.maxChartWidth = 50.0;
        this.maxChartHeight = Math.exp(5);
        this.maxChartHeight = ds.getHeight();

        valueXAxis.setLowerBound(0);
        valueYAxis.setLowerBound(0);
        valueXAxis.setUpperBound(ds.getWidth());
        valueYAxis.setUpperBound(maxChartHeight);
    }

    public void fillChart() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < 50; i++)
            series.getData().add(new XYChart.Data(i, Math.exp(i / 10.0)));
        valueChart.getData().add(series);

        this.maxChartWidth = 50.0; // valueXAxis.getUpperBound();
        this.maxChartHeight = Math.exp(5); //valueYAxis.getUpperBound();

        valueXAxis.setLowerBound(0);
        valueYAxis.setLowerBound(0);
        valueXAxis.setUpperBound(maxChartWidth);
        valueYAxis.setUpperBound(maxChartHeight);
    }



    @FXML
    protected void handleValueChartScroll(ScrollEvent event) {
        // zoom jest jeszcze do poprawy. nie zoomuje w kierunku kursora i nie jest ograniczony


        valueXAxis.setAutoRanging(false);
        valueYAxis.setAutoRanging(false);

        // scroll w dol => delta dodatnia
        double scrollFactor = -1 * event.getDeltaY() / 800.0;
        scrollFactor = Math.min( 0.1, Math.max(-0.1, scrollFactor));


        double width = valueXAxis.getUpperBound() - valueXAxis.getLowerBound();
        double height = valueYAxis.getUpperBound() - valueYAxis.getLowerBound();

        double zoomCenterX = (double) valueXAxis.getValueForDisplay(event.getX());
        double zoomCenterY = (double) valueYAxis.getValueForDisplay(event.getY());

        double newXLowerBound = valueXAxis.getLowerBound() * (1+scrollFactor) ;
        double newXUpperBound = valueXAxis.getUpperBound() * (1+scrollFactor) ;

        double newYLowerBound = valueYAxis.getLowerBound() * (1+scrollFactor) ;
        double newYUpperBound = valueYAxis.getUpperBound() * (1+scrollFactor) ;

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

        deltaX *= currentWidth / (maxChartWidth + maxChartHeight);
        deltaY *= currentHeight / (maxChartWidth + maxChartHeight);

        lastMouseX = mouseEvent.getX();
        lastMouseY = mouseEvent.getY();

        if(deltaX + valueXAxis.getLowerBound() < 0 || deltaX + valueXAxis.getUpperBound() > maxChartWidth)
            deltaX = 0;
        if(deltaY + valueYAxis.getLowerBound() < 0 || deltaY + valueYAxis.getUpperBound() > maxChartHeight)
            deltaY = 0;

        valueXAxis.setLowerBound( valueXAxis.getLowerBound() + deltaX );
        valueXAxis.setUpperBound( valueXAxis.getUpperBound() + deltaX );

        valueYAxis.setLowerBound( valueYAxis.getLowerBound() + deltaY );
        valueYAxis.setUpperBound( valueYAxis.getUpperBound() + deltaY );
    }

}
