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
public class BonApercus extends Bon{

    public BonApercus(String num_bon, int prix) {
        super(num_bon, prix);
    }
    private int prix, somme;
    private String num_bon;

    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_apercus`(`num_bon`, `prix`, `status`,`somme`)  VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setString(3, "");
            preparedStmt.setInt(4, somme);
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
            String query = "UPDATE bon_apercus SET prix=" + prix + " , somme=" + somme + " WHERE num_bon='" + num_bon + "'";
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

    
}
