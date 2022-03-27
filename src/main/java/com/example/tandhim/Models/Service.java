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

/**
 *
 * @author DELL
 */
public class Service {

    public String getService(String table, String id_bon) throws SQLException {
        if (table.equals("bon_provisions")||table.equals("bon_orders")) {
            try {
                Connection bd = BDConnection.getConnection();
                Statement st;
                ResultSet rs;
                String q1 = "SELECT id FROM notification_fidelité WHERE id_provision='" + id_bon + "' ";
                int id = 0;
                st = bd.createStatement();
                rs = st.executeQuery(q1);
                while (rs.next()) {
                    id = rs.getInt("id");
                }
                
                if (id == 0) {
                    if (table.equals("bon_provisions")) {
                        q1 = "SELECT type FROM bon_provisions WHERE num_bon='" + id_bon + "' ";
                        st = bd.createStatement();
                        rs = st.executeQuery(q1);
                        System.out.println(q1);
                        while (rs.next()) {
                            System.out.println("Models.Service.getService()");
                            return "تبليغ " + rs.getString("type");
                        }
                    }
                } else return "تكليف بالوفاء";
            } catch (SQLException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (table.equals("bon_seances")) return "تبليغ جلسة";
        if (table.equals("bon_excuses")) return "تبليغ إعذار";
        if (table.equals("bon_mandat")) return "تبليغ مذكرة";
        if (table.equals("bon_rqst")) return "تبليغ عريضة";
        if (table.equals("bon_orders")) return "تبليغ أمر";
        if (table.equals("bon_acte")) return "تكليف بالوفاء";
        if (table.equals("bon_apercus")) return "معاينة";
        if (table.equals("bon_apercu_parorders")) return "معاينة بأمر";
        if (table.equals("bon_associations")) return "حضور جمعية عامة";
        return null;
    }
    public String getTable(String table){

        if (table.equals("تبليغ جلسة")) return "bon_seances";
        if (table.equals("تكليف بالوفاء")) return "bon_provisions";
        if (table.equals("القرارات")||table.equals("الأحكام")||table.equals("تكليف بالوفاء")) return "bon_seances";
        if (table.equals("تبليغ إعذار")||table.equals("الإعذارات")) return "bon_excuses";
        if (table.equals("تبليغ مذكرة")) return "bon_mandat";
        if (table.equals("تبليغ عريضة")) return "bon_rqst";
        if (table.equals("تبليغ أمر")||table.equals("الأوامر")) return "bon_orders";
        if (table.equals("معاينة")||table.equals("وصل معاينة")) return "bon_apercus";
        if (table.equals("معاينة بأمر")||table.equals("وصل معاينة بأمر")) return "bon_apercu_parorders";
        if (table.equals("حضور جمعية عامة")||table.equals("وصل جمعية")) return "bon_associations";
        return null;
}

}
