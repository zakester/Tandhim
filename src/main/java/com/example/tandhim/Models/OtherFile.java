package com.example.tandhim.Models;

import org.controlsfx.control.spreadsheet.SpreadsheetCellType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OtherFile {
    String numBon, dateMarquage,typeFile,langue;
    public OtherFile(String numBon, String dateMarquage, String typeFile,String langue) {
        this.numBon=numBon;
        this.typeFile=typeFile;
        this.dateMarquage=dateMarquage;
        this.langue=langue;
    }
    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `fichiers`(`num_bon`, `type_fichier`, `date_marquage`, `langue`)  VALUES (?,?,?,?);";
            String sDate = dateMarquage;
            java.sql.Date date1 = java.sql.Date.valueOf(sDate);
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, numBon);
            preparedStmt.setString(2, typeFile);
            preparedStmt.setDate(3, date1);
            preparedStmt.setString(4, langue);
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
            String query = "UPDATE actes SET  type_fichier='" + typeFile + "', date_marquage='" + dateMarquage + "', langue='" + langue + "' WHERE num_bon='" + numBon + "';";
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

}
