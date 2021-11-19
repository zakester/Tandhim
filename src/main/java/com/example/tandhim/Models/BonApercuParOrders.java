/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BonApercuParOrders extends Bon {

    public BonApercuParOrders(String num_order, String commission, String date_order, String num_bon, int prix) {
        super(num_bon, prix);
        this.num_order = num_order;
        this.commission = commission;
        this.date_order = date_order;
        this.somme = somme;
        this.prix = prix;
        this.num_bon = num_bon;
    }
    private String num_order, commission, date_order, num_bon;
    private int prix, somme;

    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_apercu_parorders`(`num_bon`, `prix`, `status`, `num_order`, `date_order`, `commission`, `somme`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setString(3, "");
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
            JOptionPane.showMessageDialog(null, "عليك ملئ جميع المعلومات لضمان تخزين الوصل");
        }

        return false;
    }

    public boolean update() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "UPDATE bon_apercu_parorders SET prix=" + prix + " somme=" + somme + "  num_order='" + num_order + "', date_order='" + date_order + "', commission='" + commission + "' WHERE num_bon='" + num_bon + "'";
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
