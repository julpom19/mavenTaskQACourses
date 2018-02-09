/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.util.Const;
import com.mycompany.mavenproject2.entities.Person;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.xml.sax.SAXException;

/**
 *
 * @author User
 */
public class QAAutomation_1 {
    public static void main(String[] args) {
        try {
            XmlParser xmlManager = new XmlParser();
            DocumentsGenerator documentsGenerator = new DocumentsGenerator();
            List<Person> persons = xmlManager.getPersons(Const.XML_DATA_PATH);
            String templateFilename = Const.TEMPLATE_FILENAME + Const.DOCX_EXTENSION;
            documentsGenerator.generateDocuments(templateFilename, persons);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(QAAutomation_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QAAutomation_1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(QAAutomation_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
