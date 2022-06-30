package com.example.tandhim.DOCXModifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ToDOCX {

    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;


    /**
     * @param folderOf name of the docx that you're working with ex: folderOf = modbon, so the hole path will be docxModules/modbon/
     * @param zipFile path to your zip folder
     * @param excludeContainingFolder if true then zip without root folder
     *
     *
     *                                example:  zipFile("modbon", "demo", true);
     *
     * */
    static public void zipFile(String folderOf, String zipFile, boolean excludeContainingFolder) throws IOException {
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        String fileToZip = String.format("docxModules/%s/", folderOf);
        File srcFile = new File(fileToZip);

        if(excludeContainingFolder && srcFile.isDirectory()) {
            for(String fileName : Objects.requireNonNull(srcFile.list())) {
                addToZip("", fileToZip + "/" + fileName, zipOut);
            }
        } else {
            addToZip("", fileToZip, zipOut);
        }

        zipOut.flush();
        zipOut.close();

        System.out.println("Successfully created " + zipFile);
        toDOCX(zipFile); // change extension form  to .docx
        copyOriginal(folderOf); // copy document.xml from "original" folder to to /docxModules/folderOf/word/
    }

    /**
     * copy document.xml from /original to /docxModules/{@code originalOf}/word/
     * @param originalOf name of the document that we're working with
     */
    static private void copyOriginal(String originalOf) throws IOException {
        String original_string = String.format("original/%s/document.xml", originalOf);
        String destination_string = String.format("docxModules/%s/word/document.xml", originalOf);

        Path originalPath = Paths.get(original_string);
        Path destination = Paths.get(destination_string);

        Files.copy(originalPath, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * @param zipFile path of your zip file
     * */
    static private void toDOCX(String zipFile) {
        String name = zipFile.split(Pattern.quote("."))[0] + ".docx";
        new File(zipFile).renameTo(new File(name));
    }

    static private void addToZip(String path, String srcFile, ZipOutputStream zipOut)
            throws IOException {
        File file = new File(srcFile);
        String filePath = "".equals(path) ? file.getName() : path + "/" + file.getName();
        if (file.isDirectory()) {
            for (String fileName : Objects.requireNonNull(file.list())) {
                addToZip(filePath, srcFile + "/" + fileName, zipOut);
            }
        } else {
            zipOut.putNextEntry(new ZipEntry(filePath));
            FileInputStream in = new FileInputStream(srcFile);

            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int len;
            while ((len = in.read(buffer)) != -1) {
                zipOut.write(buffer, 0, len);
            }
        }
    }
}

