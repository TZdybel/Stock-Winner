package stockwinner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import stockwinner.parsing.AlphavantageParser;
import stockwinner.parsing.IextradingParser;
import stockwinner.parsing.Parser;
import stockwinner.parsing.QuandlWseParser;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class DataDialogController {

    private Stage stage;

    @FXML
    private ComboBox parserChoice;

    @FXML
    private TextField attachment;
    @FXML
    private TextField filename;
    private Map<String, Double> results;


    void setStage(Stage stage){
        this.stage = stage;
    }

    public void onNewFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        final File file = fileChooser.showOpenDialog(stage);
        if(file != null)
            filename.setText(file.getAbsolutePath().toString());
    }

    public void onFinish(ActionEvent actionEvent) {

        Parser parser;
        String chosenOne = parserChoice.getValue().toString();
        if(chosenOne.equals("AlphaVantage"))
            parser = new AlphavantageParser();
        else if(chosenOne.equals("IexTrading"))
            parser = new IextradingParser();
        else if (chosenOne.equals("Quandl_WSE"))
            parser = new QuandlWseParser();
        else
            return;

        parser.parseValues(filename.getText(), attachment.getText());
        results = parser.getValues();

        stage.close();
    }

    public Map<String, Double> getResults() {
        return results;
    }

    public ChartDataSource getConvertedResults() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Series<Long, Double> convertedResults = new Series<>();
        for(Map.Entry<String, Double> in : results.entrySet()){
            Long key = null;
            key = format.parse(in.getKey()).getTime();
            Data<Long, Double> point = new Data<>(key, in.getValue());
            convertedResults.getData().add( point );
        }

        return new ChartDataSource(convertedResults);
    }
}
