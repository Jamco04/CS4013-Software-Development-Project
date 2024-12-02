package com.mycompany.payrollsystem.system;

import com.mycompany.payrollsystem.staff.FullTimeEmployee;
import com.mycompany.payrollsystem.staff.PartTimeEmployee;
import com.mycompany.payrollsystem.staff.Staff;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The PayrollSystem class is responsible for handling payroll-related operations
 * including adding pay claims, generating payslips, calculating tax, and saving payslips to a CSV file
 */
public class PayrollSystem {
    private final HashMap<Integer, Double> payClaims = new HashMap<>(); // Tracks part-time employee pay claims
    private static final double LOWER_RATE = 0.2; // Lower tax rate for income tax calculation
    private static final double UPPER_RATE = 0.4; // Upper tax rate for income tax calculation
    private static final double PRSI_RATE = 0.04; // PRSI (Pay Related Social Insurance) rate
    private static final double UNION_FEE = 35; // Union fee for each employee
    private static final double HEALTH_INSURANCE_FEE = 40; // Health insurance fee for each employee

    /**
     * Adds a payClaim for a part-time employee
     * @param staffId The ID of the staff member submitting the payClaim
     * @param hoursWorked The number of hours worked by the employee
     * @return True if the payClaim is successfully added, false otherwise
     */
    public boolean addPayClaim(int staffId, double hoursWorked) {
        LocalDate today = LocalDate.now();
        LocalDate secondFriday = today.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));

        if (today.isBefore(secondFriday)) { // After testing, change to isAfter
            System.out.println("Pay claims can no longer be submitted for this month.");
            return false;
        }

        if (payClaims.containsKey(staffId)) {
            System.out.println("A pay claim for this month has already been submitted by employee ID: " + staffId);
            return false;
        }

        Staff staff = StaffContainer.getStaffById(staffId);
        if (staff == null) {
            System.out.println("Employee not found with ID: " + staffId);
            return false;
        }

        if (!(staff instanceof PartTimeEmployee)) {
            System.out.println("Pay claims can only be submitted for part-time employees.");
            return false;
        }

        payClaims.put(staffId, hoursWorked);
        System.out.println("Pay claim submitted for staff ID " + staffId + " with " + hoursWorked + " hours worked.");
        return true;
    }

    /**
     * Generates monthly payslips for all staff members
     * @return A list of payslips for all staff members
     */
    public ArrayList<Payslip> generateMonthlyPayslips() {
        ArrayList<Payslip> payslips = new ArrayList<>();
        LocalDate today = LocalDate.now();

        if (StaffContainer.isEmpty()) {
            System.out.println("No employees found. Payslips generation skipped.");
            return payslips;
        }

        if (today.getDayOfMonth() == 25) {  // After testing, change to !=25
            System.out.println("Payslips are generated only on the 25th of the month.");
            return payslips;
        }

        for (Staff staff : StaffContainer.getAllStaff()) {  // For every staff member
            Payslip payslip = generatePayslipForEmployee(staff);
            if (payslip != null) {
                payslips.add(payslip);
                System.out.println("Payslip generated for: " + staff.getName());
            }
        }

        // Clear pay claims after generating payslips
        clearPayClaims();
        savePayslipsToCSV(payslips);
        return payslips;
    }

    /**
     * Generates a payslip for a specific employee
     * @param staff staff member for whom the payslip is to be generated
     * @return generated payslip for the employee, or null if the payslip cannot be generated
     */
    public Payslip generatePayslipForEmployee(Staff staff) {
        double grossPay;

        if (staff instanceof FullTimeEmployee fullTimeEmployee) {
            grossPay = fullTimeEmployee.getSalary() / 12; // Monthly pay
        } else if (staff instanceof PartTimeEmployee partTimeEmployee) {
            if (!payClaims.containsKey(partTimeEmployee.getId())) {
                System.out.println("No pay claim found for part-time employee: " + partTimeEmployee.getName());
                return null;
            }
            double hoursWorked = payClaims.get(partTimeEmployee.getId());
            grossPay = partTimeEmployee.getPay(hoursWorked);
        } else {
            return null;
        }

        double tax = calculateTax(grossPay * 12); // Annual salary for tax calculation
        double netPay = grossPay - tax;
        String payPeriod = LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear();

        Payslip payslip = new Payslip(staff.getName(), staff.getId(), grossPay, tax, netPay, payPeriod);

        staff.addPayslip(payslip);

        return payslip;
    }

    /**
     * Clears all pay claims for the current month.
     */
    void clearPayClaims() {
        payClaims.clear();
        System.out.println("All pay claims have been cleared.");
    }

    /**
     * Saves the generated payslips to a CSV file.
     * @param payslips list of payslips to save
     */
    private void savePayslipsToCSV(ArrayList<Payslip> payslips) {
        String fileName = "Payslips_" + LocalDate.now() + ".csv";
        String fullPath = "src/output/" + fileName;
        try (FileWriter writer = new FileWriter(fullPath)) {
            // Write the CSV header
            writer.write("Name,ID,Pay Period,Gross Pay,Tax,Net Pay\n");

            // Write each payslip as a row
            for (Payslip payslip : payslips) {
                writer.write(String.format("%s,%d,%s,%.2f,%.2f,%.2f\n",
                        payslip.getName(),
                        payslip.getId(),
                        payslip.getPayPeriod(),
                        payslip.getGrossPay(),
                        payslip.getTax(),
                        payslip.getNetPay()));
            }

            System.out.println("Payslips saved to file: " + fullPath);
        } catch (IOException e) {
            System.out.println("Error saving payslips to CSV: " + e.getMessage());
        }
    }

    /**
     * Calculates the tax for an employee based on their annual salary.
     * @param annualSalary annual salary of the employee
     * @return calculated tax for the employee
     */
    private double calculateTax(double annualSalary) {
        double tax = 0;

        // Income tax
        if (annualSalary <= 42000) {
            tax += (annualSalary / 12) * LOWER_RATE;
        } else {
            tax += (42000 / 12) * LOWER_RATE;
            tax += ((annualSalary - 42000) / 12) * UPPER_RATE;
        }

        // USC
        tax += (annualSalary / 12) * calculateUSCRate(annualSalary);

        // PRSI, Union Fee, Health Insurance
        tax += (annualSalary / 12) * PRSI_RATE;
        tax += UNION_FEE + HEALTH_INSURANCE_FEE;

        return tax;
    }

    /**
     * Calculates the Universal Social Charge (USC) rate based on the annual salary.
     * @param annualSalary annual salary of the employee
     * @return USC rate applicable for the given salary
     */
    private double calculateUSCRate(double annualSalary) {
        if (annualSalary <= 12012) {
            return 0.005;
        } else if (annualSalary <= 25760) {
            return 0.02;
        } else if (annualSalary <= 70044) {
            return 0.04;
        } else {
            return 0.08;
        }
    }

    /**
     * Generates and prints payslips for all employees.
     */
    public void generateAndPrintPayslips() {
        ArrayList<Payslip> payslips = generateMonthlyPayslips();
        for (Payslip payslip : payslips) {
            System.out.println("Payslip for: " + payslip.getName());
            System.out.println("ID: " + payslip.getId() + ", Gross Pay: " + payslip.getGrossPay() + ", Net Pay: " + payslip.getNetPay());
        }
    }
}
