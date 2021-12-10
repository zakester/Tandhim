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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class identif {

    String id = "";
    Date d = new Date();
    int y = d.getYear();

    public identif() {
        y = y - 100;
    }

    public static void main(String[] args) {
        identif i = new identif();
        System.out.println("id =" + i.getidentif());

    }

    public String getidentif() {

        Connection bd = BDConnection.getConnection();
        String query;
        query = "SELECT COUNT(id) FROM identif WHERE year=" + y;
        Statement st;
        ResultSet rs;
        try {

            st = bd.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int i = rs.getInt("COUNT(id)") + 1;
                id = i + "/" + y;
            }
            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public void increment() {
        try {
            Connection bd = BDConnection.getConnection();
            String query;
            query = "INSERT INTO `identif`(`id`, `year`) VALUES (?,?)";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            String [] id = this.getidentif().split("/");
            int i = Integer.parseInt(id[0]);
            System.out.println("d = "+ i+" y= "+y);
            preparedStmt.setInt(1, i);
            preparedStmt.setInt(2, y);
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
