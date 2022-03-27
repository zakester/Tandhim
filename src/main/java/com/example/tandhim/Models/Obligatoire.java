/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Obligatoire {

    private String nom, addr, status = null, date = null, id_bon;
    int en_suspens;

    public Obligatoire(String nom, String addr, String id_bon, String status, String date, int en_suspens) {
        this.nom = nom;
        this.addr = addr;
        this.id_bon = id_bon;
        this.en_suspens = en_suspens;
        this.status = status;
        this.date = date;
    }
    public int getObligatoireId() {
        Connection bd = BDConnection.getConnection();
        Statement st;
        ResultSet rs;
        String q1 = "SELECT id FROM obligatoire WHERE nom='" + nom + "' AND addr='" + addr + "' AND id_bon='" + id_bon + "'";
        int id = 0;
        try {
            st = bd.createStatement();
            rs = st.executeQuery(q1);
            while (rs.next()) {
                id = rs.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void insert() {
        Connection bd = BDConnection.getConnection();
        String query2 = "INSERT INTO `Obligatoire`(`nom`, `id_bon`, `addr`, `status`, `date`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.setString(1, nom);
            preparedStmt2.setString(2, id_bon);
            preparedStmt2.setString(3, addr);
            if (status == null) {
                preparedStmt2.setString(4, status);
                preparedStmt2.setDate(5, null);
            } else {
                if (status.equals("ملغاة") || status.equals("منجزة")) {
                    java.sql.Date date1 = java.sql.Date.valueOf(date);
                    preparedStmt2.setString(4, status);
                    preparedStmt2.setDate(5, date1);
                } else {
                    preparedStmt2.setString(4, status);
                    preparedStmt2.setDate(5, null);
                }
            }
            preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean delete() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "DELETE FROM obligatoire WHERE id_bon='" + id_bon + "' AND nom='" + nom + "' AND addr='" + addr + "'";
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

    public boolean delete_action() {
        try {
            Connection bd = BDConnection.getConnection();
            Statement st;
            ResultSet rs;
            String q1 = "SELECT id FROM obligatoire WHERE nom='" + nom + "' AND addr='" + addr + "' AND id_bon='" + id_bon + "'";
            int id = 0;
            st = bd.createStatement();
            rs = st.executeQuery(q1);
            while (rs.next()) {
                id = rs.getInt("id");
            }
            System.out.println(id);
            String query = "DELETE FROM action WHERE id_oblig=" + id;
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int idn = preparedStmt.executeUpdate();
            if (idn >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "عليك ملئ جميع المعلومات لضمان تخزين الوصل");
        }

        return false;
    }

    public boolean deleteLetter() {
        try {
            Connection bd = BDConnection.getConnection();
            Statement st;
            ResultSet rs;
            String q1 = "SELECT id FROM obligatoire WHERE nom='" + nom + "' AND addr='" + addr + "' AND id_bon='" + id_bon + "'";
            int id = 0;
            st = bd.createStatement();
            rs = st.executeQuery(q1);
            while (rs.next()) {
                id = rs.getInt("id");
            }
            System.out.println("id =" + id);
            String query = "DELETE FROM letter WHERE id_obligatoire=" + id;
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int idn = preparedStmt.executeUpdate();
            if (idn >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "عليك ملئ جميع المعلومات لضمان تخزين الوصل");
        }

        return false;
    }

    public String getId_bon() {
        return id_bon;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getStatus() {
        System.out.println("status = " + status);
        System.out.println("nom = " + nom);
        System.out.println("addr = " + addr);

        if ((status==null) || (status.equals(""))) {
            System.out.println("here is the null");
            return "غير منجزة";
        } else {
            return status;
        }
    }
    public void updateObligatoire () {
        Connection bd = BDConnection.getConnection();
        String query2 = "UPDATE obligatoire SET nom='"+getNom()+"', id_bon='"+getId_bon()+"', addr='"+getAddr()+"', status='"+getStatus()+"', date='"+getDate()+"' WHERE id="+getObligatoireId();
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEn_suspens() {
        return en_suspens;
    }

    public void setEn_suspens(int en_suspens) {
        this.en_suspens = en_suspens;
    }

    public String toString() {
        return nom + " العنوان: " + addr;
    }
    public void addLetter (String numLetter,String date,String typePV) {
        Letter letter= new Letter(id_bon,typePV,numLetter,date,getObligatoireId(),0);
        letter.insert();
    }
    public ObservableList<Letter> getLetter(){
        ObservableList<Letter> letter = FXCollections.observableArrayList();
        try {
            Connection bd = BDConnection.getConnection();
            Statement st;
            ResultSet rs;
            String q1 = "SELECT type_rapport,num_lettre,date_lettre,id_obligatoire,publier FROM letter WHERE id_rapport='" + id_bon + "' and id_obligatoire'"+getObligatoireId()+"'";
            int id = 0;
            st = bd.createStatement();
            rs = st.executeQuery(q1);
            while (rs.next()) {
                Letter l =new Letter(id_bon,rs.getString("num_lettre"),rs.getString("date_lettre"),rs.getString("type_rapport"),rs.getInt("id_obligatoire"),rs.getInt("publier"));
                letter.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Obligatoire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return letter;
    }
    public void publish () {
        for (Letter l:this.getLetter()){
            l.publish();
        }
    }
    public String OblStatus() {
        if (status == null || status.equals("")) {
            return "الإجراء الخاص بالمطلوب لم ينجز بعد";
        } else if (status.equals("تم إرسال رسالة")) {
            try {
                Connection bd = BDConnection.getConnection();
                Statement st;
                ResultSet rs;
                String q1 = "SELECT type_rapport,num_lettre,date_lettre,publier FROM letter WHERE id_rapport='" + id_bon + "' and id_obligatoire='"+getObligatoireId()+"'";
                int id = 0;
                st = bd.createStatement();
                rs = st.executeQuery(q1);
                String rtn = "";
                while (rs.next()) {
                    rtn = rtn + "تم إرسال محضر " + rs.getString("type_rapport") + " عن طريق رسالة مسجلة رقم: " + rs.getString("num_lettre") + " بتاريخ: " + rs.getString("date_lettre") + " ";
                } return rtn;
            } catch (SQLException ex) {
                Logger.getLogger(Obligatoire.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (status.equals("تم التبليغ") || status.equals("ملغاة") || status.equals("تم إشعاره(ا)")) {
            return status + " بتاريخ: " + date;
        }
        return "";
    }
}
