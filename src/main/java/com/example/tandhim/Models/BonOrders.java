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
public class BonOrders extends BonNotification {

    private String num_order,type, date_order, commission, num_bon;
    private int prix, somme;

    public BonOrders(String num_order, String date_order, String commission,String type, String num_bon, int prix, int somme) {
        super(num_bon, prix);
        this.num_order = num_order;
        this.date_order = date_order;
        this.commission = commission;
        this.num_bon = num_bon;
        this.prix = prix;
        this.somme = somme;
        this.type = type;
    }

    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_orders`(`num_bon`, `prix`, `status`, `num_order`, `date_order`, `commission`, `somme`,`type`)  VALUES (?,?,?,?,?,?,?,?)";
            String sDate = date_order;
            java.sql.Date date1 = java.sql.Date.valueOf(sDate);
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setString(3, "");
            preparedStmt.setString(4, num_order);
            preparedStmt.setDate(5, date1);
            preparedStmt.setString(6, commission);
            preparedStmt.setInt(7, somme);
            preparedStmt.setString(8, type);
            int id = preparedStmt.executeUpdate();
            if (id >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean validate() {
        String[] a = commission.split(" : ");
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
        }if (date_order==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("../home/style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "تاريخ الأمر غير مدرج");
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

    public boolean update() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "UPDATE bon_orders SET prix=" + prix + ", somme=" + somme + ", num_order='" + num_order + "', date_order='" + date_order + "', commission='" + commission + "' WHERE num_bon='" + num_bon + "'";
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
    public String getNum_order() {
        return num_order;
    }

    public void setNum_order(String num_order) {
        this.num_order = num_order;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

}
