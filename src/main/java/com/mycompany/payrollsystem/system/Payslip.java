package com.mycompany.payrollsystem.system;

/**
 * Represents a payslip for an employee containing details such as gross pay, tax, net pay, and the pay period.
 */
public class Payslip {

    private String name;
    private int id;
    private double grossPay;
    private double tax;
    private double netPay;
    private String payPeriod;

    /**
     * Constructor for payslips
     * Takes the below parameters and creates a payslip obhject
     * @param name The name of the employee
     * @param id The unique ID of the employee
     * @param grossPay the gross pay of the employee for the pay period
     * @param tax The amount of tax deducted from the gross pay
     * @param netPay The net pay of the employee after tax
     * @param payPeriod The pay period for which the payslip is generated
     */
    public Payslip(String name, int id, double grossPay, double tax, double netPay, String payPeriod) {
        this.name = name;
        this.id = id;
        this.grossPay = grossPay;
        this.tax = tax;
        this.netPay = netPay;
        this.payPeriod = payPeriod;
    }

    /**
     * Returns the name of the employee
     * @return name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unique ID of the employee
     * @return employee's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the gross pay of the employee for the pay period
     * @return gross pay
     */
    public double getGrossPay() {
        return grossPay;
    }

    /**
     * Returns the amount of tax deducted from the gross pay
     * @return amount of tax
     */
    public double getTax() {
        return tax;
    }

    /**
     * Returns the net pay of the employee after tax
     * @return amount of net pay
     */
    public double getNetPay() {
        return netPay;
    }

    /**
     * Returns the pay period for which the payslip was generated
     * @return pay period
     */
    public String getPayPeriod() {
        return payPeriod;
    }
}
