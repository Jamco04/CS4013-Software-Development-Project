package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.StaffContainer;

import java.util.Scanner;

public class HR {
    private final PayLoader loader = new PayLoader();
    private final Scanner in = new Scanner(System.in);

    public void promoteStaff() {
        System.out.println("Enter the ID of the full-time employee to promote:");
        int id = readInt();
        Staff staff = StaffContainer.getStaffById(id);

        // Check if the staff exists and is a FullTimeEmployee
        if (staff == null || !(staff instanceof FullTimeEmployee fullTimeEmployee)) {
            System.out.println("Staff not found or not eligible for promotion.");
            return;
        }


        System.out.println("Current Scale Point: " + fullTimeEmployee.getScalePoint());

        // Ask for confirmation
        System.out.println("Do you accept this promotion? (yes/no)");
        String confirmation = in.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            // Try to update the scale point or category
            if (!fullTimeEmployee.updateScalePoint(loader)) {
                System.out.println("Employee already at the top of their salary scale. Assign new title or category if applicable.");

                // If they are at the top of their salary scale, check years at the current scale point
                int yearsAtScalePoint = fullTimeEmployee.getYearsAtCurrentScalePoint();
                System.out.println("Years at current scale point: " + yearsAtScalePoint);

                // If eligible for promotion to a new category, ask for new category and scale point
                if (yearsAtScalePoint >= 3) { // Assuming 3 years for promotion eligibility
                    System.out.println("Enter new category for promotion:");
                    String newCategory = in.nextLine().trim();

                    System.out.println("Enter new scale point for the new category:");
                    int newScalePoint = readInt();

                    fullTimeEmployee.promoteToNewCategory(newCategory, newScalePoint);
                    System.out.println("Promotion to new category and scale point completed successfully.");
                } else {
                    System.out.println("Employee is not eligible for category promotion yet. Requires more time at the current scale point.");
                }
            } else {
                System.out.println("Promotion applied successfully to the next scale point.");
            }
        } else {
            System.out.println("Promotion declined by the employee.");
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
