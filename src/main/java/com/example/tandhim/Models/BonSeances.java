/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javax.swing.JOptionPane;

/*
restriction mane9edereche nekhedemeha fe editebon

 */
/**
 *
 * @author DELL
 */
public class BonSeances extends BonNotification {

    private String num_bon, num_seance, type, commission, date_seance, date_report, date_report2;
    private int prix, somme;

    public BonSeances(String num_seance, String type, String commission, String date_seance, String date_report, String date_report2, String num_bon, int prix, int somme) {
        super(num_bon, prix);
        this.num_seance = num_seance;
        this.type = type;
        this.prix = prix;
        this.somme = somme;
        this.num_bon = num_bon;
        this.commission = commission;
        this.date_seance = date_seance;
        this.date_report = date_report;
        this.date_report2 = date_report2;

    }

    public boolean validate() {
        String[] a = commission.split(" : ");
        if (a.length==1 || !ArabicChar(a[1])) {
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
            String query = "INSERT INTO `bon_seances`( `num_bon`, `prix`, `status`, `num_seance`, `type`, `commission`, `date_seance`, `date_report`, `date_report2`, `somme`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            String sDate = date_seance;
            java.sql.Date date1 = java.sql.Date.valueOf(sDate);
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setString(3, "");
            preparedStmt.setString(4, num_seance);
            preparedStmt.setString(5, type);
            preparedStmt.setString(6, commission);
            preparedStmt.setDate(7, date1);
            sDate = date_report;
            date1 = java.sql.Date.valueOf(sDate);
            preparedStmt.setDate(8, date1);
            sDate = date_report2;
            date1 = java.sql.Date.valueOf(sDate);
            preparedStmt.setDate(9, date1);
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
            String query = "UPDATE bon_seances SET prix = " + prix + ", somme = " + somme + ", num_seance ='" + num_seance + "',type ='" + type + "', commission='" + commission + "',date_seance='" + date_seance + "',date_report='" + date_report + "',date_report2='" + date_report2 + "' WHERE num_bon='" + num_bon + "'";
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

    public String getNum_seance() {
        return num_seance;
    }

    public void setNum_seance(String num_seance) {
        this.num_seance = num_seance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(String date_seance) {
        this.date_seance = date_seance;
    }

    public String getDate_report() {
        return (date_report!=null)? date_report :"";
    }

    public void setDate_report(String date_report) {
        this.date_report = date_report;
    }

    public String getDate_report2() {
        return (date_report2!=null)? date_report2 :"";
    }

    public void setDate_report2(String date_report2) {
        this.date_report2 = date_report2;
    }
    public void PrintBon() {
        String typeService ="تكليف بالحضور لجلسة يوم " + date_seance + " والمؤجلة ليوم: "+date_report+" والمؤجلة ليوم: "+date_report2+" رقم: " + num_seance+ " ب: " + commission;
        impression imp= new impression();
        String demandeur="";
        String oblig="";
        for (Demandeur d:getDemandeur()) {
            demandeur=demandeur+"،"+d.getNom();
        }
        for (Obligatoire o:getObligatoire()) {
            oblig=oblig+"،"+o.getNom();
        }
        System.out.println(demandeur+" ljsdnlsdn "+ oblig);
        try {
            typeService =typeService.replace("null", "///");
            imp.PrintBon(demandeur, oblig, typeService, prix, num_bon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
