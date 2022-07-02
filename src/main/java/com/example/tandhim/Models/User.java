package com.example.tandhim.Models;

import com.example.tandhim.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    String nom,prenom,address,password,phone,username,type;
    int id;

    public User(String nom, String prenom, String address, String password, String phone,String type, String username,int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.type = type;
        this.username = username;
        this.id = id;

    }
    public User(String nom, String prenom, String address, String password, String phone,String type, String username) {
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.type = type;
        this.username = username;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public ObservableList<User> select(){
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            Connection bd = BDConnection.getConnection();
            Statement st;
            ResultSet rs;
            String q1 = "SELECT * FROM users ";
            int id = 0;
            st = bd.createStatement();
            rs = st.executeQuery(q1);
            while (rs.next()) {
                User u =new User(rs.getString("nom"),rs.getString("prenom"),rs.getString("address"),rs.getString("password"),rs.getString("phone"),rs.getString("type"),rs.getString("username"),rs.getInt("id"));
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Obligatoire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    public int insert() {
        Connection bd = BDConnection.getConnection();
        String query2 = "INSERT INTO `users`(`nom`, `prenom`, `address`,`password`, `phone`, `type`, `username`) VALUES (?,?,?,?,?,?,?);CALL log_insert("+ Controller.getUserID()+",'users',LAST_INSERT_ID(),' ');";
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.setString(1, nom);
            preparedStmt2.setString(2, prenom);
            preparedStmt2.setString(3, address);
            preparedStmt2.setString(4, new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,12).encode(password));
            preparedStmt2.setString(5, phone);
            preparedStmt2.setString(6, type);
            preparedStmt2.setString(7, username);
            return preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void update(){
        Connection bd = BDConnection.getConnection();
        String query2 = "UPDATE users SET nom='"+nom+"', prenom='"+prenom+"', address='"+address+"', phone='"+phone+"', password='"+new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,12).encode(password)+"' WHERE id="+this.id+";CALL log_update("+ Controller.getUserID()+",'users','"+this.id+"',' ');";
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(){
        Connection bd = BDConnection.getConnection();
        String query2 = "DELETE FROM users WHERE id="+this.id+";CALL log_update("+ Controller.getUserID()+",'users','"+this.id+"',' ');";
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
