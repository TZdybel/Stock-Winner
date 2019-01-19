package stockwinner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import stockwinner.logic.StrategyRule;

public class StrategyRuleController extends VBox {

    @FXML
    private TextField days;

    @FXML
    private TextField procent;

    private StrategyRule rule = new StrategyRule();

    public void maybeUpdate() throws IllegalArgumentException {
        try {
            int days = Integer.parseInt(this.days.getText());
            rule.setDays(days);
        } catch (IllegalArgumentException e){
            throw new IllegalStateException("Błędna liczba dni");
        }

        try {
            double procent = Double.parseDouble(this.procent.getText());
            rule.setChange(procent);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Błędna liczba procent");
        }
    }

    public StrategyRule getRule() {
        return rule;
    }
}
