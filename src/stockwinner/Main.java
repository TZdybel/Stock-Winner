package stockwinner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/main.fxml"));
        Parent root = loader.load();

        ChartController controller = loader.getController();

        // temp dane
        XYChart.Series<Number, Number> eSeries = new XYChart.Series<>();
        XYChart.Series<Number, Number> eSeries2 = new XYChart.Series<>();
        for (int i = 0; i < 50; i++) {
            eSeries.getData().add(new XYChart.Data((double) i, Math.exp(4.9) - Math.exp(i / 10.0)));
            eSeries2.getData().add(new XYChart.Data((double) i, Math.exp(i / 10.0)));
        }

        ChartDataSource ds1 = new ChartDataSource(eSeries);
        ChartDataSource ds2 = new ChartDataSource(eSeries2);

        controller.addDataSource("150-exp", ds1);
        controller.addDataSource("exp", ds2);

        controller.showDataSource(ds1);

        Scene scene = new Scene(root, 800,800);

        stage.setMaxWidth(800);
        stage.setMaxHeight(800);
        stage.setMinWidth(800);
        stage.setMinHeight(800);

        stage.setTitle("StockWinner");
        stage.setScene(scene);
        stage.show();

        // w celu demonstracji
        final double[] last = {0.0, 0.0};
        controller.tmpButton.setOnMouseClicked(event -> {
            Random r = new Random();
            ds1.addNextStrategyValue(last[0], last[0]*20);
            ds2.addNextStrategyValue(last[0], last[1]);
            last[0] += 1.0;
            last[1] += r.nextDouble()*4;
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
