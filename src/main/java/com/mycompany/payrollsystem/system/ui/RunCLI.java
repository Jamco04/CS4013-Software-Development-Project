package com.mycompany.payrollsystem.system.ui;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.ui.CLI;

import java.io.IOException;

public class RunCLI {
    public static void main(String[] args){
        try {
            // Load pay from the CSV
            PayLoader.loadPay("src/database/Salaries.csv");//this object now contains hashmap for key-salary
            PayLoader.loadTitleCategoryMap("src/database/Salaries.csv");
            CLI cli = new CLI();
            cli.run();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
