package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PackagesController {
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
}
