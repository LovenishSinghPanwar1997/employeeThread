package com.training.interace;
import com.mongodb.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
//import com.opencsv.*;
//import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;


public class CsvFileHandler {

    public static int i=0;

    public static Mongo mongo = new Mongo("localhost",27017);
    public static MongoCredential mongoCredential = MongoCredential.createCredential("training","trainingdb","training".toCharArray());

    public static DB mongoDatabase = mongo.getDB("trainingdb");
    public static FileWriter fileWriter;

    public CsvFileHandler()
    {
        try {
            fileWriter = new FileWriter(new File("employeeWrite.csv"));
        }
        catch(IOException e)
        {e.printStackTrace();}
    }

    public List<Employee> read() throws IOException {

        List<Employee> employees=new ArrayList<>();
        String csvfile="/Users/lovenishsinghpanwar/Downloads/employee.csv";
        BufferedReader br=null;
        String line="";
        String splitby=",";
        try {
            br=new BufferedReader(new FileReader(csvfile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while((line=br.readLine())!=null)
        {
            Employee emp=new Employee();
            String[] record=line.split(splitby);
            //System.out.println(record[1]+" "+record[2]);
            emp.setFirstName(record[0]);
            emp.setLastName(record[1]);
            emp.setDateOfBirth(record[2]);
            emp.setExperience(record[3]);
            employees.add(emp);
            //System.out.println(emp);
            //employess.add(emp);
        }
        return employees;

    }

    public void write(Employee emp1) throws IOException {
        if(i<=100) {
            DBCollection dbCollection = mongoDatabase.getCollection("csvData");

            BasicDBObjectBuilder basicDBObjectBuilder = new BasicDBObjectBuilder();
            basicDBObjectBuilder.append("firstName", emp1.getFirstName());
            basicDBObjectBuilder.append("lastName", emp1.getLastName());
            basicDBObjectBuilder.append("dateOfBirth", emp1.getDateOfBirth());
            basicDBObjectBuilder.append("experience", emp1.getExperience());

            DBObject dbObject = basicDBObjectBuilder.get();

            WriteResult writeResult = dbCollection.insert(dbObject);
            i++;
        }
    }
}