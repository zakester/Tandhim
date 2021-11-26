/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
//import static java.lang.Compiler.command;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Scanner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import it.grabz.grabzit.GrabzItClient;
import it.grabz.grabzit.GrabzItException;
import it.grabz.grabzit.parameters.DOCXOptions;
import jep.JepConfig;
import jep.JepException;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.ViewBox;
import jep.Jep;
import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthOptionPaneUI;

public class impression {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        impression im = new impression();
        im.PrintBon("sdmk","sklnnf","12434",34555,"sdfqsd");
    }

    public static BufferedImage getQRCode(String targetUrl, int width,
                                          int height) {
        try {
            Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();

            hintMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(targetUrl,
                    BarcodeFormat.QR_CODE, width, height, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();

            BufferedImage image = new BufferedImage(CrunchifyWidth,
                    CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            return image;
        } catch (WriterException e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting QR Code");
        }

    }

    public static String getQRCodeSvg(String targetUrl, int width, int height, boolean withViewBox) {
        SVGGraphics2D g2 = new SVGGraphics2D(width, height);
        BufferedImage qrCodeImage = getQRCode(targetUrl, width, height);
        g2.drawImage(qrCodeImage, 0, 0, width, height, null);

        ViewBox viewBox = null;
        if (withViewBox) {
            viewBox = new ViewBox(0, 0, width, height);
        }
        return g2.getSVGElement(null, true, viewBox, null, null);
    }

    public void PrintBon(String dem, String oblig, String type, int montant, String num) throws IOException {
        num= num.replace("/","-");
        String html1 = "<html>\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <title>وصـــــــل التـــــسديــد</title>\n" +
                "    <style>\n" +
                "        div .btn-scretaria{\n" +
                "            background-color: #f2f0f0;\n" +
                "            font-weight: bold;\n" +
                "            border: 1px solid #f2f0f0;\n" +
                "            display: inline-block;\n" +
                "            padding: 0 10px;\n" +
                "            margin-left: 15px;\n" +
                "            float: left;\n" +
                "            margin-top: 50px;\n" +
                "            box-shadow: 2px 2px 2px gray;\n" +
                "        }\n" +
                "        table {\n" +
                "            \n" +
                "            padding-right: 70 px;\n" +
                "            width: 100%;\n" +
                "                    \n" +
                "        }\n" +
                "        tr {\n" +
                "            direction: ltr;\n" +
                "        }\n" +
                "        main {\n" +
                "            orientation: rtl;\n" +
                "        }\n" +
                "        td {\n" +
                "            height: 70 px;\n" +
                "            text-align: right;\n" +
                "            font-size: large;\n" +
                "            direction: rtl;\n" +
                "        }\n" +
                "        td .col2{\n" +
                "            width: 30%;\n" +
                "            height: 70 px;\n" +
                "            text-align: right;\n" +
                "            font-size: large;\n" +
                "            direction: rtl;\n" +
                "        }\n" +
                "    </style>\n" +
                "    <header>\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <th>"
                +                getQRCodeSvg("lien vers"+num+".com",200,200,true)  +
                "                </th>\n" +
                "                <th><p>مكتب الأستاذ : بن ثامر دحمان</p>\n" +
                "                    <p>محضر قضائي لدى اختصاص مجلس قضاء البليدة ومحاكمه<p>\n" +
                "                    <p>الكائن مكتب : بشارع 11 ديسمبر 1960 البليدة (مقابل مجلس قضاء البليدة)</p>\n" +
                "              </th>\n" +
                "            <th>\n" +
                "                <svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:jfreesvg=\"http://www.jfree.org/jfreesvg/svg\" width=\"250\" height=\"250\" viewBox=\"0 0 250 250\" text-rendering=\"auto\" shape-rendering=\"auto\">\n" +
                "                    <defs></defs>\n" +
                "                    <image preserveAspectRatio=\"none\" xlink:href=\"data:image/png;base64,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\"  transform=\"matrix(1,0,0,1,0,0)\" x=\"0\" y=\"0\" width=\"250\" height=\"250\"/>\n" +
                "                    </svg>   \n"+
                "\n" +
                "              </th>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    <body>\n" +
                "        <main>\n" +
                "            <div style=\"margin-right:50px;margin-left:50px;\">\n" +
                "            <table>\n" +
                "            <tr>\n" +
                "                <td class=\"col2\">\n" +
                "                    <p>نوع الخدمة : </p>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <p>إسم الطالب : </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td class=\"col2\">\n" +
                "                    <p>المبلغ المسدد :  الباقي : </p>\n" +
                "                    </td>\n" +
                "                <td>\n" +
                "                    <p>إسم المطلوب : </p>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <div class=\"btn-scretaria\">\n" +
                "                    <p>الأمانة</p>\n" +
                "                </div>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "    \n" +
                "                </table>\n" +
                "            </div>\n" +
                "             </main>\n" +
                "    </body>          \n" +
                "    </header>\n" +
                "</html>";
        String Script ="import pdfkit\n" +
                "pdfkit.from_file('bon"+num+".html', 'bon"+num+".pdf')";
        try {
            File targetFile = new File("bon"+num+".html");
            File targetFile2 = new File("script.py");
            Files.write(Path.of("bon" + num + ".html"),html1.getBytes());
            Files.write(Path.of("script.py"),Script.getBytes());
            Process p = Runtime.getRuntime().exec("python script.py");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}