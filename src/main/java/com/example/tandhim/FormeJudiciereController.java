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

import java.io.IOException;

/**
 *
 * @author DELL
 */
public class FormeJudiciereController implements Initializable {

    @FXML
    private TextField numFormeExe;
    
    @FXML
    private ComboBox<String> typeFormeExe;
    Controller ctrl;

    public String getNumForme() {
        return numFormeExe.getText();
    }
    
    public String getType() {
        return typeFormeExe.getSelectionModel().getSelectedItem();
    }
    
    public String getDateFomeExe() {
        return dateFomeExe.getValue().toString();
    }

    public void setTypeFormeExe(String s) {
        typeFormeExe.getSelectionModel().select(s);
    }
    @FXML
    private DatePicker dateFomeExe;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeFormeExe.getItems().addAll("حكم", "قرار", "أمر");
    }

    public void setParentCtrl(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public void typeFormeExeItemChanged() throws IOException {
        ctrl.typeFormeExeItemChanged1(typeFormeExe.getSelectionModel().getSelectedItem());
    }
    
}
