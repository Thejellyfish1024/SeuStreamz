package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import bd.edu.seu.seustreamz.models.Movie;
import bd.edu.seu.seustreamz.models.User;
import bd.edu.seu.seustreamz.services.MovieService;
import bd.edu.seu.seustreamz.services.UserService;
import bd.edu.seu.seustreamz.utils.CommonAlert;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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

public class MoviesController implements Initializable {
    Movie selectedMovie;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Movie, String> descriptionColumn;

    @FXML
    private Button editButton;

    @FXML
    private TableColumn<Movie, String> genreColumn;

    @FXML
    private TableColumn<Movie, Number> idColumn;

    @FXML
    private TableColumn<Movie, Number> likesColumn;

    @FXML
    private TableView<Movie> moviesTable;

    @FXML
    private TableColumn<Movie, String> titleColumn;

    @FXML
    private TableColumn<Movie, Number> viewsColumn;

    ObservableList<Movie> movieList;

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
    void handleDelete() {
        CommonAlert commonAlert = new CommonAlert();
        if(commonAlert.confirmAlert("Are you sure you want to delete this movie?")) {
            MovieService movieService = new MovieService();
            movieService.deleteMovie(Main.selectedMovie);
            movieList.remove(selectedMovie);
            commonAlert.successAlert("Movie deleted successfully!");
        }
    }

    @FXML
    void handleEdit() {
        Main.changeScene("update-movie");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editButton.setVisible(false);
        deleteButton.setVisible(false);

        idColumn.setCellValueFactory(c-> new SimpleObjectProperty<>(c.getValue().getId()));
        titleColumn.setCellValueFactory(c-> new SimpleObjectProperty<>(c.getValue().getTitle()));
        descriptionColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescription()));
        genreColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGenre()));
        viewsColumn.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getViewsCount()));
        likesColumn.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getLikesCount()));

        movieList = FXCollections.observableArrayList();
        movieList.addAll(movies());
        moviesTable.setItems(movieList);
        selectedUser();

    }

    List<Movie> movies() {
        MovieService movieService = new MovieService();
        return movieService.getAllMovies();
    }

    void selectedUser(){
        moviesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedMovie = newSelection;
                Main.selectedMovie = newSelection.getId();
                editButton.setVisible(true);
                deleteButton.setVisible(true);
            } else {
                Main.selectedMovie = -1;
                editButton.setVisible(false);
                deleteButton.setVisible(false);
            }
        });
    };
}
