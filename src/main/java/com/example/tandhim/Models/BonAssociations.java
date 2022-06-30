/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;


import com.example.tandhim.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BonAssociations extends Bon {

    @Override
    public int getPrix() {
        return prix;
    }

    public int getSomme() {
        return somme;
    }

    @Override
    public String getNum_bon() {
        return num_bon;
    }

    @Override
    public String getStatus() {
        if ((status==null)||(status.equals("غير منجزة")))
            return "غير منجزة";
        else
            return status;
    }

    @Override
    public String getDate_fin() {
        return date_fin;
    }

    public BonAssociations(String num_bon, int prix, int somme) {
        super(num_bon, prix);
        this.prix = prix;
        this.somme = somme;
        this.num_bon = num_bon;
    }

    @Override
    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    @Override
    public void setNum_bon(String num_bon) {
        this.num_bon = num_bon;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateSQL() {
        return (date_fin!=null)? "'"+date_fin+"'":null;
    }

    @Override
    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    private int prix, somme;
    private String num_bon,status,date_fin;

    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `bon_associations`(`num_bon`, `prix`, `status`,`somme`)  VALUES (?,?,?,?);CALL log_insert("+ Controller.getUserID()+",'bon_associations','"+num_bon+"',' ');";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setInt(2, prix);
            preparedStmt.setString(3, "غير منجزة");
            preparedStmt.setInt(4, somme);
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
            String query = "UPDATE bon_associations SET prix=" + prix + " ,status='" + status + "', date_fin=" + getDateSQL() + ", somme=" + somme + ", last_updated = " + 1 + " WHERE num_bon='" + num_bon + "';CALL log_update("+ Controller.getUserID()+",'bon_associations','"+num_bon+"',' ');";
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

}
