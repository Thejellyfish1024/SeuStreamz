package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.models.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private HBox cardLayout;

    private List<Movie> recentlyAdded = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAddedMovies());

        try {
            for (Movie movie : recentlyAdded) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/bd/edu/seu/seustreamz/card.fxml"));
                HBox cardBox = fxmlLoader.load();
                CardController cardController = fxmlLoader.getController();
                cardController.setData(movie);
                cardLayout.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private List<Movie> recentlyAddedMovies (){
        List<Movie> ls = new ArrayList<>();
        String src = "/assets/ImportedPhoto_1705169482932.jpg";
        Movie movie = new Movie();
        movie.setName("Rich Dad Poor Dad");
        movie.setAuthor("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setImgSrc(src);
        ls.add(movie);

        movie = new Movie();
        movie.setName("JavaFX");
        movie.setAuthor("Rahim");
        movie.setImgSrc(src);
        ls.add(movie);

        movie = new Movie();
        movie.setName("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setAuthor("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setImgSrc(src);
        ls.add(movie);

        movie = new Movie();
        movie.setName("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setAuthor("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setImgSrc(src);
        ls.add(movie);

        movie = new Movie();
        movie.setName("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setAuthor("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setImgSrc(src);
        ls.add(movie);

        movie = new Movie();
        movie.setName("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setAuthor("Rich Dad Poor Dad Rich Dad Poor Dad Rich Dad Poor Dad ");
        movie.setImgSrc(src);
        ls.add(movie);

        return ls;
    }
}
