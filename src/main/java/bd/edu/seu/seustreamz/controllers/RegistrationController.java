package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import bd.edu.seu.seustreamz.models.User;
import bd.edu.seu.seustreamz.services.LoginLoginRegistrationService;
import bd.edu.seu.seustreamz.utils.CommonAlert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private Label confirmPasswordError;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label emailError;

    @FXML
    private TextField emailField;

    @FXML
    private Label nameError;

    @FXML
    private TextField nameField;

    @FXML
    private Label passwordError;

    @FXML
    private PasswordField passwordField;

    @FXML
    void handleRegister() {

        clearErrorMessages();

        boolean isValid = true;

        if (nameField.getText().trim().isEmpty()) {
            nameError.setText("Name is required");
            isValid = false;
        }
        if (emailField.getText().trim().isEmpty()) {
            emailError.setText("Email is required");
            isValid = false;
        } else if (!isValidEmail(emailField.getText().trim())) {
            emailError.setText("Invalid email format");
            isValid = false;
        }
        if (passwordField.getText().isEmpty()) {
            passwordError.setText("Password is required");
            isValid = false;
        }
        if (confirmPasswordField.getText().isEmpty()) {
            confirmPasswordError.setText("Please confirm your password");
            isValid = false;
        } else if (!confirmPasswordField.getText().equals(passwordField.getText())) {
            confirmPasswordError.setText("Passwords do not match");
            isValid = false;
        }

        if (isValid) {
            LoginLoginRegistrationService loginRegistrationService = new LoginLoginRegistrationService();
            CommonAlert commonAlert = new CommonAlert();
            if (loginRegistrationService.getUserByEmail(emailField.getText().trim()) != null) {
                commonAlert.errorAlert("User with same email already exists");
            } else {
                String name = nameField.getText().trim();
                String email = emailField.getText().toLowerCase().trim();
                String password = passwordField.getText();
                User newUser = new User(name, email, password);
                loginRegistrationService.insertUser(newUser);
                clearInputFields();
                commonAlert.successAlert("Registration successful!");
            }

        }
    }

    // Checking if email is valid
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Clear error messages
    private void clearErrorMessages() {
        nameError.setText("");
        emailError.setText("");
        passwordError.setText("");
        confirmPasswordError.setText("");
    }

    // Clear input messages
    private void clearInputFields() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public void goToLogin() {
        Main.changeScene("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearErrorMessages();
    }
}
