package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;



public class PartTimeEmployee extends Staff {
    private double payRate; //hourly rate



    public PartTimeEmployee(String name, int id, String title, int scalePoint){
        super(name, id, title, scalePoint);
    }


    public double getPay(PayLoader loader, double hoursWorked) {    //sets payRate and return "salary"
        payRate = loader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
        return  payRate * hoursWorked; // Pay is based on hours worked
    }

    public void submitPayClaim(double hours) {
        System.out.printf("Pay claim submitted: %.2f hours for %s (ID: %d)%n", hours, name, id);
    }

    @Override
    public boolean updateScalePoint(PayLoader loader) {    //IMPLEMENT THIS
        return false;
    }


    @Override
    public String toString() {
        return String.format("Part-Time Employee: name: %s | id: %d | title: %s | scalePoint: %d | payRate: %.2f",
                name, id, title, scalePoint, payRate);
    }
}