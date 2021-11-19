/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author DELL
 */
public class NotifCtrl implements Initializable {

    @FXML
    private Label numBon;
    @FXML
    private Label type;
    @FXML
    private Label oblig;
    @FXML
    private Label notif;
    @FXML
    private Label proced;
    @FXML
    private ComboBox comCommission;
    @FXML
    private ComboBox comChambre;
    
    public void initialize(URL location, ResourceBundle resources) {
    }

    void initData(String num_bon, String type, String oblig, String notif, String proced) {
        this.type.setText(type);
        this.numBon.setText(num_bon);
        this.oblig.setText(oblig);
        this.notif.setText(notif);
        this.proced.setText(proced);
    }

}
