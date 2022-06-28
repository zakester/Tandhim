package com.example.tandhim.Models.Impression;

import com.example.tandhim.Models.BDConnection;
import com.example.tandhim.Models.Demandeur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class publish {
    String numBon, typePv, adressed=null, response=null, typeRqst=null, dateFinCommune=null,dateFineTribunal=null;
    int id, id_oblig;
    public publish(String numBon, int id_oblig, String typePv) {
        this.numBon=numBon;
        this.id_oblig= id_oblig;
        this.typePv=typePv;
    }
    public void insert() {
        Connection bd = BDConnection.getConnection();
        String query2 = "INSERT INTO `publish`(`num_bon`, `type_pv`, `id_oblig`, `adressed`, `response`, `typerqst`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.setString(1, numBon);
            preparedStmt2.setString(2, typePv);
            preparedStmt2.setInt(3, id_oblig);
            preparedStmt2.setString(4, adressed);
            preparedStmt2.setString(5, response);
            preparedStmt2.setString(6, typeRqst);
            preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public boolean delete() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "DELETE FROM publish WHERE id_bon='" + numBon + "' AND id_oblig='" + id_oblig + "'";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int id = preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    public void update () {
        Connection bd = BDConnection.getConnection();
        String query2 = "UPDATE publish SET type_pv='"+typePv+"', type_rqst='"+typeRqst+"', adressed="+adressed+" , response='"+response+"' , date_fin_commune='"+getDateFinCommuneSQL()+"' , date_fin_tribunal='"+getDateFineTribunalSQL()+"' WHERE num_bon='"+numBon+"' AND id_oblig="+id_oblig;
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }


    public void setTypePv(String typePv) {
        this.typePv = typePv;
    }

    public void setAdressed(String adressed) {
        this.adressed = adressed;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setTypeRqst(String typeRqst) {
        this.typeRqst = typeRqst;
    }

    public void setDateFinCommune(String dateFinCommune) {
        this.dateFinCommune = dateFinCommune;
    }

    public void setDateFineTribunal(String dateFineTribunal) {
        this.dateFineTribunal = dateFineTribunal;
    }

    public String getNumBon() {
        return numBon;
    }

    public String getTypePv() {
        return typePv;
    }

    public String getAdressed() {
        return adressed;
    }

    public String getResponse() {
        return response;
    }

    public String getTypeRqst() {
        return typeRqst;
    }

    public String getDateFinCommune() {
        return dateFinCommune;
    }

    public String getDateFineTribunal() {
        return dateFineTribunal;
    }

    public int getId() {
        return id;
    }

    public int getId_oblig() {
        return id_oblig;
    }

    public String getDateFinCommuneSQL() {
        return (dateFinCommune!=null)? "'"+dateFinCommune+"'":null;
    }
    public String getDateFineTribunalSQL() {
        return (dateFineTribunal!=null)? "'"+dateFineTribunal+"'":null;
    }

}
