package com.mycompany.payrollsystem.system.UI;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.StaffContainer;
import com.mycompany.payrollsystem.system.user.Admin;
import com.mycompany.payrollsystem.system.user.Employee;
import com.mycompany.payrollsystem.system.user.HR;


import java.util.Scanner;

public class CLI {
    private final Scanner in = new Scanner(System.in);
    private final PayLoader loader = new PayLoader();
    //passwords are predefined
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String HR_PASSWORD = "hr123";


    public void run() {
        while (true) {
            System.out.println("Welcome to the Payroll System!");
            System.out.println("Please enter your role (Admin/HR/Employee) or type 'quit' to exit:");
            String role = in.nextLine().trim().toLowerCase();


            switch (role) {
                case "admin":
                    if (authenticateAdmin()) {
                        runAdminCLI(new Admin());
                    }
                    break;
                case "hr":
                    if (authenticateHR()) {
                        runHRCLI(new HR(loader));
                    }
                    break;
                case "employee":
                    authenticateAndRunEmployee();
                    break;
                case "quit":
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid role. Please try again.");
            }
        }
    }



    // Admin authentication
    private boolean authenticateAdmin() {
        System.out.println("Enter admin password:");
        String password = in.nextLine().trim();
        if (ADMIN_PASSWORD.equals(password)) {
            System.out.println("Admin login successful!");
            return true;
        } else {
            System.out.println("Invalid password. Access denied.");
            return false;
        }
    }

    // HR authentication
    private boolean authenticateHR() {
        System.out.println("Enter HR password:");
        String password = in.nextLine().trim();
        if (HR_PASSWORD.equals(password)) {
            System.out.println("HR login successful!");
            return true;
        } else {
            System.out.println("Invalid password. Access denied.");
            return false;
        }
    }

    // Employee authentication
    private void authenticateAndRunEmployee() {
        System.out.println("Enter your ID:");
        int id;
        try {
            id = Integer.parseInt(in.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please try again.");
            return;
        }

        var staff = StaffContainer.getStaffById(id);
        if (staff == null) {
            System.out.println("Employee not found. Please contact the admin.");
        } else {
            System.out.println("Employee login successful!");
            runEmployeeCLI(new Employee(staff));
        }
    }

    private void runAdminCLI(Admin adminAccess) {
        boolean more = true;
        while (more) {
            System.out.println("Admin Menu: \n1) Add Staff \n2) View Staff \n3) Logout");
            String command = in.nextLine().trim();
            switch (command) {
                case "1":
                    adminAccess.addStaff();
                    break;
                case "2":
                    adminAccess.viewStaff();
                    break;
                case "3":
                    System.out.println("Logging out...");
                    more = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void runHRCLI(HR hrAccess) {
        while (true) {
            System.out.println("HR Menu: \n1) View Staff \n2) Promote Staff \n3) Remove Staff \n4) Logout");
            String command = in.nextLine().trim();
            switch (command) {
                case "1":
                    hrAccess.viewStaff();
                    break;
                case "2":
                    hrAccess.promoteStaff();
                    break;
                case "3":
                    hrAccess.removeStaff();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void runEmployeeCLI(Employee employeeAccess) {
        while (true) {
            System.out.println("Employee Menu: \n1) View Details \n2) Generate Payslip \n3) Logout");
            String command = in.nextLine().trim();
            switch (command) {
                case "1":
                    employeeAccess.viewDetails();
                    break;
                case "2":
                    employeeAccess.generatePayslip();
                    break;
                case "3":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}
