/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.Hashtable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.ViewBox;

import javax.imageio.ImageIO;

public class impression {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

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

    /** Create png QR code */
    public static void QRCodePNG(String output, String data, int width, int height) throws IOException {
        File outQRCode = new File(output);
        BufferedImage bufferedQRCode = getQRCode(data, width, height);
        ImageIO.write(bufferedQRCode, "png", outQRCode);
    }

    public void PrintBon(String requester, String wanted, String type, String price, String bonNumber) throws IOException {
        bonNumber = bonNumber.replace("/", "-");
        String docxName = "bon" + bonNumber;

        QRCodePNG("docxModules/modbon/word/media/image1.png", "some data", 150, 150);

        String pythonScript = String.format("cd docxModules/modbon/ && python main.py --requester \"%s\" --wanted \"%s\" --service \"%s\" --price \"%s\" --rest \"%s\" --docxName \"%s\"", requester, wanted, type, price, "0 DA", docxName);
        Process p = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", pythonScript });
    }

}