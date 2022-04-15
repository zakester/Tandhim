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

public class MandatController implements Initializable {

    @FXML
    private TextField numMandat;

    @FXML
    private DatePicker dateMandat;

    @FXML
    private TextField Service;

    @FXML
    private ComboBox<String> typeMandat;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeMandat.getItems().addAll("طعن بالنقض", "طعن في الطعن بالنقض", "جوابية", "تفسيرية", "تدعيمية");
    }

    public String getNumMandat() {
        return numMandat.getText();
    }

    public String getDateMandat() {
        return dateMandat.getValue().toString();
    }

    public String getService() {
        return Service.getText();
    }

    public String getTypeMandat() {
        return typeMandat.getSelectionModel().getSelectedItem();
    }

    public void setNumMandat(String numMandat) {
        this.numMandat.setText(numMandat);
    }

    public void setDateMandat(String dateMandat) {
        this.dateMandat.setValue(LOCAL_DATE(dateMandat));
    }

    public void setService(String service) {
        Service.setText(service);
    }

    public void setTypeMandat(String typeMandat) {
        this.typeMandat.getSelectionModel().select(typeMandat);
    }
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
