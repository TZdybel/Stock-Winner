package stockwinner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import stockwinner.logic.StrategyPart;

public class StrategyPartController extends VBox {

    @FXML
    private TextField days;

    @FXML
    private TextField procent;

    private StrategyPart part = new StrategyPart();

    public void maybeUpdate() throws IllegalArgumentException {
        try {
            int days = Integer.parseInt(this.days.getText());
            part.setDays(days);
        } catch (IllegalArgumentException e){
            throw new IllegalStateException("Błędna liczba dni");
        }

        try {
            double procent = Double.parseDouble(this.procent.getText());
            part.setChange(procent);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Błędna liczba procent");
        }
    }

    public StrategyPart getPart() {
        return part;
    }
}
