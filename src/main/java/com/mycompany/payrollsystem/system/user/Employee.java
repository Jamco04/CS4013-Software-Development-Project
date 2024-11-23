package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.system.PayLoader;
import com.mycompany.payrollsystem.system.PayrollSystem;
import com.mycompany.payrollsystem.system.Payslip;

public class Employee {
    private final Staff staff;
    private final PayLoader loader = new PayLoader();
    private final PayrollSystem payrollSystem = new PayrollSystem();

    public Employee(Staff staff) {
        this.staff = staff;
    }

    public void viewDetails() {
        System.out.println("Your Details:");
        System.out.println(staff);
    }

    public void generatePayslip() {
        System.out.println("Generating payslip...");
        double salary = staff.getPay(loader);
        if (salary == -1) {
            System.out.println("Error: Unable to calculate salary. Please check staff details.");
            return;
        }

        Payslip payslip = payrollSystem.generatePayslip(staff, salary);
        payrollSystem.displayPayslip(payslip);
        System.out.println("Payslip generated and saved.");
    }
}
