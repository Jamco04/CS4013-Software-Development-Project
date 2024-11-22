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


    // ADD STUFF

    public void addStaff() {

        //TAKE INPUTS NEEDED TO CREATE A STUFF MEMBER

        System.out.println("Enter staff type: (1) full-time (2) part-time:");
        int type = Integer.parseInt(in.nextLine());
        while (!(type == 1 || type == 2)){
            System.out.println("Invalid staff type. Please try again.");
            System.out.println("Enter staff type: (1) full-time (2) part-time:");
            type = Integer.parseInt(in.nextLine());
        }

        System.out.println("Enter staff name:");
        String name = in.nextLine();

        System.out.println("Enter staff ID:");
        int id = Integer.parseInt(in.nextLine());

        System.out.println("Enter staff title:");
        String title = in.nextLine();

        System.out.println("Enter scale point:");
        int scalePoint = Integer.parseInt(in.nextLine());   //should implement to check if scalepoint is valid

        if (type == 1) {    //full-time
            System.out.println("Enter category:");
            String category = in.nextLine();

            FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(name, id, category, title, scalePoint);
            StaffContainer.add(fullTimeEmployee);

            System.out.println("Full-time employee added successfully!");

        } else if (type == 2) { //part-time
            System.out.println("Enter hours worked:");
            double hoursWorked = in.nextInt();

            PartTimeEmployee partTimeEmployee = new PartTimeEmployee(name, id, title, scalePoint, hoursWorked);
            StaffContainer.add(partTimeEmployee);

            System.out.println("Part-time employee added successfully!");

        }
    }

    // VIEW STUFF

    public void viewStaff() {
        if (StaffContainer.isEmpty()) {
            System.out.println("No staff members found.");
            return;
        }

        StaffContainer.listAllStaff();
    }


}
