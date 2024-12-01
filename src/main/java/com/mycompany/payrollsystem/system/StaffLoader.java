package com.mycompany.payrollsystem.system;
import java.io.*;
import java.util.*;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.staff.FullTimeEmployee;
public class StaffLoader {
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