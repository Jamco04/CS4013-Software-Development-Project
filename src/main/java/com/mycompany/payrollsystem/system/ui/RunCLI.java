package com.mycompany.payrollsystem.system.ui;

import com.mycompany.payrollsystem.system.ScaleLoader;

import java.io.IOException;

public class RunCLI {
    /**
     * A test environment of the cli is run
     * The stack trace is implemented to provide detailed information about where the exception occurred if one occurs
     * @param args main class argument
     */
    public static void main(String[] args){
        try {
            // Load pay from the CSV
            ScaleLoader.loadScales("src/database/Salaries.csv");   //this object now contains hashmap for key-salary

            CLI cli = new CLI();
            cli.run();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}