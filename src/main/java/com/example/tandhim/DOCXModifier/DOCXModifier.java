package com.example.tandhim.DOCXModifier;

import java.io.*;
import java.util.HashMap;

public class DOCXModifier {

    private final HashMap<String, String> replacements;
    /**
     * @param replacements <key, value>, keys are { @requester, @wanted, @price.... }
     *
     *                     example:
     *                                  DOCXModifier modifier = new DOCXModifier(new HashMap<String, String> {{
     *                                      put("@requester", "some name");
     *                                      put("@wanted", "some name");
     *                                      ....
     *                                  }});
     *                                  modifier.replace("modbon");
     *
     * */
    public DOCXModifier(HashMap<String, String> replacements) {
        this.replacements = replacements;
    }

    /**
     * This function replace old values with new values from HashMap<String, String> replacements
     * @param fileOf name of the docx that you're working with ex: fileOf = modbon, so the hole path will be docxModules/modbon/word/document.xml
     * */
    public void replace(String fileOf) {

        String pathXML = String.format("docxModules/%s/word/document.xml", fileOf);
        String tmpXMLPath = String.format("docxModules/%s/word/tmp_document.xml", fileOf); // create temp XML file and modify it

        try (BufferedReader br = new BufferedReader(new FileReader(pathXML)); BufferedWriter bw = new BufferedWriter(new FileWriter(tmpXMLPath))) {
            String line;

            while ((line = br.readLine()) != null) {

                for (HashMap.Entry<String, String> entry : replacements.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();

                    if (line.contains(key))
                        line = line.replace(key, value);
                }

                bw.write(line);
            }

        } catch (Exception e) {
            return;
        }

        removeTmpFile(pathXML, tmpXMLPath); // after modification delete replace old XML with new XML

    }


    private void removeTmpFile(String inputFilePath, String tmpFilePath) {
        // Once everything is complete, delete old XML.
        File oldFile = new File(inputFilePath);
        oldFile.delete();

        // And rename tmp XML's name to old XML name
        File newFile = new File(tmpFilePath);
        newFile.renameTo(oldFile);
    }

}

