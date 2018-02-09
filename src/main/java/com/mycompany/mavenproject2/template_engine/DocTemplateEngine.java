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
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.mycompany.mavenproject2.util.Const;
import com.mycompany.mavenproject2.entities.Person;

/**
 *
 * @author User
 */
public class DocTemplateEngine extends TemplateEngine {

    private HWPFDocument templateDoc;
    
    public DocTemplateEngine(String templateFileName) throws FileNotFoundException, IOException, InvalidFormatException {
        super(templateFileName);
    }

    @Override
    protected void init(FileInputStream templateInputStream) throws IOException {
        templateDoc = new HWPFDocument(templateInputStream);
    }

    @Override
    public void copyTemplateFile(OutputStream generatedOutputStream) throws FileNotFoundException, IOException {
        templateDoc.write(generatedOutputStream);
    }

    @Override
    public void replaceText(String generatedFilename, Person person) throws FileNotFoundException, IOException {
        FileInputStream generatedFileInputStream = new FileInputStream(generatedFilename);
        HWPFDocument doc = new HWPFDocument(generatedFileInputStream);
        Range range = doc.getRange();
        range.replaceText(Const.LAST_NAME_TAG, person.getLastName());
        range.replaceText(Const.FIRST_NAME_TAG, person.getFirstName());
        range.replaceText(Const.MIDDLE_NAME_TAG, person.getMiddleName());
        OutputStream generatedOutputStream = new FileOutputStream(generatedFilename);
        doc.write(generatedOutputStream);
        generatedFileInputStream.close();
        generatedOutputStream.flush();
        generatedOutputStream.close();
    }

}
