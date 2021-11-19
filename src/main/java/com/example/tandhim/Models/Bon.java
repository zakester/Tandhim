/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class Bon {

    private String num_bon;

    private int prix;
    private String date_fin, status;
    ArrayList<Demandeur> demandeur = new ArrayList<Demandeur>();

    public Bon(String num_bon, int prix) {
        this.num_bon = num_bon;
        this.prix = prix;
    }

    public String getNum_bon() {
        return num_bon;
    }

    public void setNum_bon(String num_bon) {
        this.num_bon = num_bon;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Demandeur> getDemandeur() {
        Connection bd = BDConnection.getConnection();
        String query = "SELECT nom,addr FROM demandeur WHERE id_bon='" + num_bon + "'";
        Statement st;
        ResultSet rs;
        try {

            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Demandeur dem = new Demandeur(rs.getString("nom"), rs.getString("addr"), num_bon);
                demandeur.add(dem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return demandeur;
    }

    public void addDemandeur(Demandeur o) {
        demandeur.add(o);
    }
}