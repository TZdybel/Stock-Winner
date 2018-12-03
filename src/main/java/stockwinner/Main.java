package stockwinner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();

        List<Double> source = new ArrayList<>();

        //ChartDataSource ds = new ChartDataSource(eSeries);

        //controller.addDataSource("150-exp", ds);
        //controller.showDataSource(ds);

        Scene scene = new Scene(root, 800,800);

        stage.setMaxWidth(800);
        stage.setMaxHeight(800);
        stage.setMinWidth(800);
        stage.setMinHeight(800);

        stage.setTitle("StockWinner");
        stage.setScene(scene);
        stage.show();

        /*
        FakeStrategy fs = new FakeStrategy(source);
        fs.getOutput().addListener((ListChangeListener<Pair<Double, Double>>) c -> {
            c.next();
            for(Pair<Double, Double> d : c.getAddedSubList()){
                Platform.runLater(() -> ds1.addNextStrategyValue(d.getKey(), d.getValue()));
            }
        });
        fs.startWork();
        */
    }

    public static void main(String[] args) {
        launch(args);
    }
}
