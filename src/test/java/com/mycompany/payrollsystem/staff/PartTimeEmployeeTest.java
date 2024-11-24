package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartTimeEmployeeTest {
    private static PayLoader loader;

    @BeforeAll
    static void setUp() throws Exception {
        loader = new PayLoader();
        loader.loadPay("src/database/Salaries.csv"); // Replace with your actual path
    }

    @Test
    void testPayCalculation() {
        PartTimeEmployee employee = new PartTimeEmployee("Alice", 1, "LabTutor", 1, "password");

        double hoursWorked = 10.5;
        double expectedPay = loader.getPay("", "LabTutor", "1") * hoursWorked;
        assertEquals(expectedPay, employee.getPay(hoursWorked), "Pay should be calculated correctly for hours worked");
    }

    @Test
    void testZeroHoursPay() {
        PartTimeEmployee employee = new PartTimeEmployee("Bob", 2, "LabTutor", 1, "password");

        assertEquals(0.0, employee.getPay(0), "Pay should be zero for zero hours worked");
    }

    @Test
    void testNegativeHoursPay() {
        PartTimeEmployee employee = new PartTimeEmployee("Charlie", 3, "LabTutor", 1, "password");

        assertThrows(IllegalArgumentException.class, () -> employee.getPay(-5), "Negative hours should throw an exception");
    }

    @Test
    void testUpdateScalePoint() {
        PartTimeEmployee employee = new PartTimeEmployee("Dave", 4, "LabTutor", 1, "password");

        assertFalse(employee.updateScalePoint(loader), "Part-time employees should not have scale points updated");
    }

    @Test
    void testToString() {
        PartTimeEmployee employee = new PartTimeEmployee("Eve", 5, "ExamInvigilator", 2, "password");
        String expectedString = "Part-Time Employee: name: Eve | id: 5 | title: ExamInvigilator | scalePoint: 2 | payRate: "
                + loader.getPay("", "ExamInvigilator", "2");
        assertEquals(expectedString, employee.toString(), "String representation should match expected format");
    }
}
