package com.mycompany.payrollsystem.staff;
import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.PayLoader;


public class PartTimeEmployee extends Staff {
    private double payRate; //hourly rate
    private double hoursWorked;



    public PartTimeEmployee(String name, int id, String title, int scalePoint){
        super(name, id, title, scalePoint);
        this.payRate = payRate;
        this.hoursWorked= hoursWorked;
    }

    public void calculatePayRate(PayLoader loader){
        payRate = loader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
    }

    public double getPayRate(){  //only call this after calculating the salary
        return payRate;
    }

    public double getPay() {
        return payRate * hoursWorked; // Pay is based on hours worked
    }

    @Override
    public void updateScalePoint(int newScalePoint, PayLoader loader) {
        this.scalePoint = newScalePoint;
        this.payRate = loader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));;
    }


    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "payRate=" + payRate +
                ", hoursWorked=" + hoursWorked +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", scalePoint=" + scalePoint +
                '}';
    }
}