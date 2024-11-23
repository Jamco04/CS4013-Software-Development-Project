package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.StaffContainer;

public class FullTimeEmployee extends Staff {

    private String category;    //only FullTimeEmployee has category (Director/Academic/...)
    private double salary;      //only FullTimeEmployee has annual salary

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint) {
        super(name, id, title, scalePoint);
        this.category = category;
        this.salary = getPay(loader);
    }


    @Override
    public double getPay(PayLoader loader){ //gets called in the constructor
        return loader.getPay(category, title, String.valueOf(scalePoint));
    }

    @Override
    public void updateScalePoint(PayLoader loader) {
        if (loader.getPay(category, title, String.valueOf(scalePoint)) == -1){  //check if next scalepoint not available

        }
        else {
            scalePoint += 1;
        }
    }



    @Override
    public String toString() {

        return
                "name: " + name + " | " +
                "id: " + id + " | " +
                "category: " + category + " | " +
                "title: " + title + " | " +
                "scalePoint: " + scalePoint + " | " +
                "salary: " + salary;
    }
}