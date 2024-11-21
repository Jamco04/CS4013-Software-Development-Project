package com.mycompany.payrollsystem.staff;

public class FullTimeEmployee extends Staff {

    private String category;
    private double salary;

    public FullTimeEmployee(String name, int id, String title, int scalePoint){
        super(name, id, title, scalePoint);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
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