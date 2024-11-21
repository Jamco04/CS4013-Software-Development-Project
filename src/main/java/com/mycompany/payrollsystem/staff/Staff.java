package com.mycompany.payrollsystem.staff;


public abstract class Staff {

    // salary in FullTime, payRate in PartTime
    // category only in FullTime

    protected String name;
    protected int id;
    protected String title;
    protected int scalePoint;

    public Staff(String name, int id, String title, int scalePoint){
        this.name = name;
        this.title = title;
        this.scalePoint = scalePoint;
    }

    public abstract double calculatePay(); //different in subclasses
    public abstract void updateScalePoint(int newScalePoint, double newPay); //different in subclasses


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScalePoint() {
        return scalePoint;
    }

    public void setScalePoint(int scalePoint) {
        this.scalePoint = scalePoint;
    }

    
    
}