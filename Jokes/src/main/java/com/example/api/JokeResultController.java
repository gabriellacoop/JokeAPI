package com.example.api;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JokeResultController {

    @FXML
    private Label setupLabel;

    @FXML
    private Label punchlineLabel;

    @FXML
    private Button backButton;

    private Joke joke;

    public void initialize() {
        if (joke != null) {
            setupLabel.setText(joke.getSetup());
            punchlineLabel.setText(joke.getPunchline());
        }
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
        initialize(); // call initialize
    }

    @FXML
    private void handleCloseButtonClick() {
        try {
            // Coloque o código para fechar a janela aqui
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            // Imprima informações sobre a exceção
            e.printStackTrace();
        }
    }
}
