/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.util.Const;
import com.mycompany.mavenproject2.entities.Person;
import com.mycompany.mavenproject2.template_engine.DocTemplateEngine;
import com.mycompany.mavenproject2.template_engine.TemplateEngine;
import com.mycompany.mavenproject2.template_engine.DocxTemplateEngine;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author User
 */
public class DocumentsGenerator {
    public void generateDocuments(String filename, List<Person> persons) {
        try {
            TemplateEngine engine;
            String extension;
            if(filename.substring(filename.length() - 1).equals("x")){
                engine = new DocxTemplateEngine(filename);
                extension = Const.DOCX_EXTENSION;
            } else {
                engine = new DocTemplateEngine(filename);
                extension = Const.DOC_EXTENSION;
            }
            for (int i = 0; i < persons.size(); i++) {
                Person person = persons.get(i);
                
                String generatedFilename = Const.TEMPLATE_FILENAME + "_" + person.getLastName() + "_" + person.getFirstName() + "_" + person.getMiddleName() + extension;
                
                OutputStream generatedOutputStream = new FileOutputStream(generatedFilename);
                engine.copyTemplateFile(generatedOutputStream);
                generatedOutputStream.flush();
                generatedOutputStream.close();
                
                engine.replaceText(generatedFilename, person);
                System.out.println("Generated: " + generatedFilename);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QAAutomation_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QAAutomation_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(QAAutomation_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
