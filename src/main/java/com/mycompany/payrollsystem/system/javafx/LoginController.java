package com.mycompany.payrollsystem.system.javafx;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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
    private AnchorPane anchorPane; // Reference to parent container

    @FXML
    public void initialize() {
        roleComboBox.setItems(FXCollections.observableArrayList("Admin", "HR", "Employee"));
        usernameField.setVisible(false);
        passwordField.setVisible(false);
        loginButton.setVisible(false);
    }



    @FXML
    private void handleLogin() {
        String role = roleComboBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (role == null || password.isEmpty() || ("Employee".equalsIgnoreCase(role) && username.isEmpty())) {
            showAlert("Login Error", "Please fill all required fields.", Alert.AlertType.ERROR);
            return;
        }

        try {
            if (authenticate(role, username, password)) {
                showAlert("Login Success", "Welcome!", Alert.AlertType.INFORMATION);
                // Logic for navigating to the respective menu based on role
            } else {
                showAlert("Login Failed", "Invalid credentials or role.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            showAlert("System Error", "An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private boolean authenticate(String role, String username, String password) {
        // Replace with real authentication logic
        if ("Admin".equalsIgnoreCase(role) && "admin123".equals(password)) return true;
        if ("HR".equalsIgnoreCase(role) && "hr123".equals(password)) return true;
        if ("Employee".equalsIgnoreCase(role) && "employee123".equals(password)) return true;
        return false;
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void onRoleSelected() {
        String selectedRole = roleComboBox.getValue();

        if (selectedRole == null || selectedRole.isEmpty()) {
            // Hide both fields if no role is selected
            usernameField.setVisible(false);
            passwordField.setVisible(false);
        } else if ("Employee".equalsIgnoreCase(selectedRole)) {
            // Show both fields for employees
            loginButton.setVisible(true);
            loginButton.setLayoutY(490.0);
            usernameField.setVisible(true);
            usernameField.setLayoutY(410.0); // ID field position
            passwordField.setVisible(true);
            passwordField.setLayoutY(450.0); // Password below ID
        } else {
            // Show only the password field for Admin and HR
            loginButton.setVisible(true);
            loginButton.setLayoutY(450.0);
            usernameField.setVisible(false);
            passwordField.setVisible(true);
            passwordField.setLayoutY(410.0); // Password at ID position
        }
    }


}
