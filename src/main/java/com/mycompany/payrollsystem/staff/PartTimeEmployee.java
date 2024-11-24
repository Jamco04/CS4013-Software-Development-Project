package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;



public class PartTimeEmployee extends Staff {
    private double payRate; //hourly rate
    private final PayLoader loader = new PayLoader();



    public PartTimeEmployee(String name, int id, String title, int scalePoint){
        super(name, id, title, scalePoint);
        payRate = loader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
    }


    public double getPay(double hoursWorked) {    //sets payRate and return "salary"
        payRate = loader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
        return  payRate * hoursWorked; // Pay is based on hours worked
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