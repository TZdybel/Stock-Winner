package stockwinner.views;

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

    public boolean maybeUpdate(){
        // zwraca true jeżeli się powiodło
        try {
            int days = Integer.parseInt(this.days.getText());
            part.setDays(days);
            double procent = Double.parseDouble(this.procent.getText());
            part.setChange(procent);

            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public StrategyPart getPart() {
        return part;
    }
}
