package stockwinner.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stockwinner.ChartDataSource;
import stockwinner.Main;
import stockwinner.parsing.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class DataDialogController {

    private Stage stage;

    @FXML
    private ComboBox parserChoice;

    @FXML
    private ComboBox attributeChoice;

    @FXML
    private TextField filename;

    private Map<String, Double> results;

    private Parser parser = null;

    void setStage(Stage stage){
        this.stage = stage;
    }

    public void onNewFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik");
        fileChooser.setInitialDirectory(new File("./data"));
        final File file = fileChooser.showOpenDialog(stage);
        if(file != null)
            filename.setText(file.getAbsolutePath());

        if(parser != null){
            reloadAttributes();
        }
    }

    public void apiChange(ActionEvent actionEvent){
        String chosenOne = parserChoice.getValue().toString();
        if(chosenOne.equals("AlphaVantage"))
            parser = new AlphavantageParser();
        else if(chosenOne.equals("IexTrading"))
            parser = new IextradingParser();
        else if (chosenOne.equals("Quandl_WSE"))
            parser = new QuandlWseParser();
        else if (chosenOne.equals("CSV"))
            parser = new CSVParser();

        if( ! filename.getText().isEmpty()){
            reloadAttributes();
        }
    }

    private void reloadAttributes() {
        parser.parseAttributes(filename.getText());
        List<String> attributes = parser.getAttributes();
        attributeChoice.setItems(FXCollections.observableArrayList(attributes));
    }

    public void onFinish(ActionEvent actionEvent) {

        if(parser == null && attributeChoice.getValue() != null && ! attributeChoice.getValue().toString().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.WARNING, "Niepoprawne dane", ButtonType.OK);
            alert.showAndWait();

        } else {

            try {
                parser.parseValues(filename.getText(), attributeChoice.getValue().toString());
                results = parser.getValues();

                stage.close();
            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Nieznany błąd: " + e.toString(), ButtonType.OK);
                alert.showAndWait();
            }
        }

    }

    public void showDataDownloadDialog(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/DataDownloadDialog.fxml"));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Pobieranie nowych danych");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DataDownloadController dialog = loader.getController();
            dialog.setStage(dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        ChartDataSource cds = new ChartDataSource(convertedResults);
        return cds;
    }
}
