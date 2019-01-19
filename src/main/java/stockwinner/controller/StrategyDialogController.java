package stockwinner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stockwinner.Main;
import stockwinner.logic.Strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StrategyDialogController {

    public TextField strategyName;
    private Stage stage;

    @FXML
    private VBox strats;

    private List<StrategyPartController> strategyControllers = new ArrayList<>();
    private Strategy strategy = new Strategy();

    void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest((value) -> this.strategy = null );
    }

    public void addPart(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/StrategyPartView.fxml"));
        try {
            loader.setRoot(strats);
            loader.load();

            StrategyPartController svc = loader.getController();
            strategyControllers.add(svc);
            strategy.addPart(svc.getPart());

        } catch (IOException err){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Błąd ładowania StrategyPartView.fxml: " + err.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void delPart(ActionEvent actionEvent) {
        if(strategy.getParts().size() == 0) return;
        int index = strategy.getParts().size()-1;
        strategyControllers.remove(index);
        strats.getChildren().remove(index);
        strategy.delPart(index);
    }

    public void finish(ActionEvent actionEvent) {
        if(strategyControllers.size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Brak części strategii", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if(strategyName.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Brak nazwy", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        strategy.setName(strategyName.getText());

        for(int i = 0; i < strategyControllers.size(); i++){
            StrategyPartController svc = strategyControllers.get(i);
            try{
                svc.maybeUpdate();
            } catch (IllegalArgumentException | IllegalStateException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Błąd w strategii nr " + (i+1) + ":" + e.getMessage(), ButtonType.OK);
                alert.showAndWait();
                return;
            }
        }

        // strategia jest gotowa do zwrotu

        stage.close();
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
