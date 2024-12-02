package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.ScaleLoader;



public class PartTimeEmployee extends Staff {
    private double payRate; //hourly rate


    /**
     * Constructor for a partTime employee
     * Takes the below parameters and creates a partTimeEmployee object
     * This is done by calling the default constructor specified in the staff class with
     * name, id, title, scalePoint and password, just as in the fullTimeEmployee's constructor
     * Then  payRate is calculated after and added to the object
     * @param name employees name
     * @param id employees id
     * @param title employees starting title
     * @param scalePoint employees starting scalePoint
     * @param password employees starting password
     */

    public PartTimeEmployee(String name, int id, String title, int scalePoint, String password) {
        super(name, id, title, scalePoint, password);
        payRate = ScaleLoader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
    }

    /**
     * A method to calculate a partTime employees pay based on hours worked and hourly rate
     * it takes the number of hours worked as a parameter, and gets the pay from a method in the scaleLoader class
     * @param hoursWorked number of hours worked by the employee
     * @return the employees gross pay based on the hours worked this period
     */

    public double getPay(double hoursWorked) {    //sets payRate and return "salary"
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        }
        payRate = ScaleLoader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
        return  payRate * hoursWorked; // Pay is based on hours worked
    }

    /**
     * A method to advance a partTime employees scalePoint by one
     * Their new payRate is then calculated using this information
     * @return true if the scalePoint is updated, false if not
     */

    @Override
    public boolean updateScalePoint() {
        int maxScalePoints = ScaleLoader.getMaxScalePoints(title);
        if (scalePoint < maxScalePoints) {
            scalePoint++;
            payRate = ScaleLoader.getPay("", title, String.valueOf(scalePoint));
            return true;
        }
        return false;
    }

    /**
     * toString method for displaying a partTime employees details
     * @return displays a partTime Employees current name, id, title, scalePoint, and payRate
     */

    @Override
    public String toString() {
        return String.format("Part-Time Employee: name: %s | id: %d | title: %s | scalePoint: %d | payRate: %.2f",
                name, id, title, scalePoint, payRate);
    }
}