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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author DELL
 */
public class OrdersController implements Initializable{
    @FXML
    private TextField numOrder;

    @FXML
    private DatePicker dateOrder;
    @FXML
    public VBox vboxOrders;
    @FXML
    public HBox hboxTypeOrder;

    @FXML
    public ComboBox<String> comTypeOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comTypeOrder.getItems().addAll("أمر إستعجالي","أمر بإثبات حالة" ,"أمر بحجز تنفيذي على منقول","أمر بحجز تنفيذي على عقار","أمر بحجز تنفيذي على ما للمدين لدى الغير","أمر باستبدال خبير","أمر بالزيارة المؤقتة","أمر باستجواب" ,"أمر أداء");
        comTypeOrder.getSelectionModel().selectFirst();
    }
    public void setContatOrder(){
        comTypeOrder.getSelectionModel().select("أمر بإثبات حالة");
        comTypeOrder.setDisable(true);
    }
    public void setSaisieOrderMob(){
        comTypeOrder.getSelectionModel().select("أمر بحجز تنفيذي على منقول");
        comTypeOrder.setDisable(true);
    }
    public void setSaisieOrderImmob(){
        comTypeOrder.getSelectionModel().select("أمر بحجز تنفيذي على عقار");
        comTypeOrder.setDisable(true);
    }
    public void setSaisieOrderCpt(){
        comTypeOrder.getSelectionModel().select("أمر بحجز تنفيذي على ما للمدين لدى الغير");
        comTypeOrder.setDisable(true);
    }
    public String getNumOrder() {
        return numOrder.getText();
    }

    public void setNumOrder(String numOrder) {
        this.numOrder.setText(numOrder);
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder.setValue(LOCAL_DATE(dateOrder));
    }

    public void setComTypeOrder(String comTypeOrder) {
        this.comTypeOrder.getSelectionModel().select(comTypeOrder);
    }

    public String getDateOrder() {
        return dateOrder.getValue().toString();
    }
    
    public String getComTypeOrder() {
        return comTypeOrder.getSelectionModel().getSelectedItem().toString();
    }
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}

