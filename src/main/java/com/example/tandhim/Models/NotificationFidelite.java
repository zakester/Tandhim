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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class NotificationFidelite {

    private String num, date, num_bon;

    /**
     *
     */
    public NotificationFidelite(String num, String date1, String num_bon) {
        this.num = num;
        this.date = date1;
        this.num_bon = num_bon;
    }

    public void insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `notification_fidelité`(`id_provision`, `num`, `date`) VALUES (?,?,?);CALL log_insert("+ Controller.getUserID()+",'notification_fidelite',LAST_INSERT_ID(),' ');";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num_bon);
            preparedStmt.setString(2, num);
            preparedStmt.setString(3, date);
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void update() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "UPDATE `notification_fidelité` SET  num= ?, date = ? WHERE id_provision='"+num_bon+"';CALL log_update("+ Controller.getUserID()+",'notification_fidelite','"+num_bon+"',' ');";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, num);
            preparedStmt.setString(2, date);
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
