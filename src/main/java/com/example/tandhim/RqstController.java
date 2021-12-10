package com.example.tandhim;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RqstController implements Initializable {

    @FXML
    private TextField numRqst;

    @FXML
    private DatePicker dateRqst;

    @FXML
    private ComboBox<String> typeRqst;

    public String getNumRqst() {
        return numRqst.getText();
    }

    public String getDateRqst() {
        return dateRqst.getValue().toString();
    }

    public String getTypeRqst() {
        return typeRqst.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeRqst.getItems().addAll("إفتتاحية", "إدخال في الخصومة", "إعتراض على إدخال الغير في الخصومة", "رجوع بعد خبرة", "استئناف");
    }
}
