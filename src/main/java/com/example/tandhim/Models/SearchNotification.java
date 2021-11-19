/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

/**
 *
 * @author Crash
 */
public class SearchNotification {
    private String num_bon,type_service,obligatoire,type,procedure;

    public SearchNotification(String num_bon, String type_service, String obligatoire, String type, String procedure) {
        this.num_bon = num_bon;
        this.type_service = type_service;
        this.obligatoire = obligatoire;
        this.type = type;
        this.procedure = procedure;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    
    
    
    
    public SearchNotification(String num_bon, String type_service, String obligatoire, String type) {
        this.num_bon = num_bon;
        this.type_service = type_service;
        this.obligatoire = obligatoire;
        this.type = type;
    }

    public String getNum_bon() {
        return num_bon;
    }

    public void setNum_bon(String num_bon) {
        this.num_bon = num_bon;
    }

    public String getType_service() {
        return type_service;
    }

    public void setType_service(String type_service) {
        this.type_service = type_service;
    }

    public String getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(String obligatoire) {
        this.obligatoire = obligatoire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public static String SearchQuery(){
        String query="SELECT num_bon,trim('لم تبلغ بعد') as type,trim('تبليغ جلسة') as type_service, nom  FROM `bon_seances` JOIN obligatoire ON id_bon=num_bon WHERE date_seance<= date_add(curdate(),interval 2 day) AND  date_seance>= curdate() UNION SELECT id_bon AS num_bon,trim('انتهاء الأجل القانوني') as type,trim('تكليف بالوفاء'),nom as type_service FROM obligatoire o JOIN notification_fidelité n ON id_bon=id_provision WHERE o.id NOT IN (SELECT id_oblig FROM action) AND o.status='منجزة' AND o.date <= date_add(curdate(),interval -17 day)";
        return query;
    }
    
}
