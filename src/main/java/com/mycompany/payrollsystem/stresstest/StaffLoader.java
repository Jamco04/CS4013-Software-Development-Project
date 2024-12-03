package com.mycompany.payrollsystem.stresstest;
import java.io.*;
import java.util.*;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.system.ScaleLoader;
import com.mycompany.payrollsystem.system.StaffContainer;

// StaffLoader
// (James+Enda) - addRandomStaffFromDatabase

public class StaffLoader {

    /**
     * A method to be called by the admin from the cli taking two parameters
     * One being the location of the csv file holding the sample employees
     * The other parameter being the number of random employees the admin would like generated
     * Uses the FileReader imported package to read the data from the csv file
     * Uses the BufferedReader which wraps the FileReader package, and allows more efficient reading from the file
     * Utilizes try catch blocks to prevent a whole system shutdown should an error be detected
     * Uses similar parsing techniques to create the employees and add them to the staffContainer
     * @param databaseLocation location of the sample employees csv file
     * @param numberOfEmployeesToAdd number of random employees to add
     */
    // this method, although similar to adams method in EmployeeLoader is instead intended to be called
    // by the admin in the CLI for testing purposes. More specifically for a showcasing of this project in our
    // interview on tuesday as adding staff manually is very time-consuming.

    public static void addRandomStaffFromDatabase(String databaseLocation, int numberOfEmployeesToAdd) {
        List<String> databaseEntries = new ArrayList<>();
        //reads in the database that we already populated with one of each staff type
        try (BufferedReader reader = new BufferedReader(new FileReader(databaseLocation))) {
            String line;
            while ((line = reader.readLine()) != null) {
                databaseEntries.add(line.trim());
            }
        } catch (IOException e) { // good error handling process
            System.out.println("Error reading database: " + e.getMessage());
            return;
        }
        if (databaseEntries.isEmpty()) {
            System.out.println("The database is empty.");
            return;
        }
        Collections.shuffle(databaseEntries);// uses the shuffle command to shuffle the databasse
        // ensures that staff chosen are random without needing to do any mathrandom commands
        // error handling in case the database is ever revised or the user inputs a number larger than the total database size
        int limit = Math.min(numberOfEmployeesToAdd, databaseEntries.size());
        for (int i = 0; i < limit; i++) {
            String employeeData = databaseEntries.get(i);
            // breaks up employee into fields the constructor can use
            // after some analysis it appears both adam and I implemented this within methods
            // it may have been easier to create just one method and call it here but this still works
            String[] details = employeeData.split(",");
            if (details.length < 5) {
                System.out.println("Skipping invalid entry: " + employeeData);
                continue;
            }
            String name = details[0];
            int id = Integer.parseInt(details[1]); // neccessary as constructor expects id as an int
            String title = details[2];
            int scalePoint;
            //possibly link together with max scalepoint code?
            try {
                scalePoint = Integer.parseInt(details[3]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid scale point for entry: " + employeeData);
                continue;
            }
            String password = details[4];
            String category = details.length > 5 && !details[5].equalsIgnoreCase("null") ? details[5] : null;
            //makes sure employee type is correct for the constructor trying to be used
            // more or less reused from adams part
            if (category != null) {
                String determinedCategory = ScaleLoader.getCategoryFromTitle(title);
                FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(name, id, determinedCategory, title, scalePoint, password);
                if (StaffContainer.add(fullTimeEmployee)) {
                    System.out.println("Full-time employee added: " + name);
                }
            } else {
                PartTimeEmployee partTimeEmployee = new PartTimeEmployee(name, id, title, scalePoint, password);
                if (StaffContainer.add(partTimeEmployee)) {
                    System.out.println("Part-time employee added: " + name);
                }
            }
        }
    }
}