package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;
import java.time.temporal.ChronoUnit;

import java.time.LocalDateTime;

public class FullTimeEmployee extends Staff {

    private String category;    //only FullTimeEmployee has category (Director/Academic/...)
    private double salary;      //only FullTimeEmployee has annual salary
    private LocalDateTime topScaleStartTime;

    public FullTimeEmployee(String name, int id, String category, String title, int scalePoint, String password) {
        super(name, id, title, scalePoint, password);
        this.category = category;
        this.salary = getSalary(new PayLoader());
        this.topScaleStartTime = LocalDateTime.now();
    }


    public boolean updateScalePoint(PayLoader loader) {
        double newSalary = loader.getPay(category, title, String.valueOf(scalePoint + 1));
        if (newSalary != -1) { // Check if next scale point exists
            scalePoint++;
            salary = newSalary;
            return true;
        } else {
            if (topScaleStartTime == null) {
                topScaleStartTime = LocalDateTime.now(); // Mark start time at top scale
            }
            return false; // Already at top
        }
    }

    public boolean isAtTopScalePoint(PayLoader loader) {
        double newSalary = loader.getPay(category, title, String.valueOf(scalePoint + 1));
        return newSalary == -1; // No higher scale point
    }

    public long getYearsAtTop() {
        if (topScaleStartTime == null) return 0;
        return ChronoUnit.YEARS.between(topScaleStartTime, LocalDateTime.now());
    }

    public void promoteToNewTitle(String newTitle, int newScalePoint) {
        title = newTitle;
        scalePoint = newScalePoint;
        salary = getSalary(new PayLoader());
        topScaleStartTime = null; // Reset top scale timer
    }

    public double getSalary(PayLoader loader) {
        return loader.getPay(category, title, String.valueOf(scalePoint));
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("Full-Time Employee: name: %s | id: %d | category: %s | title: %s | scalePoint: %d | salary: %.2f",
                name, id, category, title, scalePoint, salary);
    }
}