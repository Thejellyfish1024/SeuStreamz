package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import bd.edu.seu.seustreamz.models.User;
import bd.edu.seu.seustreamz.services.UserService;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UsersController implements Initializable {
    @FXML
    private TableColumn<User, String> currentPackageColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, Number> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, Number> revenueColumn;

    @FXML
    private TableColumn<User, Number> totalSubscriptionsColumn;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private Button userDetailsButton;


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
    void handleSeeUserDetails() {
        Main.changeScene("user-details");
    }

    ObservableList<User> usersList;
    int count = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDetailsButton.setVisible(false);
        idColumn.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(usersTable.getItems().indexOf(c.getValue()) + 1));
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        emailColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmail()));
        currentPackageColumn.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getCurrentSubscriptionIfExists()));
        totalSubscriptionsColumn.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getTotalSubscriptionsCount()));
        revenueColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getTotalRevenue()));

        usersList = FXCollections.observableArrayList();
        usersList.addAll(users());
        usersTable.setItems(usersList);

        selectedUser();
    }

    List<User> users() {
        UserService userService = new UserService();
        return userService.getUsers();
    }


    void selectedUser(){
        usersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Main.userEmail = newSelection.getEmail();
                userDetailsButton.setVisible(true);
            } else {
                Main.userEmail = null;
                userDetailsButton.setVisible(false);
            }
        });
    };


}
