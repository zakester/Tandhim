package com.example.tandhim.Models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Letter {
    private String numLetter,dateLetter,typePV,id_bon;
    private int publish, idObligatoire;
    public Letter (String id_bon,String numLetter, String dateLetter, String typePV,int idObligatoire,int publish){
        this.numLetter=numLetter;
        this.dateLetter=dateLetter;
        this.typePV= typePV;
        this.publish=publish;
        this.id_bon=id_bon;
        this.idObligatoire=idObligatoire;
    }

    public String getNumLetter() {
        return numLetter;
    }

    public void setNumLetter(String numLetter) {
        this.numLetter = numLetter;
    }

    public void setDateLetter(String dateLetter) {
        this.dateLetter = dateLetter;
    }

    public void setTypePV(String typePV) {
        this.typePV = typePV;
    }

    public void setId_bon(String id_bon) {
        this.id_bon = id_bon;
    }

    public void setPublish(int publish) {
        this.publish = publish;
    }

    public void setIdObligatoire(int idObligatoire) {
        this.idObligatoire = idObligatoire;
    }

    public String getDateLetter() {
        return dateLetter;
    }

    public String getTypePV() {
        return typePV;
    }

    public String getId_bon() {
        return id_bon;
    }

    public int getPublish() {
        return publish;
    }

    public int insert() {
        Connection bd = BDConnection.getConnection();
        String query2 = "INSERT INTO `letter`(`id_rapport`, `type_rapport`, `num_lettre`, `date_lettre`,`id_obligatoire`, `publier`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.setString(1, id_bon);
            preparedStmt2.setString(2, typePV);
            preparedStmt2.setString(3, numLetter);
            java.sql.Date date1 = java.sql.Date.valueOf(dateLetter);
            preparedStmt2.setDate(4, date1);
            preparedStmt2.setInt(5, idObligatoire);
            preparedStmt2.setInt(6, publish);
            return preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getLetterId() {
        Connection bd = BDConnection.getConnection();
        Statement st;
        ResultSet rs;
        String q1 = "SELECT id FROM letter WHERE id_rapport='" + id_bon + "' AND type_rapport='" + typePV + "' AND num_lettre='" + numLetter + "'";
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
        return 0;
    }
    public void updateLetter(){
        Connection bd = BDConnection.getConnection();
        String query2 = "UPDATE letter SET id_rapport='"+id_bon+"', type_rapport='"+typePV+"', num_lettre='"+numLetter+"', date_lettre='"+dateLetter+"', id_obligatoire='"+idObligatoire+"' WHERE id="+getLetterId();
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int publish() {
        Connection bd = BDConnection.getConnection();
        String query2 = "UPDATE letter SET publier=1 WHERE id_rapport='"+id_bon+"'";
        try {
            PreparedStatement preparedStmt2 = bd.prepareStatement(query2);
            return preparedStmt2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Demandeur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
