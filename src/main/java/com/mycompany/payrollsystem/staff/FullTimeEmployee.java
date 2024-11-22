package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;

public class FullTimeEmployee extends Staff {

    private String category;    //only FullTimeEmployee has category (Director/Academic/...)
    private double salary;      //only FullTimeEmployee has annual salary

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint) {
        super(name, id, title, scalePoint);
        this.salary = getPay(loader);
        this.category = category;
    }


    @Override
    public double getPay(PayLoader loader){ //gets called in the constructor
        salary = loader.getPay(this.category, String.valueOf(this.title), String.valueOf(this.scalePoint));
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