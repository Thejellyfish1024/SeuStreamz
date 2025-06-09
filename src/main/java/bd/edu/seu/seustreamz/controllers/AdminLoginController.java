package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import bd.edu.seu.seustreamz.utils.CommonAlert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminLoginController implements Initializable {
    private static final String ADMIN = "admin@gmail.com";
    private static final String PASSWORD = "admin1234";
    @FXML
    private Label adminEmailError;

    @FXML
    private TextField adminEmailField;

    @FXML
    private Label adminPasswordError;

    @FXML
    private PasswordField adminPasswordField;

    @FXML
    void handleAdminLogin() {
        clearErrorMessages();

        boolean isValid = true;

        if (adminEmailField.getText().trim().isEmpty()) {
            adminEmailError.setText("Email is required");
            isValid = false;
        } else if (!isValidEmail(adminEmailField.getText().trim())) {
            adminEmailError.setText("Invalid email format");
            isValid = false;
        }
        if (adminPasswordField.getText().isEmpty()) {
            adminPasswordError.setText("Password is required");
            isValid = false;
        }

        if (isValid) {
            CommonAlert commonAlert = new CommonAlert();
            if ((adminEmailField.getText().trim().equals(ADMIN)) && (adminPasswordField.getText().equals(PASSWORD))) {
                clearInputFields();
                goToAdminDashboard();
            } else {
                commonAlert.errorAlert("Invalid admin credentials");
            }

        }
    }

    // Clear error messages
    private void clearErrorMessages() {
        adminEmailError.setText("");
        adminPasswordError.setText("");
    }

    // Clear input messages
    private void clearInputFields() {
        adminEmailField.setText("");
        adminPasswordField.setText("");
    }

    // Checking if email is valid
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearErrorMessages();
    }

    public void goToUserLogin() {
        Main.changeScene("login");
    }

    public void goToAdminDashboard() {
        Main.changeScene("admin-dashboard");
    }
}
