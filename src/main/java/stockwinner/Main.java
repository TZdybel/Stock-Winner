package stockwinner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.init(stage);

        Scene scene = new Scene(root, 800,800);

        stage.setMaxWidth(800);
        stage.setMaxHeight(800);
        stage.setMinWidth(800);
        stage.setMinHeight(800);

        stage.setTitle("StockWinner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
