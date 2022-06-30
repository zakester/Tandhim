/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import com.example.tandhim.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.io.UnsupportedEncodingException;
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
public class BonRqst extends BonNotification{
    public int getSomme() {
        return somme;
    }

    private String num_rqst,type,commission,date,num_bon; int prix,somme;

    public BonRqst(String num_rqst, String type, String commission, String date, String num_bon, int prix, int somme) {
        super(num_bon, prix);
        this.num_bon=num_bon;
        this.num_rqst = num_rqst;
        this.type = type;
        this.commission = commission;
        this.date = date;
        this.prix=prix;
        this.somme=somme;
    }
    public boolean insert(){
        try {
                    Connection bd = BDConnection.getConnection();
                    String query = "INSERT INTO `bon_rqst`( `num_bon`, `prix`, `num_rqst`, `type`, `commission`, `date`, `somme`) VALUES (?,?,?,?,?,?,?);CALL log_insert("+ Controller.getUserID()+",'bon_rqst','"+num_bon+"',' ');";
                    String sDate = date;
                    java.sql.Date date1 = java.sql.Date.valueOf(sDate);
                    PreparedStatement preparedStmt = bd.prepareStatement(query);
                    preparedStmt.setString(1, num_bon);
                    preparedStmt.setInt(2,prix);
                    preparedStmt.setString(3, num_rqst);
                    preparedStmt.setString(4, type);
                    preparedStmt.setString(5, commission);
                    preparedStmt.setDate(6, date1);
                    preparedStmt.setInt(7,somme);
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
                    "تاريخ العريضة غير مدرج");
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
            String query = "UPDATE bon_rqst SET prix = " +prix+ " ,somme= " +somme+ ", last_updated = " + 1 + ", num_rqst ='" + num_rqst + "',type ='" + type + "', commission='" + commission +  "', date='" + date + "' WHERE num_bon='" + num_bon + "';CALL log_update("+ Controller.getUserID()+",'bon_rqst','"+num_bon+"',' ');";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int id = preparedStmt.executeUpdate();
            if (id>=1){return true;}
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
      
    return false;}
    public void setNum_rqst(String num_rqst) {
        this.num_rqst = num_rqst;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNum_bon(String num_bon) {
        this.num_bon = num_bon;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNum_rqst() {
        return num_rqst;
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

    public String getNum_bon() {
        return num_bon;
    }

    public int getPrix() {
        return prix;
    }
    

}
