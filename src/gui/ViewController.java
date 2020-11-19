package gui;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ViewController {

    @FXML
    private Button btTest;

    @FXML
    public void onBtTestAction() {
        Alerts.showAlert("Alert Title", "Alert Header", "My Message", Alert.AlertType.INFORMATION);
    }
}
