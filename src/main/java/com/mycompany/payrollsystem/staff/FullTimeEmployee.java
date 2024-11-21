package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;

public class FullTimeEmployee extends Staff {

    private String category;
    private double salary;

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint) {
        super(name, id, title, scalePoint);
        this.category = category;
    }

    public void calculateSalary(PayLoader loader){
        salary = loader.getPay(this.category, String.valueOf(this.title), String.valueOf(this.scalePoint));
    }

    public double getSalary(){  //only call this after calculating the salary
        return salary;
    }

    public void setSalary(double salary) {  // will need to be changed to change accordingly to csv file
        this.salary = salary;
    }

    @Override
    public double calculatePay() {
        return salary; // Full-time employees earn a fixed salary
    }

    @Override
    public void updateScalePoint(int newScalePoint, double newSalary) {
        this.scalePoint = newScalePoint;
        this.salary = newSalary;
    }
    
}