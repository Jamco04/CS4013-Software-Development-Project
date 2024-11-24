package com.mycompany.payrollsystem.system.UI;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {

    @Test
    void testAdminLogin() {
        String input = "admin\nadmin123\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        assertDoesNotThrow(cli::run, "Admin should log in without issues");
    }

    @Test
    void testHRLoginAndPromotion() {
        String input = "hr\nhr123\n1\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        assertDoesNotThrow(cli::run, "HR should log in and perform promotion without issues");
    }

    @Test
    void testEmployeeLoginAndViewPayslips() {
        String input = "employee\n1\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        CLI cli = new CLI();
        assertDoesNotThrow(cli::run, "Employee should log in and view payslips without issues");
    }
}
