package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.ScaleLoader;
import com.mycompany.payrollsystem.system.StaffContainer;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * The HR class is responsible for handling human resources operations, including employee promotions
 * Bnoth annual promotions for full-time employees and scale point promotions for part-time employees
 * It also allows for manual promotion of employees to the next salary scale based on their time spent at the top of-
 * Their payScale, and if they agree to such a promotion.
 */
public class HR {
    private final Scanner in = new Scanner(System.in);

    /**
     * Performs annual promotions for all full-time employees in the month of October
     * If it's not October, the promotion process will not be initiated.
     * Slightly edited manually for testing purposes however, otherwise interview would have to
     * Take place on the 25th of decemeber which I doubt anyone wants
     */
    public void annualPromotion() {
        LocalDate today = LocalDate.now();

        // Ensure it's October
        if (today.getMonthValue() == 10) {  // AFTER TESTING CHANGE TO !=
            System.out.println("Annual promotions can only be conducted in October.");
            return;
        }

        System.out.println("Performing annual promotions for full-time employees...");

        for (Staff staff : StaffContainer.getAllStaff()) {
            if (staff instanceof FullTimeEmployee) {
                FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) staff;
                if (fullTimeEmployee.updateScalePoint()) {
                    System.out.println("Promoted: " + fullTimeEmployee.getName() + " to scale point " + fullTimeEmployee.getScalePoint());
                } else {
                    System.out.println(fullTimeEmployee.getName() + " is already at the top of their scale and cannot be promoted further.");
                }
            }
        }
    }

    /**
     * Manually promotes a full-time employee to the next salary scale
     * This process involves inputting the employee's ID, authenticating the employee-
     * and selecting a new salary title for promotion
     */
    public void promoteToNextSalaryScale() {
        System.out.println("Enter the ID of the full-time employee to promote:");
        int id = readInt();
        Staff staff = StaffContainer.getStaffById(id);

        if (staff == null || !(staff instanceof FullTimeEmployee)) {
            System.out.println("Staff not found or not eligible for promotion.");
            return;
        }

        FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) staff;

        System.out.println("Promotion Details:");
        System.out.println("Current Scale Point: " + fullTimeEmployee.getScalePoint());
        System.out.println("Current Salary Scale: " + fullTimeEmployee.getTitle());

        System.out.println("Enter the employee's password for confirmation:");
        String inputPassword = in.nextLine().trim();

        // Authenticate the employee
        if (!fullTimeEmployee.authenticate(inputPassword)) {
            System.out.println("Invalid password. Promotion cannot be authorized.");
            return;
        }

        System.out.println("Enter new salary scale (title) for the employee:");
        String newTitle = "";
        while (!(ScaleLoader.validTitle(newTitle))) {
            System.out.println("Invalid title. Please enter a valid title.");
            newTitle = in.nextLine().trim();
        }

        // Calculate new scale point based on time spent at the top
        int newScalePoint = calculateNewScalePoint(fullTimeEmployee);

        fullTimeEmployee.promoteToNewTitle(newTitle);
        System.out.println("Successfully promoted " + fullTimeEmployee.getName() + " to title " + newTitle + " at scale point " + newScalePoint);
    }

    /**
     * Calculates the new scale point for a full-time employee based on their years spent at the top of their current scale
     * @param employee full-time employee to calculate the new scale point for
     * @return calculated new scale point
     */
    private int calculateNewScalePoint(FullTimeEmployee employee) {
        long yearsAtTop = employee.getYearsAtTop();

        // Logic to determine scale point based on years spent at the top
        if (yearsAtTop >= 5) {
            return 5; // Example: If more than 5 years, go to scale point 5
        } else if (yearsAtTop >= 3) {
            return 3; // If 3–4 years, go to scale point 3
        } else {
            return 1; // Otherwise, start at scale point 1
        }
    }

    /**
     * Reads an integer input from the user
     * This method handles invalid input by prompting the user until a valid integer is entered
     * @return The valid integer input entered by the user
     */
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Manually promotes a part-time employee to the next scale point
     * This process involves inputting the employee's ID and updating their scale point if they are eligible and agree
     * To do so
     */
    public void promoteToNextScalePoint() {
        System.out.println("Enter the ID of the part-time employee to promote:");
        int id = Integer.parseInt(in.nextLine().trim());
        Staff staff = StaffContainer.getStaffById(id);

        if (staff instanceof PartTimeEmployee) {
            PartTimeEmployee partTimeEmployee = (PartTimeEmployee) staff;
            if (partTimeEmployee.updateScalePoint()) {
                System.out.println("Successfully promoted " + partTimeEmployee.getName() + " to scale point " + partTimeEmployee.getScalePoint());
            } else {
                System.out.println("Cannot promote " + partTimeEmployee.getName() + " as they are already at the top of their scale.");
            }
        } else {
            System.out.println("Staff not found or not a part-time employee.");
        }
    }
}
