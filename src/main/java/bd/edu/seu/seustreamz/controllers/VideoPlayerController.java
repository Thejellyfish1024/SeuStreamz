package bd.edu.seu.seustreamz.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;

public class VideoPlayerController {
    @FXML private MediaView mediaView;
    @FXML private Slider volumeSlider;
    @FXML private Slider seekSlider;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        String videoPath = new File("src/main/resources/assets/video2.mp4").toURI().toString();
        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnReady(() -> {
            volumeSlider.setValue(mediaPlayer.getVolume() * 100);
            seekSlider.setMax(mediaPlayer.getTotalDuration().toSeconds());
        });

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!seekSlider.isValueChanging()) {
                seekSlider.setValue(newTime.toSeconds());
            }
        });

        seekSlider.valueChangingProperty().addListener((obs, wasChanging, isChanging) -> {
            if (!isChanging) {
                mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
            }
        });

        seekSlider.setOnMouseReleased(e ->
                mediaPlayer.seek(Duration.seconds(seekSlider.getValue()))
        );

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                mediaPlayer.setVolume(newVal.doubleValue() / 100)
        );
    }

    @FXML
    public void handlePlay() {
        mediaPlayer.play();
    }

    @FXML
    public void handlePause() {
        mediaPlayer.pause();
    }

    @FXML
    public void handleStop() {
        mediaPlayer.stop();
    }
}

