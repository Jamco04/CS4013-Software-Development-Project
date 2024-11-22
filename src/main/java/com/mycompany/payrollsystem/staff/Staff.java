package com.mycompany.payrollsystem.staff;


import com.mycompany.payrollsystem.system.PayLoader;

public abstract class Staff {

    // salary in FullTime, payRate in PartTime
    // category only in FullTime

    protected PayLoader loader;
    protected String name;
    protected int id;
    protected String title;
    protected int scalePoint;

    public Staff(String name, int id, String title, int scalePoint){
        this.name = name;
        this.title = title;
        this.scalePoint = scalePoint;
        System.out.println("Staff created correctly");
    }

    public abstract void updateScalePoint(PayLoader loader); //different in subclasses
    public abstract double getPay(PayLoader loader); //different in subclasses



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

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", scalePoint=" + scalePoint +
                '}';
    }
}