package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.StaffContainer;

import java.util.Scanner;

public class HR {
    private final PayLoader loader;
    private final Scanner in = new Scanner(System.in);

    public HR(PayLoader loader) {
        this.loader = loader;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public void viewStaff() {
        if (StaffContainer.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            StaffContainer.listAllStaff();
        }
    }

    public void promoteStaff() {
        int id = readInt("Enter the staff ID to promote:");
        Staff staff = StaffContainer.getStaffById(id);

        if (staff == null) {
            System.out.println("Staff not found.");
            return;
        }

        staff.updateScalePoint(loader);
        System.out.println("Staff member promoted successfully:");
        System.out.println(staff);
    }

    public void removeStaff() {
        int id = readInt("Enter the staff ID to remove:");
        Staff staff = StaffContainer.getStaffById(id);

        if (staff == null) {
            System.out.println("Staff not found.");
            return;
        }

        StaffContainer.removeStaffById(id);
        System.out.println("Staff member removed successfully.");
    }
}
