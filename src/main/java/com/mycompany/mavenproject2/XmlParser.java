/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.util.Const;
import com.mycompany.mavenproject2.entities.Person;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author User
 */
public class XmlParser {
    public List<Person> getPersons(String filepath) throws ParserConfigurationException, IOException, SAXException {
        File f = new File(filepath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(f);
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                 Element elem = (Element) node;
                 String firstName = elem.getElementsByTagName(Const.FIRST_NAME).item(0)
                                     .getChildNodes().item(0).getNodeValue();
                 String lastName = elem.getElementsByTagName(Const.LAST_NAME).item(0)
                                     .getChildNodes().item(0).getNodeValue();
                 String middleName = elem.getElementsByTagName(Const.MIDDLE_NAME).item(0)
                                     .getChildNodes().item(0).getNodeValue();
                 Person person = new Person(lastName, firstName, middleName);
                 persons.add(person);
            }
       } 
       return persons;
    }
}
