/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BonExcuses extends BonNotification {

    private String date_marquage, num_bon, type;
    private int somme, prix;

    public BonExcuses(String date_marquage, String num_bon, String type, int prix, int somme) {
        super(num_bon, prix);
        this.date_marquage = date_marquage;
        this.num_bon = num_bon;
        this.type = type;
        this.prix = prix;
        this.somme = somme;
    }
    public boolean validate() {
        if (date_marquage==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("../home/style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "تاريخ التأشير غير مدرج");
            alert.showAndWait();
            return false;
        }
        
        
        return true;
    }

    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_excuses`(`num_bon`, `prix`, `status`, `date_marquage`, `type`, `somme`)  VALUES (?,?,?,?,?,?)";

            java.sql.Date date = java.sql.Date.valueOf(date_marquage);

            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setString(3, "");
            preparedStmt.setDate(4, date);
            preparedStmt.setString(5, type);
            preparedStmt.setInt(6, somme);
            int id = preparedStmt.executeUpdate();
            if (id >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "عليك ملئ جميع المعلومات لضمان تخزين الوصل");
        }

        return false;
    }

    public boolean update() {
        try {
            Connection bd = BDConnection.getConnection();
                    String query = "UPDATE bon_excuses SET prix=" + prix + ", somme=" + somme + ",  date_marquage='" + date_marquage + "' ,  type='" + type + "' WHERE num_bon='" + num_bon + "'";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int id = preparedStmt.executeUpdate();
            if (id >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "عليك ملئ جميع المعلومات لضمان تخزين الوصل");
        }

        return false;
    }

    public String getDate_marquage() {
        return date_marquage;
    }

    public void setDate_marquage(String date_marquage) {
        this.date_marquage = date_marquage;
    }

}
