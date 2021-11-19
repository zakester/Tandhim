/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.tandhim.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdatepicker.JDatePicker;

/* TODO: add JDatePicker lib */

/**
 *
 * @author DELL
 */
public class bd_compare {
    Connection bd = BDConnection.getConnection();
    Statement st;
    ResultSet rs;
    public void getObligStat(String nom,String addr,String id_bon,String status, JDatePicker d){
    
        try {
            String q= "SELECT status,date  FROM obligatoire WHERE nom='"+nom+"' AND addr='"+addr+"' AND id_bon='"+id_bon+"'";
            System.out.println(q);
            st = bd.createStatement();
            rs = st.executeQuery(q);
            while (rs.next()) {
                if (!(rs.getString("status")==null)){
                }
                }
            } catch (SQLException ex) {
            Logger.getLogger(bd_compare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean varcharVer(String val,String caseName,String tab,String idname,String num){
            int id=-1;

        try {
            String q= "SELECT id, "+caseName+" FROM "+tab+" WHERE "+idname+"='"+num+"' AND "+caseName+"='"+val+"'";
            System.out.println(q);
            st = bd.createStatement();
            rs = st.executeQuery(q);
            while (rs.next()) {
                id=rs.getInt("id");
                System.out.println("*****"+rs.getString(caseName));
                System.out.println(id);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(bd_compare.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (id==-1){
        System.out.println(val+"= false");return false;}else {System.out.println(val+"= true");return true; }
}
    public boolean dateVer(String val,String caseName,String tab,String idname,String num){
        int id=-1;
        try {   
            
            java.sql.Date date = java.sql.Date.valueOf(val);
            System.out.println(date);
            String q= "SELECT id FROM "+tab+" WHERE "+idname+"='"+num+"' AND "+caseName+"='"+date+"'";
            System.out.println(q);
            st = bd.createStatement();
            rs = st.executeQuery(q);
            while (rs.next()) {
                id=rs.getInt("id");}
            
        } catch (SQLException ex) {
            Logger.getLogger(bd_compare.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (id==-1){
        return false;}else {return true;}
}
    public boolean intVer(String val,String caseName,String tab,String idname,String num){
        int id=-1;
        try {   
            
           int i = Integer.parseInt(val);
            String q= "SELECT id FROM "+tab+" WHERE "+idname+"='"+num+"' AND "+caseName+"="+i;
            System.out.println(q);
            st = bd.createStatement();
            rs = st.executeQuery(q);
            while (rs.next()) {
                id=rs.getInt("id");}
            
        } catch (SQLException ex) {
            Logger.getLogger(bd_compare.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (id==-1){
        return false;}else {return true;}
}
}
