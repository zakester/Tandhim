package com.example.tandhim.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logs {
    private int user_id;
    private String time,operation,table_name,id_table,details;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getId_table() {
        return id_table;
    }

    public void setId_table(String id_table) {
        this.id_table = id_table;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Logs(int user_id, String time, String operation, String table_name, String id_table, String details) {
        this.user_id = user_id;
        this.time = time;
        this.operation = operation;
        this.table_name = table_name;
        this.id_table = id_table;
        this.details = details;
    }

    public ObservableList<Logs> select(int user){
        ObservableList<Logs> logs = FXCollections.observableArrayList();
        try {
            Connection bd = BDConnection.getConnection();
            Statement st;
            ResultSet rs;
            String q1 = "SELECT * FROM logs WHERE user_id=" + user;
            int id = 0;
            st = bd.createStatement();
            rs = st.executeQuery(q1);
            while (rs.next()) {
                Logs l =new Logs(user,rs.getString("time"),rs.getString("operation"),rs.getString("table_name"),rs.getString("id_table"),rs.getString("details"));
                logs.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Obligatoire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logs;
    }

    public ObservableList<Logs> select(String timeDebut,String timeFin){
        ObservableList<Logs> logs = FXCollections.observableArrayList();
        try {
            Connection bd = BDConnection.getConnection();
            Statement st;
            ResultSet rs;
            String q1 = "SELECT * FROM `logs` WHERE time BETWEEN '"+timeDebut+"' AND '"+timeFin+"';";
            int id = 0;
            st = bd.createStatement();
            rs = st.executeQuery(q1);
            while (rs.next()) {
                Logs l =new Logs(rs.getInt("user_id"),rs.getString("time"),rs.getString("operation"),rs.getString("table_name"),rs.getString("id_table"),rs.getString("details"));
                logs.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Obligatoire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logs;
    }
    public ObservableList<Logs> select(String timeDebut,String timeFin,int user){
        ObservableList<Logs> logs = FXCollections.observableArrayList();
        try {
            Connection bd = BDConnection.getConnection();
            Statement st;
            ResultSet rs;
            String q1 = "SELECT * FROM `logs` WHERE time BETWEEN '"+timeDebut+"' AND '"+timeFin+"' AND user_id="+user+";";
            int id = 0;
            st = bd.createStatement();
            rs = st.executeQuery(q1);
            while (rs.next()) {
                Logs l =new Logs(rs.getInt("user_id"),rs.getString("time"),rs.getString("operation"),rs.getString("table_name"),rs.getString("id_table"),rs.getString("details"));
                logs.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Obligatoire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logs;
    }
}
