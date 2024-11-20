package com.mycompany.payrollsystem.staff;
import java.util.ArrayList;



public class Staff {


    private String name;
    private int id;
    private String title;
    private int scalePoint;


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

    public Staff(){
    }
    
    public Staff(String name, int id, String title, int scalePoint){
        this.title = title;
        this.scalePoint = scalePoint;
    }
    
    
}