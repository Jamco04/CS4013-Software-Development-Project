package com.mycompany.payrollsystem.system.ui;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.PayrollSystem;
import com.mycompany.payrollsystem.system.StaffContainer;
import com.mycompany.payrollsystem.system.user.Admin;
import com.mycompany.payrollsystem.system.user.Employee;
import com.mycompany.payrollsystem.system.user.HR;


import java.util.Scanner;

import static com.mycompany.payrollsystem.system.StaffLoader.addRandomStaffFromDatabase;

public class CLI {
    private final Scanner in = new Scanner(System.in);
    private final PayLoader loader = new PayLoader();
    private final PayrollSystem payrollSystem = new PayrollSystem();
    private static final String ADMIN_PASSWORD = "admin123";    //passwords are predefined
    private static final String HR_PASSWORD = "hr123";

    public void run() {
        while (true) {
            System.out.println("Welcome to the Payroll System!");
            System.out.println("Please enter your role (Admin/HR/Employee) or type 'quit' to exit:");
            if (!in.hasNextLine()) break; // Avoid exceptions if input ends (I wrote this for JUnit (Automatic input))
            String role = in.nextLine().trim().toLowerCase();


            switch (role) {
                case "admin":
                    if (authenticateAdmin()) {
                        runAdminCLI(new Admin());
                    }
                    break;
                case "hr":
                    if (authenticateHR()) {
                        runHRCLI(new HR());
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
        final int MaxAttempts = 3;
        int attempts = 0;

        while (attempts < MaxAttempts) {
            System.out.println("Enter admin password:");
            String password = in.nextLine().trim();

            if (ADMIN_PASSWORD.equals(password)) {
                System.out.println("Admin login successful!");
                return true;
            } else {
                attempts++;
                if (attempts < MaxAttempts) {
                    System.out.println("Invalid password. Try again.");
                } else {
                    System.out.println("Invalid password. Access denied. Too many attempts.");
                }
            }
        }
        return false;
    }

    // HR authentication
    private boolean authenticateHR() {
        final int MaxAttempts = 3;
        int attempts = 0;
        while (attempts < MaxAttempts) {
            System.out.println("Enter HR password:");
            String password = in.nextLine().trim();

            if (HR_PASSWORD.equals(password)) {
                System.out.println("HR login successful!");
                return true;
            } else {
                attempts++;
                if (attempts < MaxAttempts) {
                    System.out.println("Invalid password. Try again.");
                } else {
                    System.out.println("Invalid password. Access denied. Too many attempts.");
                }
            }
        }
        return false;
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
            System.out.println("Enter your password:");
            int maxAttempts = 3;

            while (maxAttempts > 0) {
                String inputPassword = in.nextLine().trim();

                if (staff.getPassword().equals(inputPassword)) {
                    System.out.println("Password accepted. Welcome!");
                    runEmployeeCLI(new Employee(staff));
                    return;
                } else {
                    maxAttempts--;
                    if (maxAttempts > 0) {
                        System.out.println("Invalid password. You have " + maxAttempts + " attempt(s remaining.");
                    } else {
                        System.out.println("Too many failed attempts. You are now logged out.");
                    }
                }
            }
        }
    }

    private void runAdminCLI(Admin adminAccess) {
        boolean more = true;
        while (more) {
            System.out.println("Admin Menu: \n1) Add Staff \n2) View Staff \n3) Generate Payslips \n4) Logout \n5) Load sample employees");
            String command = in.nextLine().trim();
            switch (command) {
                case "1":
                    adminAccess.addStaff();
                    break;
                case "2":
                    adminAccess.viewStaff();
                    break;
                case "3":
                    payrollSystem.generateMonthlyPayslips();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    more = false;
                    break;

                case "5":
                    Admin admin = new Admin();// instantiating again
                    int numberOfEmployees = admin.readInt("Enter the number of random employees to generate:");
                    addRandomStaffFromDatabase("src/database/SampleEmployees.csv",numberOfEmployees);
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void runHRCLI(HR hrAccess) {
        boolean more = true;
        while (more) {
            System.out.println("HR Menu: \n1) Perform Annual Promotion (Full-Time) \n2) Promote to New Salary Scale (Full-Time) \n3) Promote to Next Scale Point (Part-Time) \n4) Logout");
            String command = in.nextLine().trim();
            switch (command) {
                case "1":
                    hrAccess.annualPromotion();
                    break;
                case "2":
                    hrAccess.promoteToNextSalaryScale();
                    break;
                case "3":
                    //hrAccess.promoteToNextScalePoint();
                    break;
                case "4":
                    System.out.println("Logging out...");
                    more = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void runEmployeeCLI(Employee employeeAccess) {
        boolean more = true;
        while (more) {
            System.out.println("Employee Menu: \n1) View Details \n2) View Payslips \n3) Submit Pay Claim \n4) Logout");
            String command = in.nextLine().trim();
            switch (command) {
                case "1":
                    employeeAccess.viewDetails();
                    break;
                case "2":
                    employeeAccess.viewPayslips();
                    break;
                case "3":
                    submitPayClaim(employeeAccess);
                    break;
                case "4":
                    System.out.println("Logging out...");
                    more = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void submitPayClaim(Employee employeeAccess) {
        if (employeeAccess.getStaff() instanceof com.mycompany.payrollsystem.staff.PartTimeEmployee partTimeEmployee) {
            double hoursWorked;
            while (true){
                System.out.println("Enter hours worked for pay claim:");
                hoursWorked = readDouble();
                if (hoursWorked < 0){   //discovered through JUnit testing
                    System.out.println("Hours worked cannot be negative.");
                }
                else{
                    break;
                }

            }
            payrollSystem.addPayClaim(partTimeEmployee.getId(), hoursWorked);
        } else {
            System.out.println("Only part-time employees can submit pay claims.");
        }
    }


    // Helper method to safely read a double value
    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
