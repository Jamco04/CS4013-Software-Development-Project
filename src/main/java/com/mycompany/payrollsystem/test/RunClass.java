package com.mycompany.payrollsystem.test;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.UI.CLI;

import java.io.IOException;

public class RunClass {
    public static void main(String[] args){
        try {
            // Load pay from the CSV
            PayLoader.loadPay("src/database/Salaries.csv");   //this object now contains hashmap for key-salary

            CLI cli = new CLI();
            cli.run();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
