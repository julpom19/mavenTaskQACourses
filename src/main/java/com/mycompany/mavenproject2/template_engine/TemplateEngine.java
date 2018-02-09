/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2.template_engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.mycompany.mavenproject2.entities.Person;

/**
 *
 * @author User
 */
public abstract class TemplateEngine {
    protected String templateFileName;

    public TemplateEngine(String templateFileName) throws FileNotFoundException, IOException, InvalidFormatException {
        this.templateFileName = templateFileName;
        FileInputStream templateInputStream = new FileInputStream(new File(templateFileName));
        init(templateInputStream);
    }
    
    protected abstract void init(FileInputStream templateInputStream) throws IOException, InvalidFormatException;
    
    public abstract void copyTemplateFile(OutputStream generatedOutputStream) throws FileNotFoundException, IOException;
    
    public abstract void replaceText(String generatedFilename, Person person) throws FileNotFoundException, IOException, InvalidFormatException;
    
}
