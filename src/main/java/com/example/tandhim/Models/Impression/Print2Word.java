package com.example.tandhim.Models.Impression;

import com.example.tandhim.DOCXModifier.DOCXModifier;
import com.example.tandhim.DOCXModifier.ToDOCX;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

public class Print2Word {
    String docxModels;
    HashMap<String, String> marginInformation;
    HashMap<String, String> modelInformation;

    int obligRank;

    private final String qrCodePath;
    private final String docxName;
    private final String numBon;

    public Print2Word(String docxModels, HashMap<String, String> marginInformation, HashMap<String, String> modelInformation, int obligRank) throws IOException {
        this.docxModels = docxModels;
        this.marginInformation = marginInformation;
        this.modelInformation = modelInformation;
        this.obligRank = obligRank;
        this.numBon = getNumBon();
        qrCodePath = generateQRCodePath();
        docxName = generateDOCXName();
        generateQRCode();
        cleanMedia(docxModels.toString());
    }
    private String generateQRCodePath() {
        return String.format("docxModules/%s/word/media/image1.png", docxModels.toString().toLowerCase());
    }

    private String generateDOCXName() {
        String[] splitNumBon = this.numBon.split("/");

        return String.format("%s_%s_%s_%d", docxModels.toString().toLowerCase().split("_")[0], splitNumBon[0], splitNumBon[1], obligRank);
    }

    private String getNumBon() {
        return modelInformation.get("@num_bon");
    }

    private void generateQRCode() throws IOException {
        File outQRCode = new File(qrCodePath);
        BufferedImage bufferedQRCode = getQRCode(numBon, 150, 150);
        ImageIO.write(bufferedQRCode, "png", outQRCode);
    }

    private BufferedImage getQRCode(String targetUrl, int width,
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


    /** sometimes when generating QRCode it creates new files in media folder, so we clean this folder from any unnecessary files */
    private void cleanMedia(String folderOf) {
        String path = String.format("docxModules/%s/word/media/", folderOf);
        File mediaFolder = new File(path);

        // Files list inside media folder
        File[] fileList = mediaFolder.listFiles();

        if (fileList != null) {
            for (File file : fileList) {
                String fileName = file.getName();
                if (!(fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".svg")))
                    file.delete(); // if the file is not .png .jpg .jpeg .svg then delete file from media folder
            }
        }

    }



    public void replaceParameters() {
        HashMap<String, String> replacements = new HashMap<>();
        replacements.putAll(marginInformation);
        replacements.putAll(modelInformation);

        DOCXModifier docxModifier = new DOCXModifier(replacements);
        docxModifier.replace(docxModels); // docxModules/"fileOf"/word/document.xml _> docxModules/modbon/word/document.xml

        try {
            ToDOCX.zipFile(docxModels, docxName, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
