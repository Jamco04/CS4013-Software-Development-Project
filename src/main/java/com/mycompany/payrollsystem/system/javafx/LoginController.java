package com.mycompany.payrollsystem.system.javafx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        roleComboBox.setItems(FXCollections.observableArrayList("Admin", "HR", "Employee"));
    }

    @FXML
    private void handleLogin() {
        String role = roleComboBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (role == null || username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter all fields", AlertType.ERROR);
            return;
        }

        try {
            if (authenticate(role, username, password)) {
                showAlert("Login Success", "Welcome " + username + "!", AlertType.INFORMATION);
                // Proceed to next part of your application
            } else {
                showAlert("Login Failed", "Invalid credentials or role", AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("System Error", "An error occurred: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private boolean authenticate(String role, String username, String password) {
        // Implement your authentication logic here
        return true; // Placeholder
    }

    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
