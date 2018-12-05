package stockwinner;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.util.StringConverter;
import stockwinner.parsing.AlphavantageParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChartController {

    @FXML
    private LineChart<Long, Double> valueChart;

    @FXML
    private NumberAxis valueXAxis;
    @FXML
    private NumberAxis valueYAxis;

    private ChartDataSource currentSource;

    private double lastMouseX;
    private double lastMouseY;

    private DoubleProperty maxChartHeight;
    private StringProperty hoverProperty;

    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    protected void handleValueChartScroll(ScrollEvent event) {
        valueXAxis.setAutoRanging(false);
        valueYAxis.setAutoRanging(false);

        // scroll w dol => delta dodatnia
        double scrollFactorX = -1 * event.getDeltaX() / 800.0;
        double scrollFactorY = -1 * event.getDeltaY() / 800.0;
        scrollFactorX = Math.min( 0.2, Math.max(-0.2, scrollFactorX));
        scrollFactorY = Math.min( 0.2, Math.max(-0.2, scrollFactorY));

        double zoomCenterX = valueXAxis.getValueForDisplay(event.getX()).doubleValue();
        double zoomCenterY = valueYAxis.getValueForDisplay(event.getY()).doubleValue();

        double xLeftDelta =  (zoomCenterX - valueXAxis.getLowerBound()) * scrollFactorX;
        double xRightDelta = (valueXAxis.getUpperBound() - zoomCenterX) * scrollFactorX;
        double yDownDelta =  (zoomCenterY - valueYAxis.getLowerBound()) * scrollFactorY;
        double yUpDelta =    (valueYAxis.getUpperBound() - zoomCenterY) * scrollFactorY;

        double newXLowerBound = valueXAxis.getLowerBound() + xLeftDelta;
        double newXUpperBound = valueXAxis.getUpperBound() - xRightDelta;

        double newYLowerBound = Math.max(0, valueYAxis.getLowerBound() + yDownDelta);
        double newYUpperBound = Math.min(maxChartHeight.get(), valueYAxis.getUpperBound() - yUpDelta);

        //if( newXLowerBound >= newXUpperBound || newYLowerBound >= newYUpperBound )
        //return;

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


        double deltaX = (lastMouseX -  mouseEvent.getX())/800;
        double deltaY = (mouseEvent.getY() - lastMouseY)/800;

        double currentWidth = valueXAxis.getUpperBound() - valueXAxis.getLowerBound();
        double currentHeight = valueYAxis.getUpperBound() - valueYAxis.getLowerBound();

        deltaX *= currentWidth;
        deltaY *= currentHeight;

        lastMouseX = mouseEvent.getX();
        lastMouseY = mouseEvent.getY();

        valueXAxis.setLowerBound( valueXAxis.getLowerBound() + deltaX );
        valueXAxis.setUpperBound( valueXAxis.getUpperBound() + deltaX );
        valueYAxis.setLowerBound( valueYAxis.getLowerBound() + deltaY );
        valueYAxis.setUpperBound( valueYAxis.getUpperBound() + deltaY );
    }

    public StringProperty getHoverProperty() {
        return hoverProperty;
    }

    void nodeSetup(XYChart.Data<Long, Double> point){
        final
        Node node = point.getNode();
        if (node != null) {
            node.setOnMouseEntered(event -> {
                hoverProperty.set( format.format(new Date(point.getXValue())) + ": " + point.getYValue());
            });

            node.setOnMouseExited(event -> {
                hoverProperty.set("");
            });
        }
    }

    public void setData(ChartDataSource ds) {
        currentSource = ds;
        maxChartHeight = ds.getHeightProperty();

        valueChart.setData(ds.getSeriesList());

        ds.getInputValues().getData().stream().forEach(point -> { nodeSetup(point); });

        ds.getStrategyList().getData().addListener((ListChangeListener<XYChart.Data<Long, Double>>) c -> {
            while(c.next()) {
                for(XYChart.Data<Long, Double> point : c.getAddedSubList()){
                    nodeSetup(point);
                }
            }
        });

        valueXAxis.setLowerBound(ds.getMinX());
        valueYAxis.setLowerBound(0);
        valueXAxis.setUpperBound(ds.getMaxX());
        valueYAxis.setUpperBound(maxChartHeight.get());
    }

    public void init() {

        hoverProperty = new SimpleStringProperty();

        valueXAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                Date d = new Date(object.longValue());
                return format.format(d);
            }

            @Override
            public Number fromString(String string) {
                try {
                    return format.parse(string).getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
    }

    public void resetZoom() {
        valueXAxis.setLowerBound(currentSource.getMinX());
        valueXAxis.setUpperBound(currentSource.getMaxX());
        valueYAxis.setAutoRanging(true);
    }
}
