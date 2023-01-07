/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class BonAutres extends Bon {
    String num_bon, type_pv, joint_table, status, date_fin;
    int prix, somme;

    public BonAutres(String num_bon, String type_pv, String joint_table, int prix, int somme) {
        super(num_bon, prix);
        this.num_bon = num_bon;
        this.prix = prix;
        this.somme = somme;
        this.type_pv = type_pv;
        this.joint_table = joint_table;

    }

    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_autres`(`num_bon`, `joint_table`,`type_pv`, `prix`, `somme`)  VALUES (?,?,?,?,?);";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setString(2, joint_table);
            preparedStmt.setString(3, type_pv);
            preparedStmt.setInt(4, prix);
            preparedStmt.setInt(5, somme);
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
            String query = "UPDATE bon_autres SET  prix=" + prix + ", somme=" + somme + " WHERE num_bon='" + num_bon + "';";
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

    @Override
    public String getNum_bon() {
        return num_bon;
    }

    public String getType_pv() {
        return type_pv;
    }

    public String getJoint_table() {
        return joint_table;
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

    public String getStatus() {
        return status;
    }

    public void setType_pv(String type_pv) {
        this.type_pv = type_pv;
    }

    public void setJoint_table(String joint_table) {
        this.joint_table = joint_table;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }
}
