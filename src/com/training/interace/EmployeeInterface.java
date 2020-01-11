package com.training.interace;

import org.json.simple.JSONArray;

public interface EmployeeInterface {

Employee read(int index) throws Exception;
void write(Employee employeeObject);


}
