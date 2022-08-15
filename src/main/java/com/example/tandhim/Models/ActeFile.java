package com.example.tandhim.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActeFile {
    String numActe,dateActe,notaire,typeActe,numBon;
    public ActeFile(String numBon,String numActe,String dateActe,String notaire,String typeActe) {
        this.dateActe=dateActe;
        this.numActe=numActe;
        this.typeActe=typeActe;
        this.notaire=notaire;
        this.numBon=numBon;
    }
    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `actes`(`num_bon`, `num_acte`, `date_acte`, `notaire`,`type_acte`)  VALUES (?,?,?,?,?);";
            String sDate = dateActe;
            java.sql.Date date1 = java.sql.Date.valueOf(sDate);
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, numBon);
            preparedStmt.setString(2, numActe);
            preparedStmt.setDate(3, date1);
            preparedStmt.setString(4, notaire);
            preparedStmt.setString(5,typeActe);
            int id = preparedStmt.executeUpdate();
            if (id >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    public boolean update() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "UPDATE actes SET  num_acte='" + numActe + "', date_acte='" + dateActe + "', notaire='" + notaire + "', type_acte='" + typeActe +"' WHERE num_bon='" + numBon + "';";
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            int id = preparedStmt.executeUpdate();
            if (id >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public void setNumActe(String numActe) {
        this.numActe = numActe;
    }

    public void setDateActe(String dateActe) {
        this.dateActe = dateActe;
    }

    public void setNotaire(String notaire) {
        this.notaire = notaire;
    }

    public void setTypeActe(String typeActe) {
        this.typeActe = typeActe;
    }

    public String getNumActe() {
        return numActe;
    }

    public String getDateActe() {
        return dateActe;
    }

    public String getNotaire() {
        return notaire;
    }

    public String getTypeActe() {
        return typeActe;
    }
}
