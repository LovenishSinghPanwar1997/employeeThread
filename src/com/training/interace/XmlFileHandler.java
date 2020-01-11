package com.training.interace;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.mongodb.*;
import com.sun.xml.internal.ws.developer.SerializationFeature;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlFileHandler implements EmployeeInterface{

    public static int i=0;

    public static Mongo mongo = new Mongo("localhost",27017);
    public static MongoCredential mongoCredential = MongoCredential.createCredential("training","trainingdb","training".toCharArray());

    public static DB mongoDatabase = mongo.getDB("trainingdb");

    public static FileWriter file;

    public XmlFileHandler()
    {
        try {
            file=new FileWriter("employeeswrite.xml");
        }
        catch(IOException e)
        {e.printStackTrace();}
    }

    public Employee read(int idx) throws ParserConfigurationException, IOException, SAXException
    {
        Employee emp=new Employee();
        try {
            File file = new File("/Users/lovenishsinghpanwar/Downloads/employee.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("employee");
            Node nNode = nList.item(idx);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                //Get each employee's detail
                Element eElement = (Element) nNode;

                emp.setFirstName(eElement.getElementsByTagName("firstName")
                        .item(0).getTextContent());
                emp.setLastName(eElement.getElementsByTagName("lastName")
                        .item(0).getTextContent());
                emp.setDateOfBirth(eElement.getElementsByTagName("dateOfBirth")
                        .item(0).getTextContent());
                emp.setExperience(eElement.getElementsByTagName("experience")
                        .item(0).getTextContent());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return emp;
    }

    public void write(Employee employeeObject) {

        if(i<=100) {
            DBCollection dbCollection = mongoDatabase.getCollection("xmlData");

            BasicDBObjectBuilder basicDBObjectBuilder = new BasicDBObjectBuilder();
            basicDBObjectBuilder.append("firstName", employeeObject.getFirstName());
            basicDBObjectBuilder.append("lastName", employeeObject.getLastName());
            basicDBObjectBuilder.append("dateOfBirth", employeeObject.getDateOfBirth());
            basicDBObjectBuilder.append("experience", employeeObject.getExperience());

            DBObject dbObject = basicDBObjectBuilder.get();
            i++;

            WriteResult writeResult = dbCollection.insert(dbObject);
        }

    }

}
