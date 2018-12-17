package stockwinner.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import stockwinner.Main;
import stockwinner.logic.Strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StratPane extends VBox {

    @FXML
    private TextField value;

    @FXML
    private VBox rules;

    @FXML
    private Button op;

    private Strategy strategy = new Strategy();

    private List<StrategyPartController> partPanes = new ArrayList<>();

    public void addRule(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/StrategyPartView.fxml"));
        try {
            loader.setRoot(rules);
            loader.load();

            StrategyPartController p = loader.getController();
            strategy.addPart(p.getPart());
            partPanes.add(p);

        } catch (IOException err){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Błąd ładowania StrategyPartView.fxml: " + err.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public boolean maybeUpdate(){

        return false;
    }

    public void delRule(ActionEvent actionEvent) {

    }

    public void logicSwitch(ActionEvent actionEvent) {
        strategy.switchOperator();
        op.setText(strategy.getOperator().toString());
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
