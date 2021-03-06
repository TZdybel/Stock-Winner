package stockwinner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import stockwinner.Main;
import stockwinner.logic.StrategyPart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class StrategyPartController extends VBox {

    @FXML
    private TextField value;

    @FXML
    private VBox rules;

    @FXML
    private Button op;

    private StrategyPart part = new StrategyPart();
    private List<StrategyRuleController> partControllers = new ArrayList<>();

    public void addRule(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/StrategyRuleView.fxml"));
        try {
            loader.setRoot(rules);
            loader.load();

            StrategyRuleController p = loader.getController();
            part.addRule(p.getRule());
            partControllers.add(p);

        } catch (IOException err){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Błąd ładowania StrategyRuleView.fxml: " + err.toString(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void maybeUpdate() throws IllegalArgumentException, IllegalStateException {

        if(partControllers.size() == 0){
            throw new IllegalStateException("Brak reguł");
        }

        try {
            int convertedValue = Integer.parseInt(value.getText());
            part.setValue(convertedValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Błędna wartość decyzji");
        }

        for (int i = 0; i < partControllers.size(); i++) {
            StrategyRuleController spc = partControllers.get(i);
            try {
                spc.maybeUpdate();
            } catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Błąd w regule " + (i+1));
            }
        }
    }

    public void delRule(ActionEvent actionEvent) {
        if(partControllers.size() == 0) return;
        int index = partControllers.size()-1;
        rules.getChildren().remove(index);
        partControllers.remove(index);
        part.delRule(index);
    }

    public void logicSwitch(ActionEvent actionEvent) {
        part.switchOperator();
        op.setText(part.getOperator().toString());
    }

    public StrategyPart getPart() {
        return part;
    }
}
