package com.mycompany.payrollsystem.staff;
import com.mycompany.payrollsystem.staff.Staff;




public class PartTimeEmployee extends Staff {
    private double payRate; //hourly rate
    private double hoursWorked;



    public PartTimeEmployee(String name, int id, String title, int scalePoint, double payRate, double hoursWorked){
        super(name, id, title, scalePoint);
        this.payRate = payRate;
        this.hoursWorked= hoursWorked;
    }

    @Override
    public double calculatePay() {
        return payRate * hoursWorked; // Pay is based on hours worked
    }

    @Override
    public void updateScalePoint(int newScalePoint, double newPayRate) {
        this.scalePoint = newScalePoint;
        this.payRate = newPayRate;
    }

    public void setHourlyRate(double payRate) {
        this.payRate = payRate;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }



}