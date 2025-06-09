package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import bd.edu.seu.seustreamz.models.Packages;
import bd.edu.seu.seustreamz.services.PackageService;
import bd.edu.seu.seustreamz.utils.CommonAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PackagesController {
    @FXML
    private TextField basicDuration;

    @FXML
    private Label basicDurationError;

    @FXML
    private TextField basicPrice;

    @FXML
    private Label basicPriceError;

    @FXML
    private TextField premiumDuration;

    @FXML
    private Label premiumDurationError;

    @FXML
    private TextField premiumPrice;

    @FXML
    private Label premiumPriceError;

    @FXML
    private TextField standardDuration;

    @FXML
    private Label standardDurationError;

    @FXML
    private TextField standardPrice;

    @FXML
    private Label standardPriceError;

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

    @FXML
    void handleBasicUpdate() {
        basicPriceError.setText("");
        basicDurationError.setText("");

        String price = basicPrice.getText();
        String duration = basicDuration.getText();

        boolean isValid = true;
        double priceDouble = 0;
        int durationInt = 0;

        // Validate Price
        if (price.isEmpty()) {
            basicPriceError.setText("Price is required");
            isValid = false;
        } else {
            try {
                priceDouble = Double.parseDouble(price);
                if (priceDouble < 0) {
                    basicPriceError.setText("Price must be positive");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                basicPriceError.setText("Price must be a number");
                isValid = false;
            }
        }

        // Validate Duration
        if (duration.isEmpty()) {
            basicDurationError.setText("Duration is required");
            isValid = false;
        } else {
            try {
                durationInt = Integer.parseInt(duration);
                if (durationInt <= 0) {
                    basicDurationError.setText("Duration must be positive");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                basicDurationError.setText("Duration must be a number");
                isValid = false;
            }
        }

        if (isValid) {
            PackageService packageService = new PackageService();
            Packages newPackage = new Packages("Basic", priceDouble, durationInt);
            packageService.insertPackage(newPackage);
            CommonAlert commonAlert = new CommonAlert();
            commonAlert.successAlert("Package updated successfully!");
        }
    }


    @FXML
    void handlePremiumUpdate() {

    }

    @FXML
    void handleStandardUpdate() {

    }
}
