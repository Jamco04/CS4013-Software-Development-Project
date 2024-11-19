package com.mycompany.payrollsystem.staff.parttime;


import com.mycompany.payrollsystem.staff.Staff;

public class PartTimeEmployee extends Staff {
    private double hourlyRate;
    private double hoursWorked;

    public PartTimeEmployee(String title, int scalePoint, double hourlyRate, double hoursWorked){
        super(title,scalePoint);
        this.hourlyRate = hourlyRate;
        this.hoursWorked= hoursWorked;
    }
}