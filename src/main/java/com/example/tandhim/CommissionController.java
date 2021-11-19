/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
        comCommission.getItems().addAll("محكمة", "مجلس قضاء", "المحكمة الإدارية");
        comType.getItems().addAll("القسم المدني", "قسم الجنح", "قسم المخالفات", "القسم الاستعجالي", "قسم شؤون الأسرة", "قسم الأحداث", "القسم الاجتماعي", "القسم العقاري", "القسم البحري", "القسم التجاري");
    }
}
