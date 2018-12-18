package stockwinner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stockwinner.Main;
import stockwinner.logic.Strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StrategyDialogController {

    private Stage stage;

    @FXML
    private VBox strats;

    List<StrategyController> strategyControllers = new ArrayList<>();
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

            StrategyController svc = loader.getController();
            strategyControllers.add(svc);
            strategies.add(svc.getStrategy());

        } catch (IOException err){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Błąd ładowania StrategyView.fxml: " + err.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void delStrategy(ActionEvent actionEvent) {
        if(strategies.size() == 0) return;
        int index = strategies.size()-1;
        strategyControllers.remove(index);
        strats.getChildren().remove(index);
        strategies.remove(index);
    }

    public void addGroup(ActionEvent actionEvent) {
        if(strategyControllers.size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Brak strategii", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        for(int i = 0; i < strategyControllers.size(); i++){
            StrategyController svc = strategyControllers.get(i);
            try{
                svc.maybeUpdate();
            } catch (IllegalArgumentException | IllegalStateException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Błąd w strategii nr " + (i+1) + ":" + e.getMessage(), ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }
        // lista strategies jest gotowa do zwrotu

        //stage.close();
        return;
    }
}
