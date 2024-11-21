package com.mycompany.payrollsystem.system;
import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args){
        try {
            // Load pay from the CSV
            PayLoader loader = new PayLoader();
            loader.loadPay("src/database/Salaries.csv");   //this object now contains hashmap for key-salary

            // Create a FullTimeEmployee
            FullTimeEmployee employee1 = new FullTimeEmployee("Adam Urban", 23381752, "Academic", "Professor",2);

            employee1.calculateSalary(loader);
            System.out.println(employee1.getSalary());

            // Create a PartTimeEmployee
            PartTimeEmployee employee2 = new PartTimeEmployee("James Connolly", 2336892, "LabTutor", 2);

            employee2.calculatePayRate(loader);
            System.out.println(employee2.getSalary());


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
