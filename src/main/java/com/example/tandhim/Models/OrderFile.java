package com.example.tandhim.Models;

import com.example.tandhim.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderFile {
    String numOrder,dateOrder,commission,spec,typeOrder,numBon;
    public OrderFile(String numBon,String numOrder,String dateOrder,String commission,String spec,String typeOrder) {
        this.dateOrder=dateOrder;
        this.numOrder=numOrder;
        this.typeOrder=typeOrder;
        this.spec=spec;
        this.commission=commission;
        this.numBon=numBon;
    }
    public boolean insert() {
        try {
            Connection bd = BDConnection.getConnection();
            String query = "INSERT INTO `orders`(`num_bon`, `num_order`, `date_order`, `commission`,`spec`,`type_order`)  VALUES (?,?,?,?,?,?);";
            String sDate = dateOrder;
            java.sql.Date date1 = java.sql.Date.valueOf(sDate);
            PreparedStatement preparedStmt = bd.prepareStatement(query);
            preparedStmt.setString(1, numBon);
            preparedStmt.setString(2, numOrder);
            preparedStmt.setDate(3, date1);
            preparedStmt.setString(4, commission);
            preparedStmt.setString(5,spec);
            preparedStmt.setString(6, typeOrder);
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
        if (a.length == 1 || !ArabicChar(a[1])) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "اسم الهيئة خاطئ");
            alert.showAndWait();
            return false;
        }if (dateOrder==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("style.css").toExternalForm());
            dialogPane.getStyleClass().add("dialog-pane");
            alert.setTitle("خطأ في الإدخال");
            alert.setContentText(
                    "تاريخ الأمر غير مدرج");
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
            String query = "UPDATE orders SET  num_order='" + numOrder + "', date_order='" + dateOrder + "', commission='" + commission + "', spec='" + spec +"', type_order='" + typeOrder +"' WHERE num_bon='" + numBon + "';";
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

    public String getNumOrder() {
        return numOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public String getCommission() {
        return commission;
    }

    public String getSpec() {
        return spec;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public void setNumOrder(String numOrder) {
        this.numOrder = numOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }
}
