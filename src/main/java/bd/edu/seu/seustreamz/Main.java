package bd.edu.seu.seustreamz;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280 ,720);
        stage.setTitle("Seu Streamz");
        stage.setScene(scene);
        stage.show();

        Platform.runLater(() -> {
            // Request focus on the root node to avoid TextField autofocus
            scene.getRoot().requestFocus();
        });
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene (String fxml) {
       try{
           FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
           Scene scene = new Scene(fxmlLoader.load(), 1280 ,720);
           stage.setTitle("Seu Streamz");
           stage.setScene(scene);

           Platform.runLater(() -> {
               scene.getRoot().requestFocus();
           });
       } catch(IOException e){
           e.printStackTrace();
       }
    }


}