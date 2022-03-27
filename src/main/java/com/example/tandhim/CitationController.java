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
import javafx.util.converter.LocalDateStringConverter;

/**
 *
 * @author DELL
 */
public class CitationController implements Initializable{
    @FXML
    private TextField numCitation;

    @FXML
    private DatePicker dateCitation;

    @FXML
    private DatePicker dateReport;

    @FXML
    private DatePicker dateReport2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public String getNumCitation() {
        return numCitation.getText();
    }

    public void setNumCitation(String numCitation) {
        this.numCitation.setText(numCitation);
    }

    public void setDateCitation(String dateCitation) {
        this.dateCitation.setValue(LOCAL_DATE(dateCitation));
    }

    public void setDateReport(String dateReport) {
        if ((dateReport!=null)&&(!dateReport.equals("")))  this.dateReport.setValue(LOCAL_DATE(dateReport)); else this.dateReport.setValue(null);
    }

    public void setDateReport2(String dateReport2) {
        if ((dateReport2!=null)&&(!dateReport2.equals("")))  this.dateReport2.setValue(LOCAL_DATE(dateReport2)); else this.dateReport2.setValue(null);
    }

    public String getDateCitation() {
        return dateCitation.getValue().toString();
    }

    public String getDateReport() {
        return ((dateReport != null)&&(dateReport.getValue()!=null)) ? dateReport.getValue().toString() : null;    
    }

    public String getDateReport2() {
        return ((dateReport2 != null)&&(dateReport2.getValue()!=null)) ? dateReport2.getValue().toString() : null;
    }
    public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
    
}
