package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.StaffContainer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private final Scanner in = new Scanner(System.in);



    // Helper method to safely read integers
    private int readInt(String message) {
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }



    private String readString(String message) {
        System.out.println(message);
        return in.nextLine().trim();    //trim removes spaces before or after the actual string
    }

    public void addStaff() {
        List<String> validTitles = Titles.getTitles();

        // Staff Type
        int type = readInt("Enter staff type: (1) full-time (2) part-time:");
        while (type != 1 && type != 2) {
            System.out.println("Invalid staff type. Please enter 1 for full-time or 2 for part-time.");
            type = readInt("Enter staff type: (1) full-time (2) part-time:");
        }

        // Name
        String name = readString("Enter staff name:");
        
        // Unique ID
        int id;
        while (true) {
            id = readInt("Enter staff ID:");

            if (Integer.toString(id).length() != 5){
                System.out.println("Staff ID Must be 5 digits.");
            }
            if (StaffContainer.getStaffById(id) != null) {
                System.out.println("Staff ID already exists. Please enter a unique ID.");
            } else {
                break;
            }

        }

        //title validation
        String title = "";
        while (true) {
            title = readString("Enter staff title:").trim();

            if (Titles.getTitles().contains(title)) {
                System.out.println("Title accepted: " + title);
                break;
            } else {
                System.out.println("Invalid title.");

                //suggest titles part of code
                if (!title.isEmpty()) {
                    char firstLetter = Character.toUpperCase(title.charAt(0));
                    List<String> suggestions = new ArrayList<>();

                    for (int i = 0; i < validTitles.size(); i++) {
                        String validTitle = validTitles.get(i);
                        if (Character.toUpperCase(validTitle.charAt(0)) == firstLetter) {
                            suggestions.add(validTitle);
                        }
                    }

                    if (!suggestions.isEmpty()) {
                        System.out.println("Did you mean one of these? " + String.join(", ", suggestions));
                    }
                }
            }
        }

        // Scale Point
        int scalePoint = readInt("Enter scale point:");
        PayLoader loader = new PayLoader();  //by Instantiating the class we can use its method for retrieving the  max scalepoint
        // we alo need the getCatagory method so Instantiating this works out well

        int maxScale = loader.getMaxScalePoints(title);
        while (scalePoint<=0 || scalePoint>maxScale) {
            System.out.println("Invalid Scalepoint. The maximum Scale Point for this title is "+maxScale);
            scalePoint = readInt("Enter scale point:");
        }

        // Password
        String password = readString("Set a password for this employee:");
        while (password.length()<7) {
            System.out.println("Password must have at least 8 characters");
            password = readString("Set a password for this employee:");
        }
        // Full-Time Employee
        if (type == 1) {

            String category = PayLoader.getCategoryFromTitle(title);
            FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(name, id, category, title, scalePoint, password);
            if (StaffContainer.add(fullTimeEmployee)) {
                System.out.println("Full-time employee added successfully!");   //otherwise, java does garbage collection automatically
            }

            // Part-Time Employee
        } else {
            PartTimeEmployee partTimeEmployee = new PartTimeEmployee(name, id, title, scalePoint, password);
            if (StaffContainer.add(partTimeEmployee)) {
                System.out.println("Part-time employee added successfully!");
            }
        }
    }

    public void viewStaff() {
        if (StaffContainer.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            StaffContainer.listAllStaff();
            saveAllStaffToCSV();
        }
    }

    private void saveAllStaffToCSV() {
        String fileName = "employees.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write the CSV header
            writer.write("Employee,Name,ID,Category,Title\n");

            for (Staff staff : StaffContainer.getAllStaff()) {
                String category = (staff instanceof FullTimeEmployee) ? ((FullTimeEmployee) staff).getCategory() : "";
                String employee = (staff instanceof FullTimeEmployee) ? "Full-Time" : "Part-Time";

                writer.write(String.format("%s,%s,%d,%s,%s\n",
                        employee,
                        staff.getName(),
                        staff.getId(),
                        category,
                        staff.getTitle()));
            }
            System.out.println("Staff details have been saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving staff details to CSV: " + e.getMessage());
        }
    }
}
