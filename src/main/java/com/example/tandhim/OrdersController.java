/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.net.URL;
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
    private ComboBox<String> comTypeOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comTypeOrder.getItems().addAll("أمر إستعجالي","أمر بإثبات حالة" ,"أمر بحجز تنفيذي على منقول","أمر بحجز تنفيذي على عقار","أمر بحجز تنفيذي على ما للمدين لدى الغير","أمر باستبدال خبير","أمر بالزيارة المؤقتة");    
        comTypeOrder.getSelectionModel().selectFirst();
    }
    public String getNumOrder() {
        return numOrder.getText();
    }

    public String getDateOrder() {
        return dateOrder.getValue().toString();
    }
    
    public String getComTypeOrder() {
        return comTypeOrder.getSelectionModel().getSelectedItem().toString();
    }
}
