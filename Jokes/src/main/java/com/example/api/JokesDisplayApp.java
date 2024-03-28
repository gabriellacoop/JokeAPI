package com.example.api;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JokesDisplayApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // You can initialize your application here if needed
    }

    private void displayJokeDetail(Joke joke) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/api/hello-view.fxml"));
            AnchorPane root = loader.load();

            // Pass the joke to the controller of the second scene
            JokeResultController controller = loader.getController();
            controller.setJoke(joke);

            // Display the second scene in a new window
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Joke Detail");
            popupStage.setScene(new Scene(root, 400, 300));
            popupStage.showAndWait();

            // Switch to the joke detail scene after closing the detail window
            Main.changeScreen("jokeresult");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
