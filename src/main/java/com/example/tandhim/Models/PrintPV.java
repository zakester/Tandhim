/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author DELL
 */
public class PrintPV {

    public String PrintPVSeance(ObservableList<String> dem, String obl, String addr1, String type, String avocat, String num_seance, String num_bon, String date, String date1, String date2, String commission, String spec, String salle, String branche, String heure, int indice, int pvType) {

        XWPFDocument docx = null;
        try {
            if (pvType == 1) {
                docx = new XWPFDocument(
                        new FileInputStream("D://proj//modseanceper.docx"));
            } else {
                docx = new XWPFDocument(
                        new FileInputStream("D://proj//modseanceper1.docx"));
            }

        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (XWPFParagraph p : docx.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                
                System.out.println(r.getText(0));
                if (r.getText(0).contains("لــــــــفائدة :")) {
                    String s = "";
                    for (int i = 0; i < dem.size(); i++) {
                        s = s + " " + (i + 1) + " -" + dem.get(i);
                    }
                    r.setText("لــــــــفائدة :" + s, 0);
                    // System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("القائم في حقه")) {
                    r.setText("القائم في حقه (ا) : " + avocat, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("أولا:/ سلمنا التكليف بالحضور لجلسة المرفق بعريضة .... رقم ....")) {
                    r.setText("أولا:/ سلمنا التكليف بالحضور لجلسة المرفق بعريضة  " + type + " رقم  " + num_seance, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (date1 == null || date1.equals("")) {
                    if (r.getText(0).contains("للحضور لجلسة.")) {
                        r.setText("للحضور لجلسة يوم : " + date + "، قضية رقم : " + num_seance + "، والتي ستعقد أمام : " + commission + "، القسم: " + spec + "، على الساعة : " + heure + "،قاعة: " + salle + "،فرع: " + branche + ".", 0);
                        //System.out.println(r.getText(0)+" \n");
                    }
                } else {
                    if (date2 == null || date2.equals("")) {
                        if (r.getText(0).contains("للحضور لجلسة.")) {
                            r.setText("للحضور لجلسة يوم : " + date + "،والمؤجلة ليوم: " + date1 + "، قضية رقم : " + num_seance + "، والتي ستعقد أمام : " + commission + "، القسم: " + spec + "، على الساعة : " + heure + "،قاعة: " + salle + "،فرع: " + branche + ".", 0);
                            //System.out.println(r.getText(0)+" \n");
                        }
                    } else {
                        if (r.getText(0).contains("للحضور لجلسة.")) {
                            r.setText("للحضور لجلسة يوم : " + date + "،والمؤجلة ليوم: " + date1 + "،والمؤجلة ليوم: " + date2 + "، قضية رقم : " + num_seance + "، والتي ستعقد أمام : " + commission + "، القسم: " + spec + "، على الساعة : " + heure + "،قاعة: " + salle + "،فرع: " + branche + ".", 0);
                            //System.out.println(r.getText(0)+" \n");
                        }
                    }
                }
                if (r.getText(0).contains("بلغنا :")) {
                    r.setText("بلغنا : " + obl, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("وصل رقم :")) {
                    r.setText("وصل رقم :" + num_bon, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("ضد :")) {
                    r.setText("ضد : " + obl, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("العنــــــوان :")) {
                    r.setText("العنــــــوان : " + addr1, 0);
                    //System.out.println(r.getText(0)+" \n");
                }

            }
        }
        for (XWPFTable tbl : docx.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            if (r.getText(0).contains("وصل رقم :")) {
                                r.setText("وصل رقم :" + num_bon, 0);
                            }
                        }
                    }
                }
            }
        }

        num_bon = num_bon.replace("/", "-");
        String[] d = dem.get(0).split(" العنوان: ");
        String st = "citation num- " + num_bon + " contre- " + indice + ".docx";
        File nv_fichier = new File("D:/proj/" + st);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream((nv_fichier));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            docx.write(out);
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le document " + nv_fichier + " a été créé avec succès");
        try {
            System.out.println("D:/proj/" + st);
            Process p = Runtime.getRuntime().exec("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\winword.exe\" \"" + nv_fichier + "\" /mFilePrintDefault /mfileexit");
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";

    }

    public String PrintPVexcuse(ArrayList<String> dem, String obl, String addr1, String type, String avocat, String date, String num_bon, String langue, int indice, int pvType) {

        XWPFDocument docx = null;
        try {
            if (pvType == 1) {
                docx = new XWPFDocument(
                        new FileInputStream("D://proj//demande.docx"));
            }
            if (pvType == 2) {
                docx = new XWPFDocument(
                        new FileInputStream("D://proj//demande1.docx"));
            }
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (XWPFParagraph p : docx.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                System.out.println("new run : " + r.getText(0));
                if (r.getText(0).contains("بناء على طلب : ")) {
                    String s = "";
                    for (int i = 0; i < dem.size(); i++) {
                        s = s + " " + (i + 1) + " -" + dem.get(i);
                    }
                    r.setText("بناء على طلب : " + s, 0);
                    // System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("القائم في حقه : ")) {
                    r.setText("القائم في حقه :(ا) " + avocat, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains(" والمتضمن : تبليغ اعذار .")) {
                    r.setText("والمتضمن : تبليغ " + type, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("محضــــــــــــــــــر تبليغ إعذار")) {
                    r.setText("محضــــــــــــــــــر تبليغ " + type, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("موجه إلى :")) {
                    r.setText("إلى : " + obl, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("المتضمن ما جاء")) {
                    r.setText("المتضمـن: « ما جاء في النسخة المرفقة بمحضر التبليغ والمحررة باللغة :  " + langue + " والمؤشر عليها من طرفنا نحن المحضر القضائي بتاريخ : " + date + " ».", 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("بلغنا:")) {
                    r.setText("بلغنــــــــــــا:  " + obl, 0);
                    System.out.println(r.getText(0) + " \n");
                }
                if (r.getText(0).contains("العنوان :")) {
                    r.setText(" العنوان  ب :    " + addr1, 0);
                    System.out.println(r.getText(0) + " \n");
                }

            }
        }
        for (XWPFTable tbl : docx.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            if (r.getText(0).contains("وصل رقم :")) {
                                r.setText("وصل رقم :" + num_bon, 0);
                            }
                        }
                    }
                }
            }
        }

        num_bon = num_bon.replace("/", "-");
        String[] d = dem.get(0).split(" العنوان: ");
        String st = "demande- " + num_bon + " contre- " + indice + ".docx";
        File nv_fichier = new File("D:/proj/" + st);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream((nv_fichier));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            docx.write(out);
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le document " + nv_fichier + " a été créé avec succès");
        try {
            System.out.println("D:/proj/" + st);
            Process p = Runtime.getRuntime().exec("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\winword.exe\" \"D:/proj/" + nv_fichier + "\" /mFilePrintDefault /mfileexit");
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";

    }

    public String PrintPVjugement(ArrayList<String> dem, String obl, String addr1, String avocat, String commission, String num_indice, String table, String date, String typeJugement, String num_bon, String type, int indice, int pvType) {

        XWPFDocument docx = null;
        try {
            if (typeJugement.equals("غيابي")) {
                typeJugement = "الغيابي";
                if (pvType == 1) {
                    docx = new XWPFDocument(
                            new FileInputStream("D://proj//jugement-abs.docx"));
                }
                if (pvType == 2) {
                    docx = new XWPFDocument(
                            new FileInputStream("D://proj//jugement-abs1.docx"));
                }
            } else {
                String[] typej = typeJugement.split(" ");
                typeJugement = "الـ" + typej[0];
                if (typej.length >= 2) {
                    typeJugement = "الـ" + typej[0] + " ال" + typej[1];
                }
                if (typej.length == 3) {
                    typeJugement = typeJugement + " " + typej[2];
                }
                if (pvType == 1) {
                    docx = new XWPFDocument(
                            new FileInputStream("D://proj//jugement-pres.docx"));
                }
                if (pvType == 2) {
                    docx = new XWPFDocument(
                            new FileInputStream("D://proj//jugement-pres1.docx"));
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (XWPFParagraph p : docx.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                System.out.println(r.getText(0));
                if (r.getText(0).contains("بناء على طلب :")) {
                    String s = "";
                    for (int i = 0; i < dem.size(); i++) {
                        s = s + " " + (i + 1) + " -" + dem.get(i);
                    }
                    r.setText("بناء على طلب : " + s, 0);
                    // System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("القائم في حقه :")) {
                    r.setText("القائم في حقه (ا) :" + avocat, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("بلغنا وسلمنا نسخة من ")) {
                    r.setText("بلغنا وسلمنا نسخة من الحكم " + typeJugement + " الصادر عن " + commission + "، " + type + " بتاريخ: " + date + "، رقم الفهرس: " + num_indice + "، رقم الجدول: " + table, 0);
                    //System.out.println(r.getText(0)+" \n");
                }

                if (r.getText(0).contains("بلغنا :")) {
                    r.setText("بلغنــــــــــــا:  " + obl, 0);
                    System.out.println(r.getText(0) + " \n");
                }
                if (r.getText(0).contains("العنوان :")) {
                    r.setText(" العنوان  ب :    " + addr1, 0);
                    System.out.println(r.getText(0) + " \n");
                }

            }
        }

        for (XWPFTable tbl : docx.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            if (r.getText(0).contains("وصل رقم")) {
                                r.setText("وصل رقم :" + num_bon, 0);
                            }
                        }
                    }
                }
            }
        }

        num_bon = num_bon.replace("/", "-");
        String[] d = dem.get(0).split(" العنوان: ");
        String st = "jugement " + num_bon + " contre-" + indice + ".docx";
        File nv_fichier = new File("D:/proj/" + st);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream((nv_fichier));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            docx.write(out);
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le document " + nv_fichier + " a été créé avec succès");
        try {
            System.out.println("D:/proj/" + st);
            Process p = Runtime.getRuntime().exec("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\winword.exe\" \"D:/proj/" + nv_fichier + "\" /mFilePrintDefault /mfileexit");
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";

    }

    public String PrintPVdecision(ArrayList<String> dem, String obl, String addr1, String avocat, String commission, String num_indice, String table, String date, String typeJugement, String num_bon, String type, int indice, int pvType) {

        XWPFDocument docx = null;
        try {
            if (typeJugement.equals("غيابي")) {
                typeJugement = "الغيابي";
                if (pvType == 1) {
                    docx = new XWPFDocument(
                            new FileInputStream("D://proj//decision-abs.docx"));
                }
                if (pvType == 2) {
                    docx = new XWPFDocument(
                            new FileInputStream("D://proj//decision-abs1.docx"));
                }
            } else {
                String[] typej = typeJugement.split(" ");
                typeJugement = "الـ" + typej[0];
                if (typej.length >= 2) {
                    typeJugement = "الـ" + typej[0] + " ال" + typej[1];
                }
                if (typej.length == 3) {
                    typeJugement = typeJugement + " " + typej[2];
                }
                if (pvType == 1) {
                    docx = new XWPFDocument(
                            new FileInputStream("D://proj//decision-pres.docx"));
                }
                if (pvType == 2) {
                    docx = new XWPFDocument(
                            new FileInputStream("D://proj//decision-pres1.docx"));
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (XWPFParagraph p : docx.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                System.out.println(r.getText(0));
                if (r.getText(0).contains("بناء على طلب :")) {
                    String s = "";
                    for (int i = 0; i < dem.size(); i++) {
                        s = s + " " + (i + 1) + " -" + dem.get(i);
                    }
                    r.setText("بناء على طلب : " + s, 0);
                    // System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("القائم في حقه :")) {
                    r.setText("القائم في حقه (ا) :" + avocat, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("بلغنا وسلمنا نسخة من")) {
                    r.setText("بلغنا وسلمنا نسخة من القرار " + typeJugement + " الصادر عن " + commission + "، " + type + " بتاريخ: " + date + "، رقم الفهرس: " + num_indice + "، رقم الجدول: " + table, 0);
                    //System.out.println(r.getText(0)+" \n");
                }

                if (r.getText(0).contains("بلغنا :")) {
                    r.setText("بلغنــــــــــــا:  " + obl, 0);
                    System.out.println(r.getText(0) + " \n");
                }
                if (r.getText(0).contains("العنوان :")) {
                    r.setText(" العنوان  ب :    " + addr1, 0);
                    System.out.println(r.getText(0) + " \n");
                }

            }
        }

        for (XWPFTable tbl : docx.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            if (r.getText(0).contains("وصل رقم")) {
                                r.setText("وصل رقم :" + num_bon, 0);
                            }
                        }
                    }
                }
            }
        }

        num_bon = num_bon.replace("/", "-");
        String[] d = dem.get(0).split(" العنوان: ");
        String st = "decision " + num_bon + " contre-" + indice + ".docx";
        File nv_fichier = new File("D:/proj/" + st);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream((nv_fichier));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            docx.write(out);
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Le document " + nv_fichier + " a été créé avec succès");
        try {
            System.out.println("D:/proj/" + st);
            Process p = Runtime.getRuntime().exec("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\winword.exe\" \"D:/proj/" + nv_fichier + "\" /mFilePrintDefault /mfileexit");
        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";

    }

    public String PrintPVdebutExecution(ArrayList<String> dem, String obl, String addr1, String avocat, String commission, String num_indice, String table, String date, String sanad, String num_bon, String type, String num_notif, String date_notif, ArrayList<String> prices, int indice, int pvType) {

        XWPFDocument docx = null;
        try {
            if (pvType == 1) {
                docx = new XWPFDocument(
                        new FileInputStream("D://proj//debut-exe.docx"));
            }
            if (pvType == 2) {
                docx = new XWPFDocument(
                        new FileInputStream("D://proj//debut-execution1.docx"));
            }

        } catch (IOException ex) {
            Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (XWPFParagraph p : docx.getParagraphs()) {
                    //System.out.println(r.getText(0)+" \n");
                
            for (XWPFRun r : p.getRuns()) {

                 System.out.println(r.getText(0)+" \n");
                if (r.getText(0).contains("لفائدة :")) {
                    String s = "";
                    for (int i = 0; i < dem.size(); i++) {
                        s = s + " " + (i + 1) + " -" + dem.get(i);
                    }
                    r.setText("لفائدة : " + s, 0);
                    //System.out.println(r.getText(0)+" \n");
                }
                if (r.getText(0).contains("بمقتضى الحكم الصادر عن ")) {
                    r.setText("بمقتضى " + sanad + " الصادر عن " + commission + "، " + type + " بتاريخ: " + date + "، رقم الفهرس: " + num_indice + "، رقم الجدول: " + table +" والممهور بالصيغة التنفيذية رقم : " + num_notif+" الصادرة بتاريخ : "+date_notif, 0);
                    //System.out.println(r.getText(0)+" \n");
                }

                if (r.getText(0).contains("القائم في حقه :")) {
                    r.setText("القائم في حقه (ا) :" + avocat, 0);
                    System.out.println(r.getText(0) + " \n");
                }
                if (r.getText(0).contains("بلغنا :")) {
                    r.setText("بلغنــــــــــــا:  " + obl, 0);
                    System.out.println(r.getText(0) + " \n");
                }

                if (r.getText(0).contains("كلفنا :")) {
                    r.setText("كلـــفنـــا :" + obl, 0);
                    System.out.println(r.getText(0) + " \n");
                }
                if (r.getText(0).contains("العنوان :")) {
                    r.setText(" العنوان  ب :    " + addr1, 0);
                    System.out.println(r.getText(0) + " \n");
                }
                if (r.getText(0).contains("وصل رقم")) {
                    r.setText("وصل رقم :" + num_bon, 0);
                }
                if (r.getText(0).contains("المبلغ المحكوم به :")) {
                    r.setText("المبلغ المحكوم به :" + prices.get(0), 0);
                    prices.get(0);
                }
                if (r.getText(0).contains("الحق التناسبي :")) {
                    r.setText("الحق التناسبي :" + prices.get(1), 0);
                }
                if (r.getText(0).contains("المصاريف القضائية :")) {
                    r.setText("المصاريف القضائية :" + prices.get(2), 0);
                }
                if (r.getText(0).contains("ليصبح المجموع : ")) {
                    r.setText("ليصبح المجموع : " + prices.get(3), 0);
                }
            }
        }
        

            num_bon = num_bon.replace("/", "-");
            String[] d = dem.get(0).split(" العنوان: ");
            String st = "debut-execution num-" + num_bon + " contre- " + indice + ".docx";
            File nv_fichier = new File("D:/proj/" + st);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream((nv_fichier));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                docx.write(out);
            } catch (IOException ex) {
                Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Le document " + nv_fichier + " a été créé avec succès");
            try {
                System.out.println("D:/proj/" + st);
                Process p = Runtime.getRuntime().exec("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\winword.exe\" \"D:/proj/" + nv_fichier + "\" /mFilePrintDefault /mfileexit");
            } catch (IOException ex) {
                Logger.getLogger(PrintPV.class.getName()).log(Level.SEVERE, null, ex);
            }

            return "";

        }
    }
