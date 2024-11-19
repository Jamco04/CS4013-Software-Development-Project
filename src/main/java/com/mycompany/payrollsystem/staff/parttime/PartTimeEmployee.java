package com.mycompany.payrollsystem.staff.parttime;
import com.mycompany.payrollsystem.staff.Staff;
import java.util.ArrayList;



public class PartTimeEmployee extends Staff {
    private double hourlyRate;
    private double hoursWorked;







    public PartTimeEmployee(){

    }

    public PartTimeEmployee(String name, int id, String title, int scalePoint, double hourlyRate, double hoursWorked){
        super(name, id, title,scalePoint);
        this.hourlyRate = hourlyRate;
        this.hoursWorked= hoursWorked;
    }

}