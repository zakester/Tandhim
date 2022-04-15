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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 *
 * @author DELL
 */
public class ActeController implements Initializable{

    @FXML
    private TextField numActe;

    @FXML
    private TextField nomNotaire;

    @FXML
    private TextField typeActe;

    public String getNumActe() {
        return numActe.getText();
    }

    public String getNomNotaire() {
        return nomNotaire.getText();
    }

    public String getTypeActe() {
        return typeActe.getText();
    }

    public String getDateActe() {
        return dateActe.getValue().toString();
    }

    public void setNumActe(String numActe) {
        this.numActe.setText(numActe);
    }

    public void setNomNotaire(String nomNotaire) {
        this.nomNotaire.setText(nomNotaire);
    }

    public void setTypeActe(String typeActe) {
        this.typeActe.setText(typeActe);
    }

    public void setDateActe(String dateActe) {
        this.dateActe.setValue(LOCAL_DATE(dateActe));
    }

    @FXML
    private DatePicker dateActe;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
}
