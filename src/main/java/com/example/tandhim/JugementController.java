/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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

    public void setNumTable(String numTable) {
        this.numTable.setText(numTable);
    }

    public void setNumIndice(String numIndice) {
        this.numTable.setText(numIndice);
    }

    public void setComTypeForme(ObservableList ComTypeForme,int selectedIndex) {
        this.comTypeForme.getItems().addAll(ComTypeForme);
        this.comTypeForme.getSelectionModel().select(selectedIndex);
    }

    public void setDateForme(String dateForme) {
        this.dateForme.setValue(LOCAL_DATE(dateForme));
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

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
