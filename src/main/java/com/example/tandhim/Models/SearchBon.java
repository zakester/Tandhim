/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author DELL
 */
public class SearchBon {

    private String num_bon, num_case, status, date, type, date_fin,demandeur,obligatoire;

    public SearchBon(String num_bon, String num_case, String status, String date, String type, String date_fin, String demandeur, String obligatoire) {
        this.num_bon = num_bon;
        this.num_case = num_case;
        this.status = status;
        this.date = date;
        this.type = type;
        this.date_fin = date_fin;
        this.demandeur = demandeur;
        this.obligatoire = obligatoire;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public StringProperty  getObligatoireProperty() {
        return new SimpleStringProperty (obligatoire);
    }
    public StringProperty getDemandeurProperty() {
        
        return new SimpleStringProperty (demandeur);
    }

    public String getObligatoire() {
        return obligatoire;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public void setObligatoire(String obligatoire) {
        this.obligatoire = obligatoire;
    }

    static public String QuerySearch(String demandeur,String obligatoir,String numBon,String tableName,String numCaseAttribut,String type) {
        String query="";
        if (numBon.equals("") && obligatoir.equals("") && !demandeur.equals("")) {
            query = "SELECT num_bon,obligatoire.status,created_at,"+numCaseAttribut+" AS num_case,obligatoire.date AS date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,obligatoire.nom AS nom_obligatoire FROM "+tableName+",demandeur,obligatoire WHERE num_bon IN (SELECT id_bon FROM demandeur WHERE nom LIKE '%" + demandeur + "%') AND demandeur.id_bon="+tableName+".num_bon AND obligatoire.id_bon="+tableName+".num_bon";
        } else if (demandeur.equals("") && obligatoir.equals("") && !numBon.equals("")) {
            query = "SELECT num_bon,obligatoire.status,created_at,"+numCaseAttribut+" AS num_case,obligatoire.date AS date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,obligatoire.nom AS nom_obligatoire FROM "+tableName+",demandeur,obligatoire WHERE num_bon='" + numBon + "' AND demandeur.id_bon="+tableName+".num_bon AND obligatoire.id_bon="+tableName+".num_bon";
        } else if (obligatoir.equals("") && !demandeur.equals("") && !numBon.equals("")) {
            query = "SELECT num_bon,obligatoire.status,created_at,"+numCaseAttribut+" AS num_case,obligatoire.date AS date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,obligatoire.nom AS nom_obligatoire FROM "+tableName+",demandeur,obligatoire WHERE num_bon='" + numBon + "' AND num_bon IN (SELECT id_bon FROM demandeur WHERE nom LIKE '%" + demandeur + "%') AND demandeur.id_bon="+tableName+".num_bon AND obligatoire.id_bon="+tableName+".num_bon";
        } else if (!obligatoir.equals("") && demandeur.equals("") && numBon.equals("")) {
            query = "SELECT num_bon,obligatoire.status,created_at,"+numCaseAttribut+" AS num_case,obligatoire.date AS date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,obligatoire.nom AS nom_obligatoire FROM "+tableName+",demandeur,obligatoire WHERE num_bon IN (SELECT id_bon FROM obligatoire WHERE nom LIKE '%" + obligatoir + "%') AND demandeur.id_bon="+tableName+".num_bon AND obligatoire.id_bon="+tableName+".num_bon";
        } else if (!obligatoir.equals("") && !demandeur.equals("") && numBon.equals("")) {
            query = "SELECT num_bon,obligatoire.status,created_at,"+numCaseAttribut+" AS num_case,obligatoire.date AS date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,obligatoire.nom AS nom_obligatoire FROM "+tableName+",demandeur,obligatoire WHERE num_bon IN (SELECT id_bon FROM demandeur WHERE nom LIKE '%" + demandeur + "%') AND num_bon IN (SELECT id_bon FROM obligatoire WHERE nom LIKE '%" + obligatoir + "%') AND demandeur.id_bon="+tableName+".num_bon AND obligatoire.id_bon="+tableName+".num_bon";
        } else if (!obligatoir.equals("") && demandeur.equals("") && !numBon.equals("")) {
            query = "SELECT num_bon,obligatoire.status,created_at,"+numCaseAttribut+" AS num_case,obligatoire.date AS date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,obligatoire.nom AS nom_obligatoire FROM "+tableName+",demandeur,obligatoire WHERE num_bon='" + numBon + "'  AND num_bon IN (SELECT id_bon FROM obligatoire WHERE nom LIKE '%" + obligatoir + "%') AND demandeur.id_bon="+tableName+".num_bon AND obligatoire.id_bon="+tableName+".num_bon";
        } else if (!obligatoir.equals("") && !demandeur.equals("") && !numBon.equals("")) {
            query = "SELECT num_bon,obligatoire.status,created_at,"+numCaseAttribut+" AS num_case,obligatoire.date AS date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,obligatoire.nom AS nom_obligatoire FROM "+tableName+",demandeur,obligatoire WHERE num_bon='" + numBon + "' AND num_bon IN (SELECT id_bon FROM demandeur WHERE nom LIKE '%" + demandeur + "%') AND num_bon IN (SELECT id_bon FROM obligatoire WHERE nom LIKE '%" + obligatoir + "%') AND demandeur.id_bon="+tableName+".num_bon AND obligatoire.id_bon="+tableName+".num_bon";
        }else{
            query="SELECT num_bon,obligatoire.status,created_at,"+numCaseAttribut+" AS num_case,obligatoire.date AS date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,obligatoire.nom AS nom_obligatoire FROM "+tableName+",demandeur,obligatoire WHERE demandeur.id_bon="+tableName+".num_bon AND obligatoire.id_bon="+tableName+".num_bon";
        }
        
        return query;
    }
    
    static public String QuerySearch(String demandeur,String numBon,String tableName,String numCaseAttribut,String type) {
        String query="";
        if (numBon.equals("") && !demandeur.equals("")) {
            query = "SELECT num_bon,"+tableName+".status,created_at,"+numCaseAttribut+" AS num_case,date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,trim('////') AS nom_obligatoire FROM "+tableName+",demandeur WHERE num_bon IN (SELECT id_bon FROM demandeur WHERE nom LIKE '%" + demandeur + "%') AND demandeur.id_bon="+tableName+".num_bon";
        } else if (demandeur.equals("") && !numBon.equals("")) {
            query = "SELECT num_bon,"+tableName+".status,created_at,"+numCaseAttribut+" AS num_case,date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,trim('////') AS nom_obligatoire FROM "+tableName+",demandeur WHERE num_bon='" + numBon + "' AND demandeur.id_bon="+tableName+".num_bon";
        } else if (!demandeur.equals("") && !numBon.equals("")){
            query = "SELECT num_bon,"+tableName+".status,created_at,"+numCaseAttribut+" AS num_case,date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,trim('////') AS nom_obligatoire FROM "+tableName+",demandeur WHERE num_bon='" + numBon + "' AND id IN (SELECT id_bon FROM demandeur WHERE nom LIKE '%" + demandeur + "%') AND demandeur.id_bon="+tableName+".num_bon";
        } else{
            query = "SELECT num_bon,"+tableName+".status,created_at,"+numCaseAttribut+" AS num_case,date_fin,trim('"+type+"') as type,demandeur.nom AS nom_demandeur,trim('////') AS nom_obligatoire FROM "+tableName+",demandeur WHERE demandeur.id_bon="+tableName+".num_bon";
        }
        return query;
    }

    public String getNum_bon() {
        return num_bon;
    }
    public StringProperty getNum_bonProperty() {
        return new SimpleStringProperty (num_bon);
    }

    public SearchBon(String num_bon, String num_case, String status, String date, String type, String date_fin) {
        this.num_bon = num_bon;
        this.num_case = num_case;
        this.status = status;
        this.date = date;
        this.type = type;
        this.date_fin = date_fin;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public StringProperty getDate_finProperty() {
        return new SimpleStringProperty (date_fin);
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setNum_bon(String num_bon) {
        this.num_bon = num_bon;
    }

    public String getNum_case() {
        return num_case;
    }
    public StringProperty getNum_caseProperty() {
        return new SimpleStringProperty (num_case);
    }

    public void setNum_case(String num_case) {
        this.num_case = num_case;
    }

    public String getStatus() {
        return status;
    }

    public StringProperty getStatusProperty() {
        return new SimpleStringProperty (status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public StringProperty getDateProperty() {
        return new SimpleStringProperty (date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public StringProperty getTypeProperty() {
        return new SimpleStringProperty(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public SearchBon(String num_bon, String num_case, String status, String date, String type) {
        this.num_bon = num_bon;
        this.num_case = num_case;
        this.status = status;
        this.date = date;
        this.type = type;
    }

}
