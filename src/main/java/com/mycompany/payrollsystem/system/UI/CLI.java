package com.mycompany.payrollsystem.system.UI;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.user.Admin;
import com.mycompany.payrollsystem.system.user.HR;

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
                    //HR hr = new HR(loader);
                    //runAdminCLI(hr);
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
/*

    private void runHRCLI(HR hrAccess) {
        while (true) {
            System.out.println("\nHR CLI Options:");
            System.out.println("1. View Staff");
            System.out.println("2. Update Staff");
            System.out.println("3. Remove Staff");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int option = Integer.parseInt(in.nextLine());

            switch (option) {
                case 1:
                    for (Staff staff : staffList) {
                        System.out.println(staff);
                    }
                    break;
                case 2:
                    updateStaff();
                    break;
                case 3:
                    removeStaff();
                    break;
                case 4:
                    System.out.println("Exiting HR CLI...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
*/
    private void runEmployeeCLI() {
        // Similar menu but limited to viewing personal details and payslip.
    }
}
