package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.StaffContainer;

public class FullTimeEmployee extends Staff {

    private String category;    //only FullTimeEmployee has category (Director/Academic/...)
    private double salary;      //only FullTimeEmployee has annual salary

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint) {
        super(name, id, title, scalePoint);
        this.category = category;
        this.salary = getSalary(new PayLoader());
    }


    public double getSalary(PayLoader loader){ //gets called in the constructor
        return loader.getPay(category, title, String.valueOf(scalePoint));
    }

    public void promoteInOctober() {
        double newSalary = new PayLoader().getPay(category, title, String.valueOf(scalePoint + 1));
        if (newSalary != -1) {
            scalePoint++;
            salary = newSalary;
            System.out.println("Promoted in October: " + name + " to scalePoint " + scalePoint);
        }
    }

    public void promoteToNewCategory(String newCategory, int newScalePoint) {
        category = newCategory;
        scalePoint = newScalePoint;
        salary = getSalary(new PayLoader());
    }

    @Override
    public boolean updateScalePoint(PayLoader loader) {
        double newSalary = loader.getPay(category, title, String.valueOf(scalePoint + 1));
        if (newSalary != -1) {  //checks if key exists in the map in PayLoader (if not then the scalePoint is already top)
            scalePoint++;
            salary = newSalary;
            resetStartTime(); //reset the start time for the new scale point
            return true;
        } else {
            return false;   //if already on top, doesnt succeed
        }
    }



    @Override
    public String toString() {
        return String.format("Full-Time Employee: name: %s | id: %d | category: %s | title: %s | scalePoint: %d | salary: %.2f",
                name, id, category, title, scalePoint, salary);
    }
}