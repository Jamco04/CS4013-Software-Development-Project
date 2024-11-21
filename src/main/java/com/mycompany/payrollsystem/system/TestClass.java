package com.mycompany.payrollsystem.system;
import com.mycompany.payrollsystem.staff.FullTimeEmployee;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args){
        try {
            // Load pay from the CSV
            PayLoader loader = new PayLoader();
            loader.loadPay("/src/database/Salaries.csv");   //this object now contains hashmap for key-salary

            // Create a FullTimeEmployee
            FullTimeEmployee employee1 = new FullTimeEmployee("Adam Urban", 23381752, "Professor", 2);

            employee1.getSalary();
            /*
            // Print details
            System.out.println(employee);

            // Generate payslip
            System.out.println(employee.generatePayslip());

            // Update tier and print updated details
            employee.updateTier("3", loader);
            System.out.println("After Tier Update:");
            System.out.println(employee.generatePayslip());

             */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
