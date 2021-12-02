/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BonProvisions extends BonNotification {

    private String num_indice, num_table, date, commission, type, num_bon, spec;
    private int prix, somme;

    public BonProvisions(String num_indice, String num_table, String date, String commission, String type, String spec, String num_bon, int prix, int somme) {
        super(num_bon, prix);
        this.num_indice = num_indice;
        this.num_table = num_table;
        this.date = date;
        this.commission = commission;
        this.type = type;
        this.spec = spec;
        this.num_bon = num_bon;
        this.prix = prix;
        this.somme = somme;
    }

    public boolean validate() {
        String[] a = commission.split(" : ");
        System.out.println(a);
        if (a.length == 1 || !ArabicChar(a[1])) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("../home/style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "اسم الهيئة خاطئ");
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

    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_provisions`(`num_bon`, `prix`, `status`,  `num_indice`, `num_table`, `date`, `commission`, `type`,`spec`,`somme`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            String sDate = date;
            java.sql.Date date1 = java.sql.Date.valueOf(sDate);
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setString(3, "");
            preparedStmt.setString(4, num_indice);
            preparedStmt.setString(5, num_table);
            preparedStmt.setDate(6, date1);
            preparedStmt.setString(7, commission);
            preparedStmt.setString(8, type);
            preparedStmt.setString(9, spec);
            preparedStmt.setInt(10, somme);

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
            String query = "UPDATE bon_provision SET prix = " + prix + ", somme = " + somme + ", num_indice ='" + num_indice + "',num_table ='" + num_table + "', commission='" + commission + "',date='" + date + "',type='" + type + "' , spec='" + spec + "' WHERE num_bon='" + num_bon + "'";
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
    public int getSomme(){ return somme;}

    public String getNum_indice() {
        return num_indice;
    }

    public void setNum_indice(String num_indice) {
        this.num_indice = num_indice;
    }

    public String getNum_table() {
        return num_table;
    }

    public void setNum_table(String num_table) {
        this.num_table = num_table;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addNotificationFidelité(String num, String date) {
        NotificationFidelite notif = new NotificationFidelite(num, date, num_bon);
        notif.insert();
    }

    public NotificationFidelite getNotificationFidelité(boolean b) {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "SELECT  num, date FROM `notification_fidelité` WHERE id_provision = ?";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            Statement st = bd.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                b=true;
                return new NotificationFidelite(rs.getString("num"), rs.getString("date"), num_bon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BonProvisions.class.getName()).log(Level.SEVERE, null, ex);
        }
           b=false;
           return null;
     }

}
