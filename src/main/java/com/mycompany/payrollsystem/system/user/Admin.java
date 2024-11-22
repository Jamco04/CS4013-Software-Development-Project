package com.mycompany.payrollsystem.system.user;
import com.mycompany.payrollsystem.staff.*;
import com.mycompany.payrollsystem.system.PayLoader;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {

    private final ArrayList<Staff> staffList = new ArrayList<>();
    private PayLoader payLoader;
    private final Scanner scanner = new Scanner(System.in);

    public Admin(PayLoader loader) {
        this.payLoader = loader;
    }
    public void addStaff() {

        System.out.println("Enter staff type (full-time/part-time):");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.println("Enter staff name:");
        String name = scanner.nextLine();

        System.out.println("Enter staff ID:");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter staff title:");
        String title = scanner.nextLine();

        System.out.println("Enter scale point:");
        int scalePoint = Integer.parseInt(scanner.nextLine());

        if ("full-time".equals(type)) {
            System.out.println("Enter category:");
            String category = scanner.nextLine();

            FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(name, id, category, title, scalePoint);
            fullTimeEmployee.calculateSalary(payLoader);
            staffList.add(fullTimeEmployee);

            System.out.println("Full-time employee added successfully!");

        } else if ("part-time".equals(type)) {
            PartTimeEmployee partTimeEmployee = new PartTimeEmployee(name, id, title, scalePoint);
            partTimeEmployee.calculatePayRate(payLoader);
            staffList.add(partTimeEmployee);

            System.out.println("Part-time employee added successfully!");

        } else {
            System.out.println("Invalid staff type. Please try again.");
        }
    }

    public void viewStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff members found.");
            return;
        }

        for (Staff staff : staffList) {
            System.out.println(staff);
        }
    }


}
