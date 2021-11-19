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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class EditBonSearch {

    String id;
    String Service, ProvisionsType;
    boolean NotificationFidelité = false;
    Connection bd = BDConnection.getConnection();

    public EditBonSearch(String input) {
        id = input;
        Statement st, st1;
        ResultSet rs, rs1;
        String s = "";
        String[] serv = {"bon_seances", "bon_provisions", "bon_orders", "bon_excuses", "bon_autres", "bon_associations", "bon_apercu_parorders", "bon_apercus", "bon_mandat", "bon_rqst"};
        for (int i = 0; i < serv.length; i++) {
            String q = "SELECT id FROM " + serv[i] + " WHERE num_bon='" + input + "'";
            System.out.println(q);
            try {
                PreparedStatement preparedStmt = bd.prepareStatement(q);
                st = bd.createStatement();
                rs = st.executeQuery(q);
                while (rs.next()) {
                    System.out.println(serv[i]);
                    Service = serv[i];
                    break;
                }
                if (Service.equals("bon_provisions")) {
                    q = "SELECT type FROM bon_provisions WHERE num_bon='" + input + "'";
                    preparedStmt = bd.prepareStatement(q);
                    st = bd.createStatement();
                    rs = st.executeQuery(q);
                    while (rs.next()) {
                        if (!rs.getString("type").equals("حكم")) {
                            ProvisionsType = "قرار";
                        } else if (!rs.getString("type").equals("قرار")) {
                            ProvisionsType = "حكم";
                        }

                    }
                    q = "SELECT id FROM notification_fidelité WHERE id_provision='" + input + "'";

                    st1 = bd.createStatement();
                    rs1 = st1.executeQuery(q);
                    while (rs1.next()) {
                        NotificationFidelité = true;
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public BonSeances getBonData() {
        if (Service.equals("bon_seances")) {
            System.out.println(Service);
            String query = "SELECT prix,num_seance,type,commission,date_seance,date_report,date_report2,somme  FROM bon_seances WHERE num_bon='" + id + "'";
            Statement st;
            ResultSet rs;
            try {
                st = bd.createStatement();
                rs = st.executeQuery(query);

                while (rs.next()) {
                    System.out.println("yes it found it");
                    BonSeances bon = new BonSeances(rs.getString("num_seance"), rs.getString("type"), rs.getString("commission"), rs.getString("date_seance"), rs.getString("date_report"), rs.getString("date_report2"), id, rs.getInt("prix"), rs.getInt("somme"));
                    return bon;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*if (Service.equals("bon_orders")) {
            String query = "SELECT num_order,date_order,commission,prix,somme FROM bon_orders WHERE num_bon='" + id + "'";
            Statement st;
            ResultSet rs;
            try {
                st = bd.createStatement();
                rs = st.executeQuery(query);

                while (rs.next()) {
                    BonOrders bon = new BonOrders(rs.getString("num_order"), rs.getString("date_order"), rs.getString("commission"), rs.getString("num_bon"), rs.getInt("prix"),rs.getInt("somme"));
                    return bon;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        return null;
    }

    public ArrayList<Demandeur> getDemandeurList() {
        String query = "SELECT nom,addr FROM demandeur WHERE id_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        ArrayList<Demandeur> a = new ArrayList();
        try {

            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                Demandeur dem = new Demandeur(rs.getString("nom"), rs.getString("addr"), id);
                a.add(dem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public String getService() {
        return Service;
    }

    public String getProvisionsType() {
        return ProvisionsType;
    }

    public boolean isNotificationFidelité() {
        return NotificationFidelité;
    }

    public ArrayList<Obligatoire> getObligatoireList() {
        String query = "SELECT nom,addr,status,date,en_suspens FROM obligatoire WHERE id_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        ArrayList<Obligatoire> a = new ArrayList();
        try {

            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("status "+rs.getString("status"));
                Obligatoire obl = new Obligatoire(rs.getString("nom"), rs.getString("addr"), id, rs.getString("status"), rs.getString("date"), rs.getInt("en_suspens"));
                a.add(obl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

}
