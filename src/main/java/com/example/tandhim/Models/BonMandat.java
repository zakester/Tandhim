/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BonMandat extends BonNotification{
    public String getNum_mandat() {
        return num_mandat;
    }

    public String getType() {
        return type;
    }

    public String getCommission() {
        return commission;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String getNum_bon() {
        return num_bon;
    }

    public String getService() {
        return service;
    }

    @Override
    public int getPrix() {
        return prix;
    }

    public int getSomme() {
        return somme;
    }

    private String num_mandat,type,commission,date,num_bon,service; int prix,somme;

    public BonMandat(String num_mandat, String type,String service, String commission, String date, String num_bon, int prix,int somme) {
        super(num_bon, prix);
        this.service=service;
        this.num_bon=num_bon;
        this.num_mandat = num_mandat;
        this.type = type;
        this.commission = commission;
        this.date = date;
        this.prix=prix;
        this.somme=somme;
    }
    public boolean insert(){
        try {
                    Connection bd = BDConnection.getConnection();
                    String query = "INSERT INTO `bon_mandat`( `num_bon`, `prix`, `num_mandat`, `type`, `commission`, `date`,`service`,`somme`) VALUES (?,?,?,?,?,?,?,?)";
                    String sDate = date;
                    java.sql.Date date1 = java.sql.Date.valueOf(sDate);
                    PreparedStatement preparedStmt = bd.prepareStatement(query);
                    preparedStmt.setString(1, num_bon);
                    preparedStmt.setInt(2,prix);
                    preparedStmt.setString(3, num_mandat);
                    preparedStmt.setString(4, type);
                    preparedStmt.setString(5, commission);
                    preparedStmt.setDate(6, date1);
                    preparedStmt.setString(7, service);
                    preparedStmt.setInt(8,somme);

                    int id = preparedStmt.executeUpdate();
                      if (id>=1){return true;}
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
      
    return false;}
    public boolean validate() {
        String[] a = commission.split(" : ");
        if (a.length == 1 || !ArabicChar(a[1])) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "اسم الهيئة خاطئ");
            alert.showAndWait();
            return false;
        }if (date==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "تاريخ المذكرة غير مدرج");
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
        public boolean update(){
        try {
            Connection bd = BDConnection.getConnection();
            String query = "UPDATE bon_mandat SET prix = " +prix+ ",somme = " +somme+ ", last_apdated = " + 1 + ", num_mandat ='" + num_mandat + "',type ='" + type + "', commission='" + commission +  "', service='" + service +  "',date='" + date + "' WHERE num_bon='" + num_bon + "'";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int id = preparedStmt.executeUpdate();
            if (id>=1){return true;}
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "عليك ملئ جميع المعلومات لضمان تخزين الوصل");
                }
      
    return false;}
    
}
