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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class BonStats {

    ArrayList<String> nb_np = new ArrayList();
    ArrayList<String> nb_p = new ArrayList();
    ArrayList<String> nb_vr = new ArrayList();
    ArrayList<String> nb_all = new ArrayList();

    public ArrayList<String> getNb_np() {
        return nb_np;
    }

    public ArrayList<String> getNb_p() {
        return nb_p;
    }

    public ArrayList<String> getNb_vr() {
        return nb_vr;
    }

    public ArrayList<String> getNb_all() {
        return nb_all;
    }

    public class bonStatsRow {

        private String num_bon, status, type, date, demandeur, obligatoire;

        public bonStatsRow(String num_bon, String demandeur, String obligatoire, String type, String date, String status) {
            this.num_bon = num_bon;
            this.status = status;
            this.date = date;
            this.type = type;
            this.demandeur = demandeur;
            this.obligatoire = obligatoire;
        }

        public String getDemandeur() {
            return demandeur;
        }

        public StringProperty getObligatoireProperty() {
            return new SimpleStringProperty(obligatoire);
        }

        public StringProperty getDemandeurProperty() {

            return new SimpleStringProperty(demandeur);
        }

        public String getObligatoire() {
            return obligatoire;
        }

        public String getStatus() {
            return status;
        }

        public StringProperty getStatusProperty() {
            return new SimpleStringProperty(status);
        }

        public String getDate() {
            return date;
        }

        public StringProperty getDateProperty() {
            String d=date.replaceAll(date.substring(10), "");
            return new SimpleStringProperty(d);
        }

        public String getType() {
            return type;
        }

        public StringProperty getTypeProperty() {
            return new SimpleStringProperty(type);
        }

        public String getNum_bon() {
            return num_bon;
        }

        public StringProperty getNum_bonProperty() {
            return new SimpleStringProperty(num_bon);
        }
    }
    public class bonStatsExeRow {

        private String num_bon, status, type, date, created_at,commission, obligatoire;

        public bonStatsExeRow(String num_bon, String type, String obligatoire, String commission, String created_at,  String status,String date) {
            this.num_bon = num_bon;
            this.status = status;
            this.date = date;
            this.type = type;
            this.commission = commission;
            this.created_at = created_at;
            this.obligatoire = obligatoire;
        }

        public String getCommission() {
            return commission;
        }

        public StringProperty getObligatoireProperty() {
            return new SimpleStringProperty(obligatoire);
        }

        public StringProperty getCommissionProperty() {

            return new SimpleStringProperty(commission);
        }

        public String getObligatoire() {
            return obligatoire;
        }

        public String getStatus() {
            return status;
        }

        public StringProperty getStatusProperty() {
            return new SimpleStringProperty(status);
        }

        public String getDate() {
            return date;
        }

        public StringProperty getDateProperty() {
            System.out.println(date);
            String d=date;
            return new SimpleStringProperty(d);
        }
        public String getCreatedAt() {
            return created_at;
        }

        public StringProperty getCreatedAtProperty() {
            String d=created_at.replaceAll(created_at.substring(10), "");
            return new SimpleStringProperty(d);
        }

        public String getType() {
            return type;
        }

        public StringProperty getTypeProperty() {
            return new SimpleStringProperty(type);
        }

        public String getNum_bon() {
            return num_bon;
        }

        public StringProperty getNum_bonProperty() {
            return new SimpleStringProperty(num_bon);
        }
    }

    public ObservableList remp1(String date1, String date2, String order_by1) throws SQLException {
        ObservableList<bonStatsRow> bons = FXCollections.observableArrayList();
        Connection bd = BDConnection.getConnection();
        Statement st1, st2, st3;
        ResultSet rs1, rs2, rs3;
        String Qr = "drop view if exists v1";
        PreparedStatement preparedStmt = bd.prepareStatement(Qr);
        int id = preparedStmt.executeUpdate();
        Qr = "CREATE VIEW v1 AS SELECT trim('تبليغ جلسة') as type,num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_seances b WHERE o.id_bon = num_bon and num_bon = d.id_bon and (o.status='منجزة' OR o.status='تم إرسال رسالة') UNION "
                + "SELECT trim('تبليغ عريضة'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_rqst b WHERE o.id_bon = num_bon and num_bon = d.id_bon and (o.status='منجزة' OR o.status='تم إرسال رسالة') UNION "
                + "SELECT trim('تبليغ مذكرة'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_mandat b WHERE o.id_bon = num_bon and num_bon = d.id_bon and (o.status='منجزة' OR o.status='تم إرسال رسالة') UNION "
                + "SELECT trim('تبليغ أمر'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_orders b WHERE o.id_bon = num_bon and num_bon = d.id_bon and (o.status='منجزة' OR o.status='تم إرسال رسالة') UNION "
                + "SELECT trim('تبليغ إعذار'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_excuses b WHERE o.id_bon = num_bon and num_bon = d.id_bon and (o.status='منجزة' OR o.status='تم إرسال رسالة') union "
                + "SELECT trim('تكليف بالوفاء'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_provisions b WHERE b.num_bon IN (SELECT id_provision FROM  notification_fidelité) AND o.id_bon = num_bon and num_bon = d.id_bon and (o.status='منجزة' OR o.status='تم إرسال رسالة') union "
                + "SELECT trim('معاينة'),num_bon,d.nom as dem,trim('////'),b.date_fin,b.status ,b.created_at from  demandeur d, bon_apercus b WHERE num_bon = d.id_bon and b.status='منجزة' union "
                + "SELECT trim('معاينة بأمر'),num_bon,d.nom as dem,trim('////'),b.date_fin,b.status ,b.created_at from  demandeur d, bon_apercu_parorders b WHERE num_bon = d.id_bon and b.status='منجزة' union "
                + "SELECT trim('جمعية عامة'),num_bon,d.nom as dem,trim('////'),b.date_fin,b.status ,b.created_at from  demandeur d, bon_associations b WHERE num_bon = d.id_bon and b.status='منجزة'";
        String Qr1 = "SELECT type,num_bon,dem,nom,date,status from v1 WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "'";
        preparedStmt = bd.prepareStatement(Qr);
        preparedStmt.executeUpdate();
        st1 = bd.createStatement();
        rs1 = st1.executeQuery(Qr1);
        while (rs1.next()) {
            System.out.println("date = "+rs1.getString("date"));
            bonStatsRow b = new bonStatsRow(rs1.getString("num_bon"), rs1.getString("dem"), rs1.getString("nom"), rs1.getString("type"), rs1.getString("date"), rs1.getString("status"));
            bons.add(b);
        }

        return bons;
    }

    public ObservableList remp(String date1, String date2, String order_by2) throws SQLException {
        ObservableList<bonStatsRow> bons = FXCollections.observableArrayList();
        String Qr = "drop view if exists v2";
        Connection bd = BDConnection.getConnection();
        Statement st1, st2, st3;
        ResultSet rs1, rs2, rs3;
        PreparedStatement preparedStmt = bd.prepareStatement(Qr);
        int id = preparedStmt.executeUpdate();
        Qr = "CREATE VIEW v2 AS SELECT b.id,trim('تبليغ جلسة') as type,num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_seances b WHERE o.id_bon = num_bon and num_bon = d.id_bon UNION "
                + "SELECT b.id,trim('تبليغ عريضة'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_rqst b WHERE o.id_bon = num_bon and num_bon = d.id_bon  UNION "
                + "SELECT b.id,trim('تبليغ مذكرة'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_mandat b WHERE o.id_bon = num_bon and num_bon = d.id_bon UNION "
                + "SELECT b.id,trim('تبليغ أمر'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_orders b WHERE o.id_bon = num_bon and num_bon = d.id_bon  UNION "
                + "SELECT b.id,trim('تبليغ إعذار'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_excuses b WHERE o.id_bon = num_bon and num_bon = d.id_bon   union "
                + "SELECT b.id,trim('تكليف بالوفاء'),num_bon,d.nom as dem,o.nom,o.date,o.status ,b.created_at from  demandeur d,obligatoire o, bon_provisions b WHERE b.num_bon IN (SELECT id_provision FROM  notification_fidelité) AND o.id_bon = num_bon and num_bon = d.id_bon union "
                + "SELECT b.id,trim('معاينة'),num_bon,d.nom as dem,trim('////'),b.date_fin,b.status ,b.created_at from  demandeur d, bon_apercus b WHERE num_bon = d.id_bon union "
                + "SELECT b.id,trim('معاينة بأمر'),num_bon,d.nom as dem,trim('////'),b.date_fin,b.status ,b.created_at from  demandeur d, bon_apercu_parorders b WHERE num_bon = d.id_bon union "
                + "SELECT b.id,trim('جمعية عامة'),num_bon,d.nom as dem,trim('////'),b.date_fin,b.status ,b.created_at from  demandeur d, bon_associations b WHERE num_bon = d.id_bon ";
        String Qr1 = "SELECT type,num_bon,dem,nom,created_at,status from v2 WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' order by " + order_by2;
        preparedStmt = bd.prepareStatement(Qr);
        preparedStmt.executeUpdate();
        st1 = bd.createStatement();
        rs1 = st1.executeQuery(Qr1);
        while (rs1.next()) {
            bonStatsRow b = new bonStatsRow(rs1.getString("num_bon"), rs1.getString("dem"), rs1.getString("nom"), rs1.getString("type"), rs1.getString("created_at"), rs1.getString("status"));
            bons.add(b);
        }
        bd.close();
        return bons;
    }
     public ObservableList rempStatsExe(String date1, String date2, String order_by2) throws SQLException {
        ObservableList<bonStatsExeRow> bons = FXCollections.observableArrayList();
        String Qr = "drop view if exists v2";
        Connection bd = BDConnection.getConnection();
        Statement st1, st2, st3;
        ResultSet rs1, rs2, rs3;
        PreparedStatement preparedStmt = bd.prepareStatement(Qr);
        int id = preparedStmt.executeUpdate();
        Qr = "CREATE VIEW v2 AS SELECT trim('تكليف بالوفاء/ حكم') as type,num_bon,o.nom,o.date,o.status ,b.created_at,b.commission,b.spec from  obligatoire o, bon_provisions b WHERE o.id_bon = num_bon and num_bon in (SELECT id_provision from notification_fidelité) and b.type='حكم' UNION "
                + "SELECT trim('تكليف بالوفاء/ أمر') as type,num_bon,o.nom,o.date,o.status ,b.created_at,b.commission,b.type as spec from  obligatoire o, bon_orders b WHERE o.id_bon = num_bon and num_bon in (SELECT id_provision from notification_fidelité)  UNION "
                + "SELECT trim('تكليف بالوفاء/ قرار') as type,num_bon,o.nom,o.date,o.status ,b.created_at,b.commission,b.spec from  obligatoire o, bon_provisions b WHERE o.id_bon = num_bon and num_bon in (SELECT id_provision from notification_fidelité) and b.type='قرار' UNION "
                + "SELECT trim('تكليف بالوفاء/ عقد') as type,num_bon,o.nom,o.date,o.status,b.created_at,b.nom_notaire as commission,b.type_acte as spec from  obligatoire o, bon_acte b WHERE o.id_bon = num_bon ";
        String Qr1 = "SELECT type,num_bon,nom,date,status,created_at,commission,spec from v2 WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' ";
        preparedStmt = bd.prepareStatement(Qr);
        preparedStmt.executeUpdate();
        st1 = bd.createStatement();
        rs1 = st1.executeQuery(Qr1);
        while (rs1.next()) {
            System.out.println("date="+rs1.getString("date"));
            bonStatsExeRow b = new bonStatsExeRow(rs1.getString("num_bon"), rs1.getString("commission"), rs1.getString("nom"), rs1.getString("type"),rs1.getString("created_at"), rs1.getString("status"),rs1.getString("date"));
            bons.add(b);
        }
        bd.close();
        return bons;
    }
    public ArrayList<Integer> remp2(String date1, String date2) {
        ArrayList<Integer> rtrn = new ArrayList();
        nb_np.removeAll(nb_np);
        nb_p.removeAll(nb_p);
        nb_vr.removeAll(nb_vr);
        nb_all.removeAll(nb_all);
        try {
            int somme_totale = 0,somme_expected = 0, nb_paye = 0, nb_nonpaye = 0, nb_vers = 0;
            Connection bd = BDConnection.getConnection();
            Statement st1, st2, st3;
            ResultSet rs1, rs2, rs3;
            String Qr = "SELECT trim('bon_seances') as bon,trim('3000') as type,num_bon,prix,somme from bon_seances WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_rqst'),trim('1500'),num_bon,prix,somme from bon_rqst WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_provisions'),trim('bon_provisions'),num_bon,prix,somme from bon_provisions WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_orders'),trim('1500'),num_bon,prix,somme from bon_orders WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_mandat'),trim('1500'),num_bon,prix,somme from bon_mandat WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_excuses'),trim('1870'),num_bon,prix,somme from bon_excuses WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_autres'),trim('0'),num_bon,prix,somme from bon_autres WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_associations'),trim('4500'),num_bon,prix,somme from bon_associations WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_apercu_parorders'),trim('4500'),num_bon,prix,somme from bon_apercu_parorders WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "' UNION"
                    + " SELECT trim('bon_apercus'),trim('4500'),num_bon,prix,somme from bon_apercus WHERE created_at >= '" + date1 + "' AND created_at <= '" + date2 + "'";
            st1 = bd.createStatement();
            rs1 = st1.executeQuery(Qr);
            System.out.println(Qr);
            while (rs1.next()) {
                somme_totale = somme_totale + rs1.getInt("prix");
                somme_expected=somme_expected+rs1.getInt("somme");
                nb_all.add(rs1.getString("num_bon"));
                nb_all.add(rs1.getString("bon"));
                if (rs1.getInt("prix") == 0) {
                    nb_nonpaye++;
                    nb_np.add(rs1.getString("num_bon"));
                    nb_np.add(rs1.getString("bon"));
                } else if (rs1.getInt("prix") < rs1.getInt("somme")) {
                    nb_vers++;
                    System.out.println("nbvers = " + nb_vers+" "+rs1.getString("num_bon"));
                    nb_vr.add(rs1.getString("num_bon"));
                    nb_vr.add(rs1.getString("bon"));
                } else if (rs1.getInt("prix") >= rs1.getInt("somme")) {
                    nb_paye++;
                    System.out.println("nbpaye = " + nb_paye+" "+rs1.getString("num_bon"));
                    nb_p.add(rs1.getString("num_bon"));
                    nb_p.add(rs1.getString("bon"));
                }
            
            }
            rtrn.add(nb_paye);
            rtrn.add(nb_nonpaye);
            rtrn.add(nb_vers);
            rtrn.add(somme_expected);
            rtrn.add(somme_totale);
            bd.close();
        } catch (SQLException ex) {
        }
        return rtrn;
    }

    public int vraiPrix(String num_bon, String prix) {

        int somme = 0;
        try {
            Connection bd = BDConnection.getConnection();
            Statement st1, st2, st3;
            ResultSet rs1, rs2, rs3;
            if (prix.equals("bon_provisions")) {
                System.out.println("type = " + prix);
                String Qr1 = "SELECT id from notification_fidelité where id_provision='" + num_bon + "' ";
                st1 = bd.createStatement();
                rs1 = st1.executeQuery(Qr1);
                int id = 0;
                while (rs1.next()) {
                    id = rs1.getInt("id");
                }
                if (id != 0) {
                    prix = "6780";
                } else {
                    prix = "1500";
                }
            }
            if (prix.equals("4500")) {
                return 4500;
            } else {
                System.out.println("type = " + prix);
                String Qr = "SELECT en_suspens from obligatoire where id_bon='" + num_bon + "' ";
                st1 = bd.createStatement();
                rs1 = st1.executeQuery(Qr);
                while (rs1.next()) {
                    if (rs1.getInt("en_suspens") == 1) {
                        somme = somme + Integer.parseInt(prix) + 3000;
                    } else if (rs1.getInt("en_suspens") == 3) {
                        somme = somme + Integer.parseInt(prix) + 9000;
                    } else {
                        somme = somme + Integer.parseInt(prix);
                    }
                }
            }
            bd.close();
        } catch (SQLException ex) {
        }
        return somme;
    }

}

