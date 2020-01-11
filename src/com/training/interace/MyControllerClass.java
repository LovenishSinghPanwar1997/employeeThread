package com.training.interace;

import com.mongodb.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;


public class MyControllerClass {

    private  static MyCollection employeeList = new MyCollection();

    public static Mongo mongo = new Mongo("localhost",27017);
    public static MongoCredential mongoCredential = MongoCredential.createCredential("training","trainingdb","training".toCharArray());

    public static DB mongoDatabase = mongo.getDB("trainingdb");


    private static JsonFileHandler jsonFileHandler = new JsonFileHandler();
    private static CsvFileHandler csvFileHandler = new CsvFileHandler();
    private static XmlFileHandler xmlFileHandler = new XmlFileHandler();

    private static int jsonReadCounter =0;
    private   static int csvReadCounter = 0;
    private static int xmlReadCounter = 0;


    public static int allWriteCounter = 0;

    public static void main(String[] args) {
            for(int i=0;i<100;i++) {
                JsonRead jsonRead = new JsonRead();
                CsvRead csvRead = new CsvRead();
                XmlRead xmlRead = new XmlRead();

                Thread thread1 = new Thread(jsonRead);
                Thread thread2 = new Thread(csvRead);
                Thread thread3 = new Thread(xmlRead);

                thread1.start();
                thread2.start();
                thread3.start();
                try {
                    thread1.join();
                    thread2.join();
                    thread3.join();
                } catch (Exception e) {

                }
            }
        for(int i=0;i<100;i++) {
            JsonWrite jsonWrite = new JsonWrite();
            CsvWrite csvWrite = new CsvWrite();
            XmlWrite xmlWrite = new XmlWrite();

            Thread thread4 = new Thread(jsonWrite);
            Thread thread5 = new Thread(csvWrite);
            Thread thread6 = new Thread(xmlWrite);

            thread4.start();
            thread5.start();
            thread6.start();

            try {
                thread4.join();
                thread5.join();
                thread6.join();
            } catch (Exception e) {

            }
        }
        for(int i=0;i<300;i++)
        {
            System.out.println(employeeList.readCounter(i));
        }
        try {
            CsvFileHandler.fileWriter.close();
            XmlFileHandler.file.close();
            jsonFileHandler.file.close();
        }catch(Exception e)
        {

        }
    }

    public static class CsvRead implements Runnable{
        @Override
        public void run() {

            try {

                CsvFileHandler csvFileHandler = new CsvFileHandler();

                List<Employee> employeeList1 = csvFileHandler.read();

                    employeeList.writeCounter(employeeList1.get(csvReadCounter));
                csvReadCounter++;
            }catch(Exception e)
            {

            }

        }
    }

    public static class  JsonRead implements Runnable{
        @Override
        public void run() {


            try {
                JsonFileHandler jsonFileHandler = new JsonFileHandler();


                Employee employeeObject = jsonFileHandler.read(jsonReadCounter);
                employeeList.writeCounter(employeeObject);
                jsonReadCounter++;
            } catch (Exception e) {

            }

        }

    }

    public static class XmlRead implements Runnable{

        @Override
        public void run() {

            try {
                    XmlFileHandler xmlFileHandler = new XmlFileHandler();
                    Employee employeeobject = xmlFileHandler.read(xmlReadCounter);
                    xmlReadCounter++;
                    employeeList.writeCounter(employeeobject);
                }catch(Exception e)
                {

                }


        }
    }
    public static class JsonWrite implements Runnable{
        @Override
        public void run() {
            try {
                 allWriteCounter++;



            }catch(Exception e)
            {

            }
            jsonFileHandler.write(employeeList.readCounter(allWriteCounter));


        }
    }

    public static class CsvWrite implements Runnable{
        @Override
        public void run() {
            try {

                csvFileHandler.write(employeeList.readCounter(allWriteCounter));
                allWriteCounter++;

            }catch (Exception e)
            {

            }
        }
    }

    public static class XmlWrite implements Runnable{

        public void run() {
            try{


                xmlFileHandler.write(employeeList.readCounter(allWriteCounter));
                allWriteCounter++;
            }catch (Exception e)
            {

            }

        }
    }

}
