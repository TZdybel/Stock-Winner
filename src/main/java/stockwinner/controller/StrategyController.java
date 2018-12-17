package stockwinner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stockwinner.Main;
import stockwinner.logic.Strategy;
import stockwinner.views.StratPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StrategyController {

    private Stage stage;

    @FXML
    private VBox strats;

    List<Strategy> strategies = new ArrayList<>();

    void setStage(Stage stage) {
        this.stage = stage;
    }

    public void addStrategy(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/StrategyView.fxml"));
        try {
            loader.setRoot(strats);
            loader.load();

            StratPane svc = loader.getController();
            strategies.add(svc.getStrategy());

            strategies.add(new Strategy());

        } catch (IOException err){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Błąd ładowania StrategyView.fxml: " + err.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void delStrategy(ActionEvent actionEvent) {
        strats.getChildren().remove(strats.getChildren().size()-1);
        strategies.remove(strategies.size()-1);
    }
}
