package com.training.interace;


import java.util.ArrayList;
import java.util.List;

public class MyCollection {

    private List<Employee> employeeList = new ArrayList<Employee>();
    synchronized public Employee readCounter(int currIndex)
    {
            return employeeList.get(currIndex);
    }

    synchronized public void writeCounter(Employee empData)
    {
        employeeList.add(empData);

    }

}
