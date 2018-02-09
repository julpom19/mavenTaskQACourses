/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2.template_engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import com.mycompany.mavenproject2.util.Const;
import com.mycompany.mavenproject2.entities.Person;

/**
 *
 * @author User
 */
public class DocxTemplateEngine extends TemplateEngine {

    private XWPFDocument templateDocx;
    
    public DocxTemplateEngine(String templateFileName) throws FileNotFoundException, IOException, InvalidFormatException {
        super(templateFileName);
    }

    @Override
    protected void init(FileInputStream templateInputStream) throws IOException, InvalidFormatException {
        templateDocx = new XWPFDocument(OPCPackage.open(templateInputStream));
    }
   
    @Override
    public void copyTemplateFile(OutputStream generatedOutputStream) throws FileNotFoundException, IOException {
        templateDocx.write(generatedOutputStream);
    }

    @Override
    public void replaceText(String generatedFilename, Person person) throws FileNotFoundException, IOException, InvalidFormatException {
        FileInputStream generatedFileInputStream = new FileInputStream(generatedFilename);
        XWPFDocument docx = new XWPFDocument(OPCPackage.open(generatedFileInputStream));
        for (XWPFParagraph paragraph : docx.getParagraphs()) {
            for (XWPFRun r : paragraph.getRuns()) {
                String text = r.getText(0);
                if (text.contains(Const.LAST_NAME_TAG)) {
                    text = text.replace(Const.LAST_NAME_TAG, person.getLastName());
                    r.setText(text, 0);
                } 
                if (text.contains(Const.FIRST_NAME_TAG)) {
                    text = text.replace(Const.FIRST_NAME_TAG, person.getFirstName());
                    r.setText(text, 0);
                } 
                if (text.contains(Const.MIDDLE_NAME_TAG)) {
                    text = text.replace(Const.MIDDLE_NAME_TAG, person.getMiddleName());
                    r.setText(text, 0);
                }
            }
        }
        OutputStream generatedOutputStream = new FileOutputStream(generatedFilename);
        docx.write(generatedOutputStream);
        generatedFileInputStream.close();
        generatedOutputStream.flush();
        generatedOutputStream.close();
    }
    
    
    
}
