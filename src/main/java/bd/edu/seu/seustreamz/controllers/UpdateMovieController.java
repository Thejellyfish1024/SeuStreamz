package bd.edu.seu.seustreamz.controllers;

import bd.edu.seu.seustreamz.Main;
import bd.edu.seu.seustreamz.models.Movie;
import bd.edu.seu.seustreamz.services.MovieService;
import bd.edu.seu.seustreamz.utils.CommonAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
        import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdateMovieController implements Initializable {
    @FXML
    private Label descriptionError;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ComboBox<String> genreComboBox;

    @FXML
    private Label genreChoiceBoxError;

    @FXML
    private ImageView thumbnailField;

    @FXML
    private Label titleError;

    @FXML
    private TextField titleField;

    @FXML
    private Label videoError;

    @FXML
    private Label thumbnailError;

    @FXML
    private MediaView videoField;


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
    void handleUpdate() {
        clearErrors();

        String title = titleField.getText();
        String description = descriptionField.getText();
        String genre = genreComboBox.getValue();
        String thumbnailLink = null;
        if (thumbnailField.getImage() != null) {
            thumbnailLink = thumbnailField.getImage().getUrl();
        }

        String videoLink = null;
        if (videoField.getMediaPlayer() != null && videoField.getMediaPlayer().getMedia() != null) {
            videoLink = videoField.getMediaPlayer().getMedia().getSource();
        }

        boolean isValid = true;
        if (title.isEmpty()) {
            titleError.setText("Title is required");
            isValid = false;
        }
        if (description.isEmpty()) {
            descriptionError.setText("Description is required");
            isValid = false;
        }
        if (genre == null) {
            genreChoiceBoxError.setText("Genre is required");
            isValid = false;
        }
        if (thumbnailLink == null) {
            thumbnailError.setText("Thumbnail is required");
            isValid = false;
        }
        if (videoLink == null) {
            videoError.setText("Video is required");
            isValid = false;
        }

        if (isValid) {
            CommonAlert commonAlert = new CommonAlert();
            MovieService movieService = new MovieService();
            Movie newMovie = new Movie(title, description, genre, videoLink, thumbnailLink);
            movieService.updateMovie(Main.selectedMovie, newMovie);
            commonAlert.successAlert("Movie updated successfully!");
        }

    }

    @FXML
    void handleThumbnailUpload(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        File selectedFile = fileChooser.showOpenDialog(titleField.getScene().getWindow());

        if (selectedFile != null) {
            try {
                thumbnailField.setVisible(true);
                File destDir = new File("assets/images");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
                String originalName = selectedFile.getName();
                String extension = originalName.substring(originalName.lastIndexOf("."));

                // Unique name using UUID
                String uniqueName = UUID.randomUUID().toString() + extension;
                File destFile = new File(destDir, uniqueName);

                // Copy file
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Set image to preview
                Image image = new Image(destFile.toURI().toString());
                thumbnailField.setImage(image);
                thumbnailField.setPreserveRatio(false);
                thumbnailField.setFitWidth(thumbnailField.getFitWidth());
                thumbnailField.setFitHeight(thumbnailField.getFitHeight());
                thumbnailError.setText("");

            } catch (IOException e) {
                thumbnailError.setText("Failed to upload image.");
                e.printStackTrace();
            }
        } else {
            thumbnailError.setText("No image selected.");
        }
    }


    @FXML
    void handleVideoUpload(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Video");

        FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter(
                "Video Files", "*.mp4", "*.mov", "*.avi", "*.mkv");
        fileChooser.getExtensionFilters().add(videoFilter);

        File selectedFile = fileChooser.showOpenDialog(videoField.getScene().getWindow());

        if (selectedFile != null) {
            try {
                videoField.setVisible(true);

                // Create destination folder if not exists
                File destDir = new File("assets/videos");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }

                // Generate a unique filename
                String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
                File destFile = new File(destDir, uniqueFileName);

                // Copy video
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Load and play video
                Media media = new Media(destFile.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                videoField.setMediaPlayer(mediaPlayer);

                // Auto resize the MediaView
                videoField.setPreserveRatio(false);
                videoField.setFitWidth(videoField.getFitWidth());
                videoField.setFitHeight(videoField.getFitHeight());
                videoError.setText("");

                mediaPlayer.setAutoPlay(true);

            } catch (IOException e) {
                System.out.println("Failed to upload video.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error playing video.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No video selected.");
        }
    }


    void clearErrors() {
        descriptionError.setText("");
        titleError.setText("");
        genreChoiceBoxError.setText("");
        thumbnailError.setText("");
        videoError.setText("");
    }

    void clearInputs() {
        descriptionField.setText("");
        titleField.setText("");
        thumbnailField.setImage(null);
        videoField.setMediaPlayer(null);
    }

    void setSelectedMovieDetails(){
        MovieService movieService = new MovieService();
        Movie movie = movieService.getMovieById(Main.selectedMovie);

        // title, description, genre
        titleField.setText(movie.getTitle());
        descriptionField.setText(movie.getDescription());
        genreComboBox.setValue(movie.getGenre());

        // thumbnail
        String thumbnailPath = movie.getThumbnailLink();
        Image image = new Image(thumbnailPath);
        thumbnailField.setImage(image);
        thumbnailField.setPreserveRatio(false);
        thumbnailField.setFitWidth(thumbnailField.getFitWidth());
        thumbnailField.setFitHeight(thumbnailField.getFitHeight());

        // video
        Media media = new Media(movie.getVideoLink());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        videoField.setMediaPlayer(mediaPlayer);
        videoField.setPreserveRatio(false);
        videoField.setFitWidth(videoField.getFitWidth());
        videoField.setFitHeight(videoField.getFitHeight());
        mediaPlayer.setAutoPlay(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearErrors();
        clearInputs();

        // Genre Type
        ObservableList<String> genreTypeOptions = FXCollections.observableArrayList();
        genreTypeOptions.add("Action");
        genreTypeOptions.add("Adventure");
        genreTypeOptions.add("Comedy");
        genreTypeOptions.add("Drama");
        genreTypeOptions.add("Horror");
        genreTypeOptions.add("Thriller");
        genreComboBox.setItems(genreTypeOptions);

        setSelectedMovieDetails();
    }
}
