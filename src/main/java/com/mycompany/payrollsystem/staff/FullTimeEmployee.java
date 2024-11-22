package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;

public class FullTimeEmployee extends Staff {

    private String category;    //only FullTimeEmployee has category (Director/Academic/...)
    private double salary = getPay(loader);      //only FullTimeEmployee has annual salary

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint) {
        super(name, id, title, scalePoint);
        this.category = category;
    }


    @Override
    public double getPay(PayLoader loader){ //gets called in the constructor
        return salary;
    }

    @Override
    public void updateScalePoint(PayLoader loader) {
    }



    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "category='" + category + '\'' +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", scalePoint=" + scalePoint +
                '}';
    }
}