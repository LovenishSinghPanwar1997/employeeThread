package com.training.interace;

import java.util.Date ;

public class Employee {
    String firstName ;
	String lastName ;
	String dateOfBirth ;
	String experience ;

	public String getFirstName() {
        return firstName;
    }

    @SuppressWarnings("unchecked")  
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "ESOP{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", experience=" + experience +
                '}';
    }

    @Deprecated
    public String deprecatedMethod(){
    	return "Test";
    }

}
