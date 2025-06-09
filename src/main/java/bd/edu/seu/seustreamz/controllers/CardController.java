package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.models.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CardController {

    @FXML
    private Label authorField;

    @FXML
    private HBox box;

    @FXML
    private ImageView imageField;

    @FXML
    private Label nameField;

    public void setData(Movie movie){
        Image image = new Image(getClass().getResourceAsStream(movie.getThumbnailLink()));
        imageField.setImage(image);
        nameField.setText(movie.getTitle());
        authorField.setText(movie.getDescription());
    }
}

