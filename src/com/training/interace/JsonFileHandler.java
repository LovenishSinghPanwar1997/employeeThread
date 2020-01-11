package com.training.interace;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class JsonFileHandler implements EmployeeInterface {

    private static JSONArray empJsonArray = new JSONArray();
    FileWriter file;
    public static int i=0;
    public JsonFileHandler()

    {
        try {
            file = new FileWriter("employeeswrite.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Employee read(int index) throws Exception {
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader("/Users/lovenishsinghpanwar/Downloads/employee.json");
        Object object;
        object = jsonParser.parse(reader);

        JSONArray employeeList = (JSONArray) object;

        Employee empObj = new Employee();
        JSONObject employeeObject = (JSONObject) employeeList.get(index);

        empObj.firstName = employeeObject.get("firstName").toString();
        empObj.lastName = employeeObject.get("lastName").toString();
        empObj.dateOfBirth = employeeObject.get("dateOfBirth").toString();
        empObj.experience = employeeObject.get("experience").toString();


        return empObj;
    }

    public void write(Employee employeeObject) {
        try {
            if(i<=100) {
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/trainingdb", "lovenish", "lovenish1997");
                Statement statement = connection.createStatement();
                int rowsUpdated1 = statement.executeUpdate("insert into jsonData(firstName,lastName,dateOfBirth,experience) values ('" + employeeObject.getFirstName() + "','" + employeeObject.getLastName() + "','" + employeeObject.getDateOfBirth() + "','" + employeeObject.getExperience() + "');");
                i++;
            }
            } catch (Exception e) {

        }

    }
}
