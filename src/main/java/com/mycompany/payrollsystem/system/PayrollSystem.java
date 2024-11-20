
package com.mycompany.payrollsystem.system;


import com.mycompany.payrollsystem.staff.Staff;
import java.time.LocalDate;


public class PayrollSystem {
    private double lowerRate = .2;
    private double upperRate = .4;
    private double uscRate = 0;
    double prsiRate =.04;


    public Payslip generatePayslip(Staff staff, double salary) {
        double grossPay = salary / 12; //
        double tax = calculateTax(salary, grossPay);
        double netPay = grossPay - tax;
        String payPeriod = LocalDate.now().getMonth().toString() + " " + LocalDate.now().getYear();





        netPay = grossPay-tax;


        return new Payslip(staff.getName(), staff.getId(), grossPay, tax, netPay, payPeriod);


    }
    private double calculateTax(double Salary, double grossPay) {
        double tax = 0;

        if (Salary <= 42000) {
            tax += grossPay * lowerRate;
        } else {
            tax += (42000 / 12) * lowerRate;
            tax += ((Salary - 42000) / 12) * upperRate;
        }

        uscRate = calculateUSCRate(Salary);
        tax += grossPay * uscRate;

        tax += grossPay * prsiRate;

        return tax;
    }
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
}
