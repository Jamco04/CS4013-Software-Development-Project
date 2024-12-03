package com.mycompany.payrollsystem.stresstest;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.system.StaffContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//EmployeeLoader
//Adam - loadEmployeesFromFile
//James - parseEmployee

public class EmployeeLoader {



    /**
     * Method that loads employees from the specified csv file location which is passed as the only parameter
     * Uses the FileReader imported package to read the data from the csv file
     * Uses the BufferedReader which wraps the FileReader package, and allows more efficient reading from the file
     * This method also has the throws IOException tag as it ensures the fileReader is always-
     * closed after the block executes, even if an exception occurs
     * @param filePath location of employees file csv
     * @throws IOException error handling in case of an exception, ensures fileReader closes
     */
    public static void loadEmployeesFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                parseEmployee(line);
            }
        }
    }

    /**
     * Used to break up the employee information which is stored in a comma seperated value (CSV) file
     * Splits the employees info at each comma, and assigns each piece to its relevant employee datafield
     * Then calls the relevant constructor (partTime or fullTime employee) with that sorted information
     * Adds the employee to the StaffContainer
     * @param csvLine the current line being accessed within the csv file
     */

    private static void parseEmployee(String csvLine) {
        String[] parts = csvLine.split(",");

        String name = parts[0];
        int id = Integer.parseInt(parts[1].trim());
        String title = parts[2];
        int tier = Integer.parseInt(parts[3].trim());
        String password = parts[4];
        String category = parts[5];

        if (category.equals("null")) {
            PartTimeEmployee employee = new PartTimeEmployee(name, id, title, tier, password);
            StaffContainer.add(employee);
        } else {
            FullTimeEmployee employee = new FullTimeEmployee(name, id, category, title, tier, password);
            StaffContainer.add(employee);
        }
    }
}