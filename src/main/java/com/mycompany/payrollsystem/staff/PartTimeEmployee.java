package com.mycompany.payrollsystem.staff;
import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.PayLoader;


public class PartTimeEmployee extends Staff {
    private double payRate; //hourly rate
    private double hoursWorked;
    private double pay = getPay(loader);



    public PartTimeEmployee(String name, int id, String title, int scalePoint, double hoursWorked){
        super(name, id, title, scalePoint);
        this.hoursWorked= hoursWorked;
    }


    public double getPayRate(){
        return payRate;
    }

    @Override
    public double getPay(PayLoader loader) {    //sets payRate and return "salary"
        payRate = loader.getPay("", String.valueOf(this.title), String.valueOf(this.scalePoint));
        return this.payRate * this.hoursWorked; // Pay is based on hours worked
    }

    @Override
    public void updateScalePoint(PayLoader loader) {
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