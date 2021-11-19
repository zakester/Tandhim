/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author DELL
 */
public class JugementController implements Initializable {

    public String getNumTable() {
        return numTable.getText();
    }

    public String getNumIndice() {
        return numIndice.getText();
    }

    public String getDateForme() {
        return dateForme.getValue().toString();
    }

    public String getComTypeForme() {
        return comTypeForme.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private TextField numTable;

    @FXML
    private TextField numIndice;
    
    @FXML
    public HBox hboxTypeJugement;
    @FXML
    public VBox vboxJugement;

    @FXML
    private DatePicker dateForme;

    @FXML
    private ComboBox<String> comTypeForme;

    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setComTypeForme(String s) {
        comTypeForme.getItems().addAll(s);
        comTypeForme.getSelectionModel().selectFirst();
        comTypeForme.setEditable(false);
    }
}
