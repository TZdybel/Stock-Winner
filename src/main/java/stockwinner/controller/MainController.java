package stockwinner.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stockwinner.ChartDataSource;
import stockwinner.Main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class MainController {

    private Stage primaryStage;

    @FXML
    public Text hoverValue;

    @FXML
    private ComboBox<String> valueSource;

    @FXML
    Node chart;
    @FXML
    private ChartController chartController;

    private Map<String, ChartDataSource> allSeries = new HashMap<>();

    public void init(Stage stage){
        this.primaryStage = stage;

        chartController.init();
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        chartController.getHoverProperty().addListener((observable, oldValue, newValue) -> {
            hoverValue.setText(newValue);
        });
    }

    void addDataSource(String name, ChartDataSource source) {
        valueSource.getItems().add(name);
        allSeries.put(name, source);
    }

    @FXML
    public void handleDataSourceChange(ActionEvent actionEvent) {
        showDataSource(allSeries.get(valueSource.getValue()));
    }

    public void showDataDialog(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/DataDialog.fxml"));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dodaj nowe źródło danych");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DataDialogController dialog = loader.getController();
            dialog.setStage(dialogStage);

            dialogStage.showAndWait();
            ChartDataSource source = dialog.getConvertedResults();

            addDataSource("dataSource" + allSeries.size(), source);
            chartController.resetZoom();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void showStratsDialog(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/StrategyDialog.fxml"));
            VBox page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setHeight(600.0);
            dialogStage.setWidth(600.0);
            dialogStage.setTitle("Dodaj nowy zestaw strategii");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StrategyDialogController dialog = loader.getController();
            dialog.setStage(dialogStage);

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDataSource(ChartDataSource ds){
        chartController.setData(ds);
    }


    // handlery związane z wykresem poniżej

    public void onZoomReset(ActionEvent actionEvent) {
        chartController.resetZoom();
    }

}
