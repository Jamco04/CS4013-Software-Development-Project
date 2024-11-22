package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.PayLoader;

import java.util.Scanner;

public class Employee {

    private final Staff staff; // Current logged-in employee
    private final Scanner scanner = new Scanner(System.in);
    private PayLoader loader;

    public Employee(Staff staff) {
        this.staff = staff;
        this.loader = new PayLoader();
    }

    /**
     * View the logged-in employee's details.
     */
    public void viewDetails() {
        System.out.println("Employee Details:");
        System.out.println(staff);
    }

    /**
     * Calculate and display the employee's pay.
     */
    public void calculatePay() {
        System.out.println("Calculating pay...");
        System.out.printf("Your pay is: %.2f%n", staff.getPay(loader));
    }
}
