package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;

public class FullTimeEmployee extends Staff {

    private String category;
    private double salary = 0.0;

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint) {
        super(name, id, title, scalePoint);
        this.category = category;
    }


    @Override
    public double getPay(PayLoader loader){
        salary = loader.getPay(this.category, String.valueOf(this.title), String.valueOf(this.scalePoint));
        return salary;
    }

    @Override
    public void updateScalePoint(int newScalePoint, PayLoader loader) {
        this.scalePoint = newScalePoint;
        this.salary = loader.getPay(this.category, String.valueOf(this.title), String.valueOf(this.scalePoint));
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