/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 *
 * @author DELL
 */
public class ExcuseController implements Initializable{
    @FXML
    private DatePicker dateMarquage;

    public String getDateMarquage() {
        return dateMarquage.getValue().toString();
    }

    public void setDateMarquage(String dateMarquage) {
        this.dateMarquage.setValue(LOCAL_DATE(dateMarquage));
    }

    public void setComTypeExcuse(String comTypeExcuse) {
        this.comTypeExcuse.getSelectionModel().select(comTypeExcuse);
    }

    public String getComTypeExcuse() {
        return comTypeExcuse.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private ComboBox<String> comTypeExcuse;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        comTypeExcuse.getItems().addAll("إعذار", "رد على إعذار", "طلب", "رد على طلب", "استدعاء", "رد على استدعاء", "عرض وفاء","رد على عرض وفاء","ارسالية","رد على ارسالية");
        comTypeExcuse.getSelectionModel().selectFirst();
    }
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
