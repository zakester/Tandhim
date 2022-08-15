package com.example.tandhim.Models;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JugementFile {
    String numIndice,numTable,dateJugement,commission,spec,typeJugement,numBon;
    public JugementFile(String numBon,String numIndice,String numTable,String dateJugement,String commission,String spec,String typeJugement) {
        this.dateJugement=dateJugement;
        this.numIndice=numIndice;
        this.numTable=numTable;
        this.typeJugement=typeJugement;
        this.spec=spec;
        this.commission=commission;
        this.numBon=numBon;
    }
    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `jugements`(`num_bon`, `num_indice`,`num_table`, `date`, `commission`,`spec`,`type`)  VALUES (?,?,?,?,?,?,?);";
            String sDate = dateJugement;
            java.sql.Date date1 = java.sql.Date.valueOf(sDate);
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, numBon);
            preparedStmt.setString(2, numIndice);
            preparedStmt.setString(3, numTable);
            preparedStmt.setDate(4, date1);
            preparedStmt.setString(5, commission);
            preparedStmt.setString(6,spec);
            preparedStmt.setString(7, typeJugement);
            int id = preparedStmt.executeUpdate();
            if (id >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    public boolean validate() {
        String[] a = commission.split(" : ");
        System.out.println(a);
        if (a.length == 1 || !ArabicChar(a[1])) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("../home/style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "اسم الهيئة خاطئ");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean ArabicChar(String ar) {
        char[] cs = ar.toCharArray();
        String s = "ابتجحخدذرزسشصضطظعغفقكلمنهوي ةىءئؤأآإ";
        for (Character c : cs) {
            if (!s.contains(c.toString())) {
                return false;
            }
        }
        return true;
    }


    public boolean update() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "UPDATE jugements SET  num_indice='" + numIndice + "',num_table='" + numTable + "', date='" + dateJugement + "', commission='" + commission + "', spec='" + spec +"', type='" + typeJugement +"' WHERE num_bon='" + numBon + "';";
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

    public void setNumIndice(String numIndice) {
        this.numIndice = numIndice;
    }

    public void setNumTable(String numTable) {
        this.numTable = numTable;
    }

    public void setDateJugement(String dateJugement) {
        this.dateJugement = dateJugement;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setTypeJugement(String typeJugement) {
        this.typeJugement = typeJugement;
    }

    public String getNumIndice() {
        return numIndice;
    }

    public String getNumTable() {
        return numTable;
    }

    public String getDateJugement() {
        return dateJugement;
    }

    public String getCommission() {
        return commission;
    }

    public String getSpec() {
        return spec;
    }

    public String getTypeJugement() {
        return typeJugement;
    }
}
