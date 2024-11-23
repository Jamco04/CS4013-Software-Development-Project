package com.mycompany.payrollsystem.system.UI;

import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.user.Admin;

import java.util.Scanner;

public class CLI {
    private final Scanner in = new Scanner(System.in);

    public void run() {
        System.out.println("Welcome to the Payroll System!");

        String role;
        while (true) {
            System.out.println("Please enter your role (Admin/HR/Employee):");
            role = in.nextLine().trim().toLowerCase();
            if (role.equals("admin") || role.equals("hr") || role.equals("employee")) {
                break;
            }
            System.out.println("Invalid role. Please try again.");
        }

        switch (role) {
            case "admin":
                Admin adminAccess = new Admin();
                runAdminCLI(adminAccess);
                break;
            case "hr":
                // HR CLI implementation (do this next
                System.out.println("HR CLI under development.");
                break;
            case "employee":
                // Employee CLI implementation (do this next
                System.out.println("Employee CLI under development.");
                break;
        }
    }

    private void runAdminCLI(Admin adminAccess) {
        boolean more = true;
        while (more) {
            System.out.println("Admin Menu: \n1) Add Staff \n2) View Staff \n3) Quit");
            String command = in.nextLine().trim();
            switch (command) {
                case "1":
                    adminAccess.addStaff();
                case "2":
                    adminAccess.viewStaff();
                case "3":
                    more = false;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}
