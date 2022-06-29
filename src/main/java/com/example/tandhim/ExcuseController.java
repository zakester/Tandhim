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
import javafx.scene.control.TextField;

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
        if (type.isVisible()) {
            String s = type.getText().replace("محضر","");
            s = s.replace("تبليغ","");
            return type.getText();
        }
        else return comTypeExcuse.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private ComboBox<String> comTypeExcuse;
    @FXML
    private TextField type;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        comTypeExcuse.getItems().addAll("إعذار", "رد على إعذار", "طلب", "رد على طلب", "استدعاء", "رد على استدعاء", "عرض وفاء","رد على عرض وفاء","ارسالية","رد على ارسالية","عقد عزل وكالة","صنف آخر");
        comTypeExcuse.getSelectionModel().selectFirst();
    }
    public void updateType(){
        if (comTypeExcuse.getSelectionModel().getSelectedItem().equals("صنف آخر")) type.setVisible(true); else type.setVisible(false);
    }
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
