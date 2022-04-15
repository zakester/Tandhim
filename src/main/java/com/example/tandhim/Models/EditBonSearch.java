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
    String Service="", ProvisionsType;
    boolean NotificationFidelité = false;
    Connection bd = BDConnection.getConnection();

    public EditBonSearch(String input) {
        id = input;
        Statement st, st1;
        ResultSet rs, rs1;
        String s = "";
        String[] serv = {"bon_seances", "bon_provisions", "bon_orders", "bon_excuses", "bon_autres", "bon_associations", "bon_apercu_parorders", "bon_apercus", "bon_mandat", "bon_rqst","bon_acte"};
        for (int i = 0; i < serv.length; i++) {
            System.out.println(i);
            String q = "SELECT id FROM " + serv[i] + " WHERE num_bon='" + input + "'";
            System.out.println(q);
            try {
                PreparedStatement preparedStmt = bd.prepareStatement(q);
                st = bd.createStatement();
                rs = st.executeQuery(q);
                while (rs.next()) {
                    System.out.println(serv[i]);
                    Service = serv[i];
                    System.out.println("service  ="+Service);
                    break;
                }

                if (Service.equals("bon_provisions")||Service.equals("bon_orders")) {
                    if (Service.equals("bon_provisions")){
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
        if (Service.equals("")){
            JOptionPane op= new JOptionPane();
            op.showMessageDialog(null,"رقم الوصل غير موجود");
        }
    }

    public BonSeances getBonData() {
            System.out.println(Service);
            String query = "SELECT prix,num_seance,type,commission,date_seance,date_report,date_report2,somme  FROM "+getService()+" WHERE num_bon='" + id + "'";
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
        return null;
    }
    public BonActe getBonActeData() {
            System.out.println(Service);
            String query = "SELECT nom_notaire,type_acte,date,prix,num,somme  FROM "+getService()+" WHERE num_bon='" + id + "'";
            Statement st;
            ResultSet rs;
            try {
                st = bd.createStatement();
                rs = st.executeQuery(query);

                while (rs.next()) {
                    System.out.println("yes it found it");
                    BonActe bon = new BonActe(id,rs.getString("num"),rs.getString("nom_notaire"), rs.getString("type_acte"), rs.getString("date"), rs.getInt("prix"), rs.getInt("somme"));
                    return bon;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
    public BonOrders getBonOrdersData() {
        System.out.println(Service);
        String query = "SELECT prix,num_order,type,commission,date_order,somme  FROM "+getService()+" WHERE num_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonOrders bon = new BonOrders(rs.getString("num_order"),rs.getString("date_order"),rs.getString("commission"),rs.getString("type"),id,rs.getInt("prix"),rs.getInt("somme"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public BonMandat getBonMandatData() {
        System.out.println(Service);
        String query = "SELECT prix,num_mandat,type,commission,date,service,somme  FROM "+getService()+" WHERE num_bon='" + id + "'";
        System.out.println(query);
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonMandat bon = new BonMandat(rs.getString("num_mandat"),rs.getString("type"),rs.getString("service"),rs.getString("commission"),rs.getString("date"),id,rs.getInt("prix"),rs.getInt("somme"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public BonRqst getBonRqstData() {
        System.out.println(Service);
        String query = "SELECT prix,num_rqst,type,commission,date,somme  FROM "+getService()+" WHERE num_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonRqst bon = new BonRqst(rs.getString("num_rqst"),rs.getString("type"),rs.getString("commission"),rs.getString("date"),id,rs.getInt("prix"),rs.getInt("somme"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public BonProvisions getBonProvisionsData() {
        System.out.println(Service);
        String query = "SELECT prix,num_indice,num_table,type,commission,date,spec,somme  FROM bon_provisions WHERE num_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonProvisions bon = new BonProvisions(rs.getString("num_indice"),rs.getString("num_table"),rs.getString("date"),rs.getString("commission"),rs.getString("type"),rs.getString("spec"),id,rs.getInt("prix"),rs.getInt("somme"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public BonExcuses getBonExcusesData() {
        System.out.println(Service);
        String query = "SELECT prix,type,date_marquage,somme  FROM bon_excuses WHERE num_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonExcuses bon = new BonExcuses(rs.getString("date_marquage"),id,rs.getString("type"),rs.getInt("prix"),rs.getInt("somme"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public BonActe getBonActData() {
        System.out.println(Service);
        String query = "SELECT prix,type_acte,date_acte,nom_notaire,num,somme  FROM bon_acte WHERE num_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonActe bon = new BonActe(id,rs.getString("num"),rs.getString("nom_notaire"),rs.getString("type_acte"),rs.getString("date_acte"),rs.getInt("prix"),rs.getInt("somme"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public BonApercus getBonApercuData() {
        System.out.println(Service);
        String query = "SELECT prix,status,date_fin,somme  FROM bon_apercus WHERE num_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonApercus bon = new BonApercus(id,rs.getInt("prix"),rs.getInt("somme"));
                bon.setStatus(rs.getString("status"));
                bon.setDate_fin(rs.getString("date_fin"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public BonAssociations getBonAssociation() {
        System.out.println(Service);
        String query = "SELECT prix,status,date_fin,somme  FROM bon_associations WHERE num_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonAssociations bon = new BonAssociations(id,rs.getInt("prix"),rs.getInt("somme"));
                bon.setStatus(rs.getString("status"));
                bon.setDate_fin(rs.getString("date_fin"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public BonApercuParOrders getBonApercuParOrdersData() {
        System.out.println(Service);
        String query = "SELECT prix,status,date_fin,somme,num_order,date_order,commission  FROM bon_apercu_parorders WHERE num_bon='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                BonApercuParOrders bon = new BonApercuParOrders(rs.getString("num_order"),rs.getString("commission"),rs.getString("date_order"),id,rs.getInt("prix"),rs.getInt("somme"));
                bon.setStatus(rs.getString("status"));
                bon.setDate_fin(rs.getString("date_fin"));
                return bon;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    public NotificationFidelite getNotificationFidelité () {
        String query = "SELECT num,date  FROM notification_fidelité WHERE id_provision='" + id + "'";
        Statement st;
        ResultSet rs;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println("yes it found it");
                NotificationFidelite notif = new NotificationFidelite(rs.getString("num"),rs.getString("date"),id);
                return notif;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
