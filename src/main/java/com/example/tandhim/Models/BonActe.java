/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.tandhim.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BonActe extends BonNotification{
    private String num_bon,num,nomNotaire,type,date;
    private int prix,somme,last_Apdated;
    public BonActe (String num_bon,String num,String nomNotaire,String type,String date, int prix, int somme) {
    super(num_bon, prix);
    this.num= num;
    this.num_bon= num_bon;
    this.nomNotaire= nomNotaire;
    this.type= type;
    this.date= date;
    this.prix= prix;
    this.somme= somme;
    }
    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_acte`(`num_bon`, `prix`, `date`, `type_acte`,`num`,`nom_notaire`,`somme`) VALUES (?,?,?,?,?,?,?);CALL log_insert("+ Controller.getUserID()+",'bon_acte','"+num_bon+"',' ');";
            String sDate = date;
            java.sql.Date date1 = java.sql.Date.valueOf(sDate);
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setDate(3, date1);
            preparedStmt.setString(4, type);
            preparedStmt.setString(5, num);
            preparedStmt.setString(6, nomNotaire);
            preparedStmt.setInt(7, somme);
            int id = preparedStmt.executeUpdate();
            if (id >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean update() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "UPDATE bon_acte SET prix = " + prix + ", somme = " + somme + ", last_updated = " + 1 + ", num ='" + num + "', date='" + date + "',type_acte='" + type + "' , nom_notaire='" + nomNotaire + "' WHERE num_bon='" + num_bon + "';CALL log_update("+ Controller.getUserID()+",'bon_acte','"+num_bon+"',' ');";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int id = preparedStmt.executeUpdate();
            if (id >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public String getNum() {
        return num;
    }

    public String getNomNotaire() {
        return nomNotaire;
    }

    public String getType() {
        return type;
    }

    @Override
    public int getPrix() {
        return prix;
    }

    public int getSomme() {
        return somme;
    }

    public String getDate() {
        return date;
    }

    public boolean validate() {
        if (!ArabicChar(nomNotaire)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "اسم الموثق باللغة العربية");
            alert.showAndWait();
            return false;
        }
        if (!ArabicChar(type)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "نوع العقد باللغة العربية");
            alert.showAndWait();
            return false;
        }
        if ((date==null)||(date.equals(""))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("../home/style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "يجب إدراج تاريخ العقد");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean ArabicChar(String ar) {
        char[] cs = ar.toCharArray();
        String s = "ابتجحخدذرزسشصضطظعغفقكلمنهوي ةىءئؤأآإ";
        for (Character c : cs) {
            if (!s.contains(c.toString())) {
                return false;
            }
        }
        return true;
    }


}
