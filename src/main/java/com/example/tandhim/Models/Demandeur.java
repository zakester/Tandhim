/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Demandeur {
    private String nom,addr,type,id_bon;
    public void insert(){
    Connection bd = BDConnection.getConnection();
    String query2 = "INSERT INTO `demandeur`(`nom`, `id_bon`, `addr`) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.setString(1, nom);
            preparedStmt2.setString(2, id_bon);
            preparedStmt2.setString(3, addr);
            preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean delete(){
        try {
            Connection bd = BDConnection.getConnection();
            String query = "DELETE FROM demandeur WHERE id_bon='" + id_bon + "' AND nom='"+nom+"' AND addr='"+addr+"'";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int id = preparedStmt.executeUpdate();
            if (id>=1){return true;}
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "عليك ملئ جميع المعلومات لضمان تخزين الوصل");
                }
      
    return false;}
    
    public Demandeur(String nom, String addr, String id_bon) {
        this.nom = nom;
        this.addr = addr;
        this.id_bon = id_bon;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId_bon() {
        return id_bon;
    }

    public void setId_bon(String id_bon) {
        this.id_bon = id_bon;
    }
    public String toString (){
    return nom+" العنوان: "+addr;
    }
    
    
}
