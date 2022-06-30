/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import com.example.tandhim.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BonApercuParOrders extends Bon {

    public BonApercuParOrders(String num_order, String commission, String date_order, String num_bon, int prix, int somme) {
        super(num_bon, prix);
        this.num_order = num_order;
        this.commission = commission;
        this.date_order = date_order;
        this.somme = somme;
        this.prix = prix;
        this.num_bon = num_bon;
    }

    @Override
    public void setNum_bon(String num_bon) {
        this.num_bon = num_bon;
    }
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
        }if (date_order==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
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

    @Override
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }
    public String getDateSQL() {
        return (date_fin!=null)? "'"+date_fin+"'":null;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = ""+date_fin;
        System.out.println("here in the function "+this.date_fin);
    }

    public void setStatus(String status) {
        this.status = status;
        System.out.println("here is status = "+status);
    }

    public String num_order, commission, date_order, num_bon,date_fin="",status="";
    private int prix, somme;

    @Override
    public String getNum_bon() {
        return num_bon;
    }

    @Override
    public int getPrix() {
        return prix;
    }

    public int getSomme() {
        return somme;
    }

    @Override
    public String getDate_fin() {
        return date_fin;
    }

    @Override
    public String getStatus() {
        if ((status==null)||(status.equals("غير منجزة")))
            return "غير منجزة";
        else
            return status;
    }

    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_apercu_parorders`(`num_bon`, `prix`, `status`, `num_order`, `date_order`, `commission`, `somme`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setString(3, "غير منجزة");
            preparedStmt.setString(4, num_order);
            java.sql.Date date = java.sql.Date.valueOf(date_order);
            preparedStmt.setDate(5, date);
            preparedStmt.setString(6, commission);
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
            String query = "UPDATE bon_apercu_parorders SET prix=" + prix + ", somme=" + somme + ", last_apdated = " + 1 + ",  num_order='" + num_order + "', date_order='" + date_order + "' , status='" + status + "', date_fin=" + getDateSQL() + ", commission='" + commission + "' WHERE num_bon='" + num_bon + "';log_update("+ Controller.getUserID()+",'bon_apercu_par_order','"+num_bon+"');";
            System.out.println(query);
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

    public String getNum_order() {
        return num_order;
    }

    public void setNum_order(String num_order) {
        this.num_order = num_order;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

}
