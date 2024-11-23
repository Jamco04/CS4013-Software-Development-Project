package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.Staff;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.system.PayrollSystem;
import com.mycompany.payrollsystem.system.Payslip;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private final Staff staff;
    private final PayrollSystem payrollSystem = new PayrollSystem();
    private final List<Payslip> payslips = new ArrayList<>(); // Stores generated payslips for viewing history

    public Employee(Staff staff) {
        this.staff = staff;
    }

    public void viewDetails() {
        System.out.println("Your Details:");
        System.out.println(staff);
    }


    public void viewPayslips() {
        if (payslips.isEmpty()) {
            System.out.println("No payslips available.");
            return;
        }

        System.out.println("Your Payslips:");
        for (Payslip payslip : payslips) {
            System.out.printf("Pay Period: %s | Net Pay: %.2f\n", payslip.getPayPeriod(), payslip.getNetPay());
        }
    }


    public void submitPayClaim(double hoursWorked) {
        if (staff instanceof PartTimeEmployee partTimeEmployee) {
            partTimeEmployee.submitPayClaim(hoursWorked);
        } else {
            System.out.println("Pay claims can only be submitted by part-time employees.");
        }
    }


    public void generatePayslip() {
        System.out.println("Generating payslip...");
        Payslip payslip = payrollSystem.generatePayslipForEmployee(staff);
        if (payslip != null) {
            payslips.add(payslip);
            System.out.println("Payslip generated successfully.");
        } else {
            System.out.println("Unable to generate payslip. Please ensure all details are correct.");
        }
    }

    public Staff getStaff() {
        return staff;
    }
}
