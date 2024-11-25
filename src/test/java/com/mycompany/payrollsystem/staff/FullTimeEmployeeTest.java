package com.mycompany.payrollsystem.staff;

import com.mycompany.payrollsystem.system.PayLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullTimeEmployeeTest {
    private static PayLoader loader;

    @BeforeAll
    static void setUp() throws Exception {
        loader = new PayLoader();
        loader.loadPay("src/database/Salaries.csv"); // Replace with your actual path
    }

    @Test
    void testUpdateScalePoint() {
        FullTimeEmployee employee = new FullTimeEmployee("Adam", 1, "Academic", "Professor", 1, "123");

        boolean updated = employee.updateScalePoint(loader);
        assertTrue(updated);
        assertEquals(2, employee.getScalePoint(), "Scale point should increment by 1");
    }

    @Test
    void testPromotionToNewTitle() {
        FullTimeEmployee employee = new FullTimeEmployee("Adam", 1, "Academic", "Professor", 6, "123");

        employee.promoteToNewTitle("Full Professor", loader);
        assertEquals("Full Professor", employee.getTitle());
        assertEquals(1, employee.getScalePoint());
        assertEquals(loader.getPay("Academic", "Full Professor", "1"), employee.getSalary(loader));
    }

    @Test
    void testSalaryCalculation() {
        FullTimeEmployee employee = new FullTimeEmployee("Adam", 1, "Academic", "Professor", 3, "123");

        double expectedSalary = loader.getPay("Academic", "Professor", "3");
        assertEquals(expectedSalary, employee.getSalary(loader));
    }

    @Test
    void testPromotionAtTop() {
        FullTimeEmployee employee = new FullTimeEmployee("Adam", 1, "Academic", "Professor", 6, "password");

        boolean promoted = employee.updateScalePoint(loader);
        assertFalse(promoted, "Employee at top scale should not be promotable to a higher scale point");

        employee.promoteToNewTitle("Full Professor", loader);
        assertEquals("Full Professor", employee.getTitle(), "Title should be updated to 'Full Professor'");
        assertEquals(1, employee.getScalePoint(), "Scale point should reset to 1");
    }

}