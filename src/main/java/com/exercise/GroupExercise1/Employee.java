package com.exercise.GroupExercise1;

import java.util.Date;

public class Employee {

    public Employee(int employeeId, String firstName, String middleName, String lastName, Date dateHired) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateHired = dateHired;
    }
    private int employeeId;

    private String firstName;

    private String middleName;

    private String lastName;

    private Date dateHired; //YYYYMMDD

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }
}
