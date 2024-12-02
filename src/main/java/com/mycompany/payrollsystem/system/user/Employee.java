package com.mycompany.payrollsystem.system.user;

import com.mycompany.payrollsystem.staff.Staff;

/**
 * The Employee class represents an employee in the payroll system, and
 * Allows them to view their personal details and payslips
 */

// intelliJ seems to suggest this class can be a record?
// Future investigation may be required
public class Employee {
    private final Staff staff;

    /**
     * Constructs an Employee object with the specified staff member
     * @param staff staff member associated with this employee
     */
    public Employee(Staff staff) {
        this.staff = staff;
    }

    /**
     * Displays the personal details of the employee
     * This method prints out the details of the staff member associated with the employee
     */
    public void viewDetails() {
        System.out.println("Your Details:");
        System.out.println(staff);
    }

    /**
     * Displays the payslips of the employee
     * This method carry's out the viewPayslips method on the employee
     */
    public void viewPayslips() {
        staff.viewPayslips();
    }

    /**
     * Returns the staff member associated with this employee
     * @return staff member associated with this employee
     */
    public Staff getStaff() {
        return staff;
    }
}
