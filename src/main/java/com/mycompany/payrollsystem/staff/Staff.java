package com.mycompany.payrollsystem.staff;


import com.mycompany.payrollsystem.system.PayLoader;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Staff {

    // salary in FullTime, payRate in PartTime
    // category only in FullTime

    private LocalDate startTime;
    protected String name;
    protected int id;
    protected String title;
    protected int scalePoint;


    public Staff(String name, int id, String title, int scalePoint){
        this.startTime = LocalDate.now();
        this.name = name;
        this.id = id;
        this.title = title;
        this.scalePoint = scalePoint;
    }

    public void resetStartTime() {
        this.startTime = LocalDate.now();
    }

    public int getYearsAtCurrentScalePoint() {
        return (int) ChronoUnit.YEARS.between(startTime, LocalDate.now());
    }

    public abstract boolean updateScalePoint(PayLoader loader); //different in subclasses


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getScalePoint() {
        return scalePoint;
    }

    @Override
    public String toString() {
        return String.format("name: %s | id: %d | title: %s | scalePoint: %d", name, id, title, scalePoint);
    }
}