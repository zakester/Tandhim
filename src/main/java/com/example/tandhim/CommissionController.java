/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

/**
 *
 * @author DELL
 */
public class CommissionController implements Initializable {

    public String getComNomCommission() {
        return comNomCommission.getText();
    }

    public String getComCommission() {
        return comCommission.getSelectionModel().getSelectedItem();
    }

    public String getComType() {
        return comType.getSelectionModel().getSelectedItem();
    }

    public void setComType(String type) {
        comType.getSelectionModel().select(type);
    }

    public void setCom_NomCommission(String item) {
        String com[] = item.split(" : ");
        comCommission.getSelectionModel().select(com[0]);
        comNomCommission.setText(com[1]);
    }

    @FXML
    private TextField comNomCommission;

    @FXML
    private ComboBox<String> comCommission, comType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comCommission.getItems().addAll("محكمة", "مجلس قضاء", "المحكمة الإدارية", "مجلس الدولة", "المحكمة العليا");
        comCommission.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if ((newValue != null) || (!newValue.equals(oldValue))) {
                    if (newValue.equals("محكمة")){
                        comType.setDisable(false);
                        comType.getItems().clear();
                        comType.getItems().addAll("القسم المدني", "قسم الجنح", "قسم المخالفات", "القسم الاستعجالي", "قسم شؤون الأسرة", "قسم الأحداث", "القسم الاجتماعي", "القسم العقاري", "القسم التجاري/البحري");
                    }else if (newValue.equals("مجلس قضاء")||newValue.equals("المحكمة العليا")) {
                        comType.setDisable(false);
                        comType.getItems().clear();
                        comType.getItems().addAll("الغرفة المدنية", "الغرفة الجزائية", "الغرفة الاستعجالية", "غرفة شؤون الأسرة", "غرفة الإتهام", "الغرفة الاجتماعية", "الغرفة العقارية","غرفة الأحداث", "الغرفة التجارية/البحرية");
                    }else {
                        comType.getItems().clear();
                        comType.setDisable(true);
                    }
                }
            }

        });
    }

}
