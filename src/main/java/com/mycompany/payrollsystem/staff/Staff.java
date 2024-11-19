package com.mycompany.payrollsystem.staff;
import java.util.ArrayList;



public class Staff {


    private String title;
    private int scalePoint;
    ArrayList<Double> payRate;




    public Staff(){
    }
    
    public Staff(String name, int id, String title, int scalePoint){
        this.title = title;
        this.scalePoint = scalePoint;
    }
    
    
}