package com.mycompany.payrollsystem.system.user;
import com.mycompany.payrollsystem.staff.*;
import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.StaffContainer;

import java.util.Scanner;

public class Admin {

    private PayLoader payLoader;
    private final Scanner in = new Scanner(System.in);

    public Admin(PayLoader loader) {
        this.payLoader = loader;
    }
    public void addStaff() {

        System.out.println("Enter staff type (full-time/part-time):");
        String type = in.nextLine().trim().toLowerCase();

        System.out.println("Enter staff name:");
        String name = in.nextLine();

        System.out.println("Enter staff ID:");
        int id = Integer.parseInt(in.nextLine());

        System.out.println("Enter staff title:");
        String title = in.nextLine();

        System.out.println("Enter scale point:");
        int scalePoint = Integer.parseInt(in.nextLine());

        if ("full-time".equals(type)) {
            System.out.println("Enter category:");
            String category = in.nextLine();

            FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(name, id, category, title, scalePoint);
            fullTimeEmployee.getPay(payLoader);
            StaffContainer.add(fullTimeEmployee);

            System.out.println("Full-time employee added successfully!");

        } else if ("part-time".equals(type)) {
            System.out.println("Enter hours worked:");
            double hoursWorked = in.nextInt();

            PartTimeEmployee partTimeEmployee = new PartTimeEmployee(name, id, title, scalePoint, hoursWorked);
            partTimeEmployee.getPay(payLoader);
            StaffContainer.add(partTimeEmployee);

            System.out.println("Part-time employee added successfully!");

        } else {
            System.out.println("Invalid staff type. Please try again.");
        }
    }

    public void viewStaff() {
        if (StaffContainer.isEmpty()) {
            System.out.println("No staff members found.");
            return;
        }

        StaffContainer.listAllStaff();
    }


}
