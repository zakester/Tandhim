/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author DELL
 */
public class BonNotification extends Bon {

    ArrayList<Obligatoire> obligatoire = new ArrayList<Obligatoire>();
    String num_bon;
    public BonNotification(String num_bon, int prix) {
        super(num_bon, prix);
        this.num_bon=num_bon;
    }

    public ArrayList<Obligatoire> getObligatoire() {
        Connection bd = BDConnection.getConnection();

        String query = "SELECT nom,addr,status,date,en_suspens FROM obligatoire WHERE id_bon='" + num_bon + "'";
        Statement st;
        ResultSet rs;
        try {

            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("status " + rs.getString("status"));
                Obligatoire obl = new Obligatoire(rs.getString("nom"), rs.getString("addr"), num_bon, rs.getString("status"), rs.getString("date"), rs.getInt("en_suspens"));
                obligatoire.add(obl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obligatoire;
    }

    public void addObligatoire(Obligatoire o) {
        obligatoire.add(o);
    }
}
