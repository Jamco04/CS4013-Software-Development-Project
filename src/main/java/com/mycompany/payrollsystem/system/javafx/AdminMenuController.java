package com.mycompany.payrollsystem.system.javafx;

import com.mycompany.payrollsystem.system.PayrollSystem;
import com.mycompany.payrollsystem.system.StaffContainer;
import com.mycompany.payrollsystem.system.user.Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuController {

    private final Admin admin = new Admin();
    private final PayrollSystem payrollSystem = new PayrollSystem();

    @FXML
    private void handleAddStaff() {
        try {
            admin.addStaff();
            showAlert("Add Staff", "Staff member added successfully.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "Failed to add staff: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleViewStaff() {
        if (StaffContainer.isEmpty()) {
            showAlert("View Staff", "No staff members available.", Alert.AlertType.INFORMATION);
        } else {
            try {
                admin.viewStaff(); // Displays staff in the console and saves to a CSV
                showAlert("View Staff", "Staff details displayed and saved to CSV.", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                showAlert("Error", "Failed to view staff: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void handleGeneratePayslips() {
        try {
            payrollSystem.generateMonthlyPayslips(); // Generate payslips for all staff
            showAlert("Generate Payslips", "Payslips generated and saved successfully.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", "Failed to generate payslips: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleLogout() {
        try {
            // Navigate back to the login screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Stage.getWindows()
                    .stream()
                    .filter(window -> window instanceof Stage && window.isShowing())
                    .findFirst()
                    .orElse(null);
            if (stage != null) {
                stage.setScene(new Scene(root));
                stage.setTitle("Login");
            }
        } catch (IOException e) {
            showAlert("Error", "Failed to load login screen: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
