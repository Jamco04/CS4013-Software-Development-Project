package com.mycompany.payrollsystem.system.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // com.mycompany.payrollsystem.system.javafx.Login Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Fields
        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label roleLabel = new Label("Role:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "HR", "Employee");

        // Buttons
        Button loginButton = new Button("com.mycompany.payrollsystem.system.javafx.Login");
        Button exitButton = new Button("Exit");

        // Layout
        grid.add(idLabel, 0, 0);
        grid.add(idField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(roleLabel, 0, 2);
        grid.add(roleComboBox, 1, 2);
        grid.add(loginButton, 0, 3);
        grid.add(exitButton, 1, 3);

        // Button Actions
        loginButton.setOnAction(e -> {
            String id = idField.getText();
            String password = passwordField.getText();
            String role = roleComboBox.getValue();

            // TODO: Validate login credentials and redirect to the appropriate dashboard
            System.out.println("ID: " + id + ", Password: " + password + ", Role: " + role);
        });

        exitButton.setOnAction(e -> primaryStage.close());

        // Scene and Stage
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setTitle("Payroll System - com.mycompany.payrollsystem.system.javafx.Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
