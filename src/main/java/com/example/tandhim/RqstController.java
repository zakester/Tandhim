package com.example.tandhim;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void setNumRqst(String numRqst) {
        this.numRqst.setText(numRqst);
    }

    public void setDateRqst(String dateRqst) {
        this.dateRqst.setValue(LOCAL_DATE(dateRqst));
    }

    public void setTypeRqst(String typeRqst) {
        this.typeRqst.getSelectionModel().select(typeRqst);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeRqst.getItems().addAll("إفتتاحية", "إدخال في الخصومة", "إعتراض على إدخال الغير في الخصومة", "رجوع بعد خبرة", "استئناف");
    }
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
