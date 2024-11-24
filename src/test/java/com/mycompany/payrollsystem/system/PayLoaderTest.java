package com.mycompany.payrollsystem.system;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayLoaderTest {
    private static PayLoader loader;

    @BeforeAll
    static void setUp() throws Exception {
        loader = new PayLoader();
        loader.loadPay("src/database/Salaries.csv");
    }

    @Test
    void testLoadPay() {
        int maxScalePoints = loader.getMaxScalePoints("Professor");
        assertTrue(maxScalePoints > 0, "Scale points should be loaded for 'Professor'");

        double salary = loader.getPay("Academic", "Professor", "1");
        assertTrue(salary > 0, "Salary should be greater than 0 for 'Professor' scale point 1");
    }
}
