/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import static java.lang.Compiler.command;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import org.apache.poi.util.Units;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
/**
 *
 * @author DELL
 */
public class impression {

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        impression im = new impression();
        im.imp("طه بن لحكل", "عبد الوهاب بن ثامر", "راني غير نسيي", 0, "4333");
        /*List<XWPFTable> tab =docx.getTables();
    String s =tab.getText();
    System.out.println(s);
    //Document Word vide
       /*    String client="رميد محمد عبد الكريم";
       String adv="بن ثامر مراد";
    XWPFDocument doc= new XWPFDocument();

    XWPFParagraph p= doc.createParagraph();
    XWPFParagraph p1= doc.createParagraph();
    XWPFParagraph p2= doc.createParagraph();
    XWPFParagraph p3= doc.createParagraph();
    p.setAlignment(ParagraphAlignment.CENTER);
    p1.setAlignment(ParagraphAlignment.LEFT);
    p2.setAlignment(ParagraphAlignment.LEFT);
    p3.setAlignment(ParagraphAlignment.LEFT);
    XWPFRun r= p.createRun();XWPFRun r1= p1.createRun();XWPFRun r2= p2.createRun();XWPFRun r3= p3.createRun();
    String imgFile = "D:/download-d/huissier.jpg";
    FileInputStream is = new FileInputStream(imgFile);

    r.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(300), Units.toEMU(200)); // 200x200 pixels

     r.addBreak();
    is.close();
      try
    {
    Connection bd = BDConnection.getConnection();
       String query = "SELECT d.nom, bs.num_bon , d.type_bon FROM bon_seances as bs, demandeur as d WHERE bs.num_bon=d.id_bon";
       Statement st = bd.createStatement();
       ResultSet rs = st.executeQuery(query);
        while (rs.next())
      {
        int id = rs.getInt("id");
        String nom = rs.getString("nom");
        String type = rs.getString("type_bon");
        String num = rs.getString("num._bon");

        // print the results
        System.out.println(nom+" "+type+" "+num);
      }
      st.close(); }   catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }

    rvsw rv = new rvsw();
      r.setText("وصل تسديد");r.setBold(true); r.setFontSize(48);r.setTextPosition(100);
    r1.setText("إسم الطالب : "+ client);
    r2.setText("إسم المطلوب :"+ " "+ adv);
     File fold = new File("D:/proj/");
     if(!fold.exists()){
     System.out.println("created");
     fold.mkdir();}
    //créer le document dans le chemin spécifique en lui donnant un nom
    File nv_fichier = new File("D:/proj/"+"bon.docx");
    FileOutputStream out = new FileOutputStream((nv_fichier));
    doc.write(out);
    out.close();
    System.out.println("Le document "+ nv_fichier +" a été créé avec succès");
         */
    }
    public void imp(String nom1, String nom2, String type, int montant, String num) throws FileNotFoundException, IOException {
        XWPFDocument docx = new XWPFDocument(
                new FileInputStream("D://proj/modbon.docx"));
        for (XWPFTable tbl : docx.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            System.out.println(r.getText(0) + " \n");
                            if (r.getText(0).contains("الطالب")) {
                                r.setText("الطالب : " + nom1, 0);
                            }
                            if (r.getText(0).contains("المطلوب")) {
                                r.setText(" المطلوب : " + nom2, 0);
                            }
                            if (r.getText(0).contains("الخدمة")) {
                                r.setText("الخدمة : " + type, 0);
                            }
                            if (r.getText(0).contains("المسدد")) {
                                Integer i = new Integer(montant);
                                String str = i.toString();
                                r.setText("المسدد : " + str, 0);
                                System.out.println(r.getText(0));
                            }

                        }
                    }
                }
            }
        }
        num = num.replace("/", "-");
        String st = "bon" + num + ".docx";
        File nv_fichier = new File("D://proj/" + st);
        FileOutputStream out = new FileOutputStream((nv_fichier));
        docx.write(out);
        out.close();
        System.out.println("Le document " + nv_fichier + " a été créé avec succès");
        Process p = Runtime.getRuntime().exec("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\winword.exe\" \"D:/proj/" + st + "\" /mFilePrintDefault /mfileexit");
    }

    public void imp2(String nom1, String nom2, String adr, String nomf) throws FileNotFoundException, IOException {
        XWPFDocument docx = new XWPFDocument(
                new FileInputStream("D://proj/" + nomf));
        for (XWPFTable tbl : docx.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {

                            System.out.println(r.getText(0) + " \n");
                            if (r.getText(0).contains("مكتب الأستاذ :")) {
                                r.setText("مكتب الأستاذ : " + nom1, 0);
                            }
                            if (r.getText(0).contains("مجلس قضاء البليدة ومحاكمه")) {
                                r.setText("مجلس قضاء " + nom2 + " ومحاكمه", 0);
                            }
                            if (r.getText(0).contains("الكائن مكتبنا :")) {
                                r.setText("الكائن مكتبنا : " + adr, 0);
                            }

                        }
                    }
                }
            }
        }
        for (XWPFParagraph p : docx.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                System.out.println(r);
                if (r.getText(0).contains("مكتب الأستاذ :/")) {
                    r.setText("مكتب الأستاذ :/" + nom1 + " محضر قضائي لدى اختصاص مجلس قضاء " + nom2 + " ومحاكمه، الكائن مكتبه " + adr, 0);
                }

            }
        }

        File nv_fichier = new File("D:/proj/" + nomf);
        FileOutputStream out = new FileOutputStream((nv_fichier));
        docx.write(out);
        out.close();
        System.out.println("Le document " + nv_fichier + " a été créé avec succès");
    }
}
