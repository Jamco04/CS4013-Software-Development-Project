package com.mycompany.payrollsystem.staff.fulltime;
import com.mycompany.payrollsystem.staff.Staff;

public class FullTimeEmployee extends Staff {

    private double salary;

    public FullTimeEmployee(){

    }

    public FullTimeEmployee(String name, int id, String title, int scalePoint, double salary){
        super(name, id, title, scalePoint);
        this.salary = salary;
    }
    
}