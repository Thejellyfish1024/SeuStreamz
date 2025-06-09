package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import bd.edu.seu.seustreamz.models.User;
import bd.edu.seu.seustreamz.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDetailsController implements Initializable {
    @FXML
    private Label emailField;

    @FXML
    private ImageView imageField;

    @FXML
    private Label nameField;

    @FXML
    private Label packageField;

    @FXML
    private Label totalSubscriptionField;

    @FXML
    void goToAddMovie(MouseEvent event) {
        Main.changeScene("movie");
    }

    @FXML
    void goToAdminDashboard(MouseEvent event) {
        Main.changeScene("admin-dashboard");
    }

    @FXML
    void goToAdminProfile(MouseEvent event) {
        Main.changeScene("admin-profile");
    }

    @FXML
    void goToMovies(MouseEvent event) {
        Main.changeScene("movies");
    }

    @FXML
    void goToPackages(MouseEvent event) {
        Main.changeScene("packages");
    }

    @FXML
    void goToSubscriptions(MouseEvent event) {
        Main.changeScene("subscriptions");
    }

    @FXML
    void goToUsers(MouseEvent event) {
        Main.changeScene("users");
    }

    @FXML
    void goToWebSeries(MouseEvent event) {
        Main.changeScene("web-series");
    }

    @FXML
    void handleLogOut(MouseEvent event) {
        Main.changeScene("login");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserService userService = new UserService();
        User user = userService.getUserByEmail(Main.userEmail);
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
        packageField.setText(user.getCurrentSubscriptionIfExists());
        totalSubscriptionField.setText(String.valueOf(user.getTotalSubscriptionsCount()));
    }
}
