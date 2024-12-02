package com.mycompany.payrollsystem.system.ui;

import com.mycompany.payrollsystem.system.PayrollSystem;
import com.mycompany.payrollsystem.system.StaffContainer;
import com.mycompany.payrollsystem.system.user.Admin;
import com.mycompany.payrollsystem.system.user.Employee;
import com.mycompany.payrollsystem.system.user.HR;
import java.util.Scanner;

import static com.mycompany.payrollsystem.stresstest.StaffLoader.addRandomStaffFromDatabase;

public class CLI {
    private final Scanner in = new Scanner(System.in);
    private final PayrollSystem payrollSystem = new PayrollSystem();
    private static final String ADMIN_PASSWORD = "admin123";    //passwords are predefined
    private static final String HR_PASSWORD = "hr123";

    /**
     * Method to initiate the payroll system
     * Allows user to select from 3 roles (admin/HR/Employee) to login as
     * Runs the relevant cli method based on which one is chosen
     * For both admin and hr, the authentication method is run first to ensure the user is an admin or hr staff
     * If correct, their respective cli environment is run
     * If incorrect 3 times, they are logged out.
     * The employee login environment combines these steps into one
     */
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

    /**
     * Ensures a maximum of 3 login attempts to prevent against password brute forcing
     * If admin password is correct, return true which will allow the above method to run the admin cli environment
     * if password is false 3 or more times, return false which will not allow the user to run the admin cli
     * @return true if user inputs correct password in under 3 attempts, false if they don't
     */

    private boolean authenticateAdmin() {
        int attemptsRemaining = 3;

        while (true) {
            System.out.println("Enter admin password:");
            String password = in.nextLine().trim();

            if (ADMIN_PASSWORD.equals(password)) {
                System.out.println("Admin login successful!");
                return true;

            } else {
                attemptsRemaining--;
                if (attemptsRemaining > 0) {
                    System.out.println("Invalid password. You have " + attemptsRemaining + " attempt/s remaining.");
                } else {
                    System.out.println("Invalid password. Access denied. Too many attempts.");
                    return false;
                }
            }
        }
    }


    // HR authentication

    /**
     * Ensures a maximum of 3 login attempts to prevent against password brute forcing
     * If HR password is correct, return true which will allow the above method to run the HR cli environment
     * if password is false 3 or more times, return false which will not allow the user to run the HR cli
     * @return true if user inputs correct password in under 3 attempts, false if they don't
     */

    // pretty much the same code as authenticateAdmin()
    private boolean authenticateHR() {
        int attemptsRemaining = 3;
        while (true) {
            System.out.println("Enter HR password:");
            String password = in.nextLine().trim();

            if (HR_PASSWORD.equals(password)) {
                System.out.println("HR login successful!");
                return true;
            } else {
                attemptsRemaining--;
                if (attemptsRemaining > 0) {
                    System.out.println("Invalid password. You have " + attemptsRemaining + " attempt/s remaining.");
                } else {
                    System.out.println("Invalid password. Access denied. Too many attempts.");
                    return false;
                }
            }
        }
    }

    // Employee authentication

    /**
     * Method to authenticate an employees credentials, and run the employeeCli
     * Prompts user for their id and verifies it exists
     * Prompts user for the password associated with that id
     * If the user enters the password incorrectly 3 times they are logged out
     * If the user enters the correct password, the employeeCli is run
     */
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
            return;
        }


        System.out.println("Enter your password:");

        int attemptsRemaining = 3;

        while (true) {
            String inputPassword = in.nextLine().trim();

            if (staff.getPassword().equals(inputPassword)) {
                System.out.println("Password accepted. Welcome!");
                runEmployeeCLI(new Employee(staff));
                return;

            } else {
                attemptsRemaining--;
                if (attemptsRemaining > 0) {
                    System.out.println("Invalid password. You have " + attemptsRemaining + " attempt/s remaining.");
                } else {
                    System.out.println("Too many failed attempts. You are now logged out.");
                    return;
                }
            }
        }
    }

    /**
     * A method to display the admin cli
     * Displays an admin menu of 5 choices, add/view staff, generate payslips manually, logout
     * And load sample employees (this method is here just for use during the project interview)
     * Depending on which option the user picks using the cli, the respective method is run
     * @param adminAccess instance of the Admin class
     */

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
                    int numberOfEmployees = adminAccess.readInt("Enter the number of random employees to generate:");
                    addRandomStaffFromDatabase("src/database/SampleEmployees.csv", numberOfEmployees);
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    /**
     * A method to display the HR cli
     * Displays an HR menu of 4 choices, perform annual promotion for a full time employee
     * Promote a fullTime employee to a new salary scale
     * Promote a partTime employee to their next scalePoint, and logout
     * Depending on which option the user picks using the cli, the respective method is run
     * @param hrAccess instance of the hr class
     */

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
                    hrAccess.promoteToNextScalePoint();
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

    /**
     * A method to display the employees cli
     * Displays an employee menu of 4 choices, the employee can view their current details
     * View any generated payslips, submit a payClaim (only works for partTime Employees), and logout.
     * Depending on which option the user picks using the cli, the respective method is run
     * @param employeeAccess instance of the employee class
     */

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

    /**
     * A method that allows a partTime employee to submit a payClaim
     * The employee submits their hours worked, which is then stored
     * If the employee trying to run this method is of type FullTimeEmployee, they get an error
     * @param employeeAccess instance of the employee class
     */

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

    /**
     * A helper method used within the submitPayClaim method
     * Ensures that the value passed by the user is a double
     * This is done to prevent errors from incorrect types being passsed into methods relating to pay
     * @return the double passed by the user through the
     */


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