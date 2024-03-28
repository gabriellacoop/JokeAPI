package com.example.api;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import javafx.scene.image.Image;


public class Main extends Application {
    public static Scene helloview;
    public static Scene jokeresult;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load hello-view.fxml
            FXMLLoader fxmlLoaderHello = new FXMLLoader();
            URL helloViewUrl = getClass().getResource("/com/example/jokes/hello-view.fxml");
            if (helloViewUrl == null) {
                throw new IOException("FXML file not found: hello-view.fxml");
            }
            Parent fxmlHello = fxmlLoaderHello.load(helloViewUrl.openStream());
            helloview = new Scene(fxmlHello, 400, 300);

            // Load joke-result.fxml
            FXMLLoader fxmlLoaderJoke = new FXMLLoader();
            URL jokeResultUrl = getClass().getResource("/com/example/jokes/joke_result.fxml");
            if (jokeResultUrl == null) throw new IOException("FXML file not found: joke-result.fxml");
            Parent fxmlJoke = fxmlLoaderJoke.load(jokeResultUrl.openStream());
            jokeresult = new Scene(fxmlJoke, 400, 300);

            // Set up primaryStage
            primaryStage.setTitle("Choose a Joke");
            primaryStage.setScene(helloview); // Define the initial scene

            // Adicionar o ícone da aplicação
            boolean add = primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/jokes/images/imagem.png")));


            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeScreen(String src) {
        Stage stage = (Stage) helloview.getWindow();
        switch (src) {
            case "helloview":
                stage.setScene(helloview);
                break;
            case "jokeresult":
            default:
                stage.setScene(jokeresult);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
