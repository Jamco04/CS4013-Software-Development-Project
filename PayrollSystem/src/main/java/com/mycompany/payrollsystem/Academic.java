package com.mycompany.payrollsystem;


public class Academic extends Staff {
    private double[] annualRate;
    
    public Academic(){ 
        super();
    }
    
    public Director(String title){
        super(title, 1);
        if (title.toUpperCase().equals("PRESIDENT")){
            this.annualRate = 240716.0;
        }
        else {
            this.annualRate = 184171.0;
        }
        
    }
}