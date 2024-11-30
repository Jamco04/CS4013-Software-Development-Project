package com.mycompany.payrollsystem.system.javafx;

import com.mycompany.payrollsystem.system.user.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuController {

    private final Admin admin = new Admin(); // Use the Admin class for functionalities

    @FXML
    private Button addStaffButton;

    @FXML
    private Button viewStaffButton;

    @FXML
    private Button generatePayslipsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private void handleAddStaff() {
        // Call the add staff functionality
        admin.addStaff();
        showAlert("Success", "Staff added successfully!", AlertType.INFORMATION);
    }

    @FXML
    private void handleViewStaff() {
        // Call the view staff functionality
        admin.viewStaff();
        showAlert("Success", "Staff details have been displayed.", AlertType.INFORMATION);
    }

    @FXML
    private void handleGeneratePayslips() {
        try {
            // Generate payslips functionality
            admin.generatePayslips();
            showAlert("Success", "Payslips generated successfully!", AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "Failed to generate payslips: " + e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    private void handleLogout() {
        // Close the current admin menu and return to the login screen
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
