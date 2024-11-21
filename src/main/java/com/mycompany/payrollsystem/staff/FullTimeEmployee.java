package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;

public class FullTimeEmployee extends Staff {

    private String category;
    private double salary;

    public FullTimeEmployee(String name, int id, String title, int scalePoint) {
        super(name, id, title, scalePoint);
        this.salary = salary;
    }

    public double getSalary(PayLoader loader){
        salary = loader.getPay(category, String.valueOf(salary), String.valueOf(scalePoint));
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