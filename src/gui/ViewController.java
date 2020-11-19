package gui;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import model.entities.Person;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    @FXML
    private TextField txtNumber1;

    @FXML
    private TextField txtNumber2;

    @FXML
    private Label labelResult;

    @FXML
    private Button btSum;

    @FXML
    private ComboBox<Person> comboBoxPerson;

    @FXML
    private Button btAll;

    private ObservableList<Person> observableList;

    @FXML
    public void onBtSumAction() {

        try {
            double number1 = Double.parseDouble(txtNumber1.getText());
            double number2 = Double.parseDouble(txtNumber2.getText());
            double sum = number1 + number2;
            labelResult.setText(String.format("%.2f", sum));
        } catch (NumberFormatException e) {
            Alerts.showAlert("Error", "Parse Error", e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    public void onComboBoxPersonAction() {
        Person person = comboBoxPerson.getSelectionModel().getSelectedItem();
        System.out.println(person);
    }

    @FXML
    public void onBtAllAction() {
        for (Person person : comboBoxPerson.getItems()) {
            System.out.println(person);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Constraints.setTextFieldDouble(txtNumber1);
        Constraints.setTextFieldDouble(txtNumber2);
        Constraints.setTextFieldMaxLength(txtNumber1, 12);
        Constraints.setTextFieldMaxLength(txtNumber2, 12);

        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "Luiz", "luiz@email.com"));
        list.add(new Person(2, "Maria", "maria@email.com"));
        list.add(new Person(3, "Ana", "ana@email.com"));

        observableList = FXCollections.observableArrayList(list);
        comboBoxPerson.setItems(observableList);

        Callback<ListView<Person>, ListCell<Person>> factory = personListView -> new ListCell<Person>() {
            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);
                setText(empty ? "" : person.getName());
            }
        };
        comboBoxPerson.setCellFactory(factory);
        comboBoxPerson.setButtonCell(factory.call(null));
    }
}
