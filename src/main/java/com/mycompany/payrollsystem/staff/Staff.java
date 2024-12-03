package com.mycompany.payrollsystem.staff;


import com.mycompany.payrollsystem.system.Payslip;

import java.time.LocalDate;
import java.util.ArrayList;

//Staff
//(Adam+James)

public abstract class Staff {

    // salary in FullTime, payRate in PartTime
    // category only in FullTime

    private LocalDate startTime;
    protected String name;
    protected int id;
    protected String title;
    protected int scalePoint;
    private final ArrayList<Payslip> payslips = new ArrayList<>();
    private String password;

    /**
     * Constructor for all staff objects
     * Used by both fullTime and partTime employees as the super constructor in their own constructors
     * The following parameters were chosen to be in this super class as both types of staff share them
     * @param name staff name
     * @param id staff id
     * @param title staff current title
     * @param scalePoint staff current scalepoint
     * @param password staff password
     */
    public Staff(String name, int id, String title, int scalePoint, String password){
        this.startTime = LocalDate.now();
        this.name = name;
        this.id = id;
        this.title = title;
        this.scalePoint = scalePoint;
        this.password = password;
    }

    /**
     * used by both employee types to add their payslips, takes a payslip object as a parameter
     * @param payslip an employees payslip object
     */

    public void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }

    /**
     * Method to view payslips, has no parameters
     * First checks that an employee has payslips to view
     * Prints header "Payslips" then iterates through all an employees payslips, displaying their details
     */

    public void viewPayslips() {
        if (payslips.isEmpty()) {
            System.out.println("No payslips available.");
            return;
        }

        System.out.println("Payslips:");
        for (Payslip payslip : payslips) {
            System.out.printf("Pay Period: %s | Gross Pay: %.2f | Tax: %.2f | Net Pay: %.2f%n",
                    payslip.getPayPeriod(), payslip.getGrossPay(), payslip.getTax(), payslip.getNetPay());
        }
    }

    /**
     * An abstract method with no code in the main block
     * Each subclass will provide its own version and implementation of this method
     * @return true if operation has been carried out, false if not
     */

    public abstract boolean updateScalePoint(); //different in subclasses

    /**
     * Method to validate an employees password
     * Checks the password entered by the employee against the password assosiated with that employee
     * In a real setting this would likely be encrypted and not stored in plain text but here it's ok I think
     * @param inputPassword password entered by the employee trying to login
     * @return true if passwords match, false if they dont
     */
    public boolean authenticate(String inputPassword) {
        return password.equals(inputPassword);
    }

    /**
     * getter method to retrieve an employees name
     * @return employees name
     */
    public String getName() {
        return name;
    }

    /**
     * getter method to get the employees id
     * @return employees id
     */
    public int getId() {
        return id;
    }

    /**
     * getter method to get the employees current title
     * @return employees current title
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter method to get the employees current scalePoint
     * @return employees current scalePoint
     */
    public int getScalePoint() {
        return scalePoint;
    }

    /**
     * toString method to print an employees default information
     * @return employees default information in a string form
     */
    @Override
    public String toString() {
        return String.format("name: %s | id: %d | title: %s | scalePoint: %d", name, id, title, scalePoint);
    }

    /**
     * getter method to get the employees password
     * necessary for the password validation method
     * @return employees current password
     */
    public String getPassword() {
        return password;
    }
}