package com.mycompany.payrollsystem.system.UI;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.user.Admin;

import java.util.Scanner;

/**
 A Command Line Interface for the payroll system.
 */

public class CLI
{
    private Scanner in;

    public CLI()
    {
        in = new Scanner(System.in);
    }

    PayLoader loader = new PayLoader();

    /**
     Runs the system.
     */

    public void run()
    {
        System.out.println("Welcome to the Payroll System!");
        System.out.println("Please enter your role (Admin/HR/Employee):");
        String role = in.nextLine().toLowerCase();

        boolean more = false;

        while (more) {
            switch (role) {
                case "admin":
                    Admin adminAccess = new Admin(loader);
                    runAdminCLI(adminAccess);
                    break;
                case "hr":
                    runHRCLI();
                    break;
                case "employee":
                    runEmployeeCLI();
                    break;
                default:
                    System.out.println("Please enter a VALID role (Admin/HR/Employee):");
                    more = true;
            }
        }
    }

    private void runAdminCLI(Admin adminAccess) {
        boolean more = true;
        while (more) {
            System.out.println("Admin Menu: \n1) Add Staff \n2) View Staff \n3) Quit");
            String command = in.nextLine();
            switch (command) {
                case "1":
                    adminAccess.addStaff();
                    break;
                case "2":
                    adminAccess.viewStaff();
                    break;
                case "3":
                    more = false;
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }


    private void runHRCLI() {
        // Similar menu but tailored for HR tasks like viewing payslips, updating details, etc.
    }

    private void runEmployeeCLI() {
        // Similar menu but limited to viewing personal details and payslip.
    }
}
