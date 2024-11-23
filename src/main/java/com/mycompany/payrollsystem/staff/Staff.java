package com.mycompany.payrollsystem.staff;


import com.mycompany.payrollsystem.system.PayLoader;

public abstract class Staff {

    // salary in FullTime, payRate in PartTime
    // category only in FullTime



    protected String name;
    protected int id;
    protected String title;
    protected int scalePoint;

    protected PayLoader loader = new PayLoader(); //for pay calculations

    public Staff(String name, int id, String title, int scalePoint){
        this.name = name;
        this.id = id;
        this.title = title;
        this.scalePoint = scalePoint;
    }

    public abstract void updateScalePoint(PayLoader loader); //different in subclasses
    public abstract double getPay(PayLoader loader); //different in subclasses



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