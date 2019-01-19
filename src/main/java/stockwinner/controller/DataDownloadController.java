package stockwinner.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import stockwinner.datadownload.Downloader;
import stockwinner.datadownload.enums.AlphaVantageFunction;
import stockwinner.datadownload.enums.Function;
import stockwinner.datadownload.enums.IEXTradingFunction;
import stockwinner.datadownload.enums.QuandlWSEFunction;
import stockwinner.datadownload.urlbuilders.AlphaVantageURLBuilder;
import stockwinner.datadownload.urlbuilders.IEXTradingURLBuilder;
import stockwinner.datadownload.urlbuilders.QuandlWSEURLBuilder;
import stockwinner.datadownload.urlbuilders.URLBuilder;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataDownloadController {
    private Stage stage;

    @FXML
    private ComboBox APIChoice;

    @FXML
    private ComboBox function;

    @FXML
    private ComboBox extension;

    @FXML
    private TextField symbol;

    @FXML
    private TextField toSymbol;

    @FXML
    private TextField folderName;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        toSymbol.setDisable(true);
    }

    public void reloadFunctions() {
        String choice = APIChoice.getValue().toString();
        if (choice.equals("AlphaVantage")) {
            List<String> functions = Stream.of(AlphaVantageFunction.values()).map(AlphaVantageFunction::name).collect(Collectors.toList());
            function.setItems(FXCollections.observableArrayList(functions));
        } else if (choice.equals("IexTrading")) {
            List<String> functions = Stream.of(IEXTradingFunction.values()).map(IEXTradingFunction::name).collect(Collectors.toList());
            function.setItems(FXCollections.observableArrayList(functions));
        } else if (choice.equals("QuandlWSE")) {
            List<String> functions = Stream.of(QuandlWSEFunction.values()).map(QuandlWSEFunction::name).collect(Collectors.toList());
            function.setItems(FXCollections.observableArrayList(functions));
        }
    }

    public void chooseFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Wybierz miejsce zapisu");
        File defaultDirectory = new File("./data");
        directoryChooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = directoryChooser.showDialog(stage);
        folderName.setText(selectedDirectory.getAbsolutePath());
    }

    public void functionChoice() {
        toSymbol.setText("");
        String choice = function.getValue().toString();
        if (APIChoice.getValue().toString().equals("AlphaVantage")) {
            if (!choice.equals("TIME_SERIES_DAILY") && !choice.equals("TIME_SERIES_WEEKLY") && !choice.equals("TIME_SERIES_MONTHLY")) {
                toSymbol.setDisable(false);
            } else toSymbol.setDisable(true);
        } else toSymbol.setDisable(true);
    }

    public void downloadAndExit() {
        if (!areAllFieldsFilled()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wprowadz dane we wszystkie pola", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        URLBuilder builder;
        Function func;
        String apichoice = APIChoice.getValue().toString();
        if (apichoice.equals("AlphaVantage")) {
            builder = new AlphaVantageURLBuilder();
            func = AlphaVantageFunction.valueOf(function.getValue().toString());
        }
        else if (apichoice.equals("IexTrading")) {
            builder = new IEXTradingURLBuilder();
            func = IEXTradingFunction.valueOf(function.getValue().toString());
        }
        else if (apichoice.equals("QuandlWSE")) {
            builder = new QuandlWSEURLBuilder();
            func = QuandlWSEFunction.valueOf(function.getValue().toString());
        }
        else return;

        builder.function(func).datatype(extension.getValue().toString()).symbol(symbol.getText());
        String filename;
        if (!toSymbol.isDisabled() && !toSymbol.getText().equals("")) {
            builder.toSymbol(toSymbol.getText());
            filename = String.format("%s_to_%s_%s_%s.%s", symbol.getText(), toSymbol.getText(), function.getValue().toString(), APIChoice.getValue().toString(), extension.getValue().toString());
        } else filename = String.format("%s_%s_%s.%s", symbol.getText(), function.getValue().toString(), APIChoice.getValue().toString(), extension.getValue().toString());

        String absolutePath = String.format("%s\\%s", folderName.getText(), filename);
        Downloader.downloadFile(builder.buildURL(), absolutePath);

        stage.close();
    }

    private boolean areAllFieldsFilled() {
        if (!APIChoice.getSelectionModel().isEmpty() && !function.getSelectionModel().isEmpty() &&
                !extension.getSelectionModel().isEmpty() && !symbol.getText().equals("") && !folderName.toString().equals("")) {
            if (!toSymbol.isDisabled() && toSymbol.getText().equals("")) {
                return false;
            }
            return true;
        } return false;
    }
}
