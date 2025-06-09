package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import bd.edu.seu.seustreamz.services.UserService;
import bd.edu.seu.seustreamz.utils.CommonAlert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private Label emailError;

    @FXML
    private Label passwordError;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void goToRegister() {
        Main.changeScene("registration");
    }

    // login method
    @FXML
    void handleLogin() {
        clearErrorMessages();

        boolean isValid = true;

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

        if (isValid) {
            String email = emailField.getText().toLowerCase().trim();
            String password = passwordField.getText();
            UserService userService = new UserService();
            CommonAlert commonAlert = new CommonAlert();
            if (userService.getUserByEmail(email) == null) {
                commonAlert.errorAlert("No account with this email exists!");
            } else if (!(userService.getUserByEmail(email).getPassword().equals(password))) {
                commonAlert.errorAlert("Credentials do not match!");
            } else if (userService.getUserByEmail(email).getPassword().equals(password)) {
                clearInputFields();
                System.out.println("Login successful!");
                goToHome();
            }

        }
    }


    // Clear error messages
    private void clearErrorMessages() {
        emailError.setText("");
        passwordError.setText("");
    }

    // Clear input messages
    private void clearInputFields() {
        emailField.setText("");
        passwordField.setText("");
    }

    // Checking if email is valid
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearErrorMessages();
    }

    public void goToAdminLogin() {
        Main.changeScene("admin-login");
    }

    public void goToHome() {
        Main.changeScene("home");
    }
}
