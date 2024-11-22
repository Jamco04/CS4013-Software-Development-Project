package com.mycompany.payrollsystem.system.user;


import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.PayLoader;

import java.util.ArrayList;
import java.util.Scanner;
public class HR {

    private final ArrayList<Staff> staffList;
    private final Scanner scanner = new Scanner(System.in);

    public HR(ArrayList<Staff> staffList) {
        this.staffList = staffList;
    }

    /**
     * Promote a staff member.
     */
    public void promoteStaff() {
        System.out.println("Enter staff ID to promote:");
        int id = Integer.parseInt(scanner.nextLine());

        Staff staffToUpdate = null;
        for (Staff staff : staffList) {
            if (staff.getId() == id) {
                staffToUpdate = staff;
                break;
            }
        }

        if (staffToUpdate == null) {
            System.out.println("Staff not found.");
            return;
        }

        System.out.println("Enter new scale point:");
        int newScalePoint = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter new salary/pay rate:");
        double newPay = Double.parseDouble(scanner.nextLine());

        //staffToUpdate.updateScalePoint(newScalePoint);
        System.out.println("Staff details updated successfully!");
    }

}

