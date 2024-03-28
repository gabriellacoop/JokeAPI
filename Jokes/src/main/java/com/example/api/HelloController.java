package com.example.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HelloController {

    @FXML
    private ListView<Joke> listView;

    @FXML
    private TextField searchField;

    private ObservableList<Joke> jokes;

    public void initialize() {
        jokes = fetchJokes();
        listView.setItems(jokes);

        listView.setOnMouseClicked(this::handleListItemClicked);
    }

    @FXML
    private void handleListItemClicked(MouseEvent event) {
        Joke selectedJoke = listView.getSelectionModel().getSelectedItem();
        if (selectedJoke != null) {
            displayJokeDetail(selectedJoke);
        }
    }

    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText().trim().toLowerCase();
        if (!searchTerm.isEmpty()) {
            ObservableList<Joke> filteredJokes = FXCollections.observableArrayList();
            for (Joke joke : jokes) {
                if (joke.getSetup().toLowerCase().contains(searchTerm) || joke.getPunchline().toLowerCase().contains(searchTerm)) {
                    filteredJokes.add(joke);
                }
            }
            listView.setItems(filteredJokes);
        } else {
            // if is empty you can search again
            listView.setItems(fetchJokes()); // Update the List
        }
    }

    private ObservableList<Joke> fetchJokes() {
        ObservableList<Joke> jokesList = FXCollections.observableArrayList();
        try {
            URL url = new URL("https://official-joke-api.appspot.com/jokes/ten");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();
            Gson gson = new Gson();
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String setup = jsonObject.get("setup").getAsString();
                String punchline = jsonObject.get("punchline").getAsString();
                String id = jsonObject.get("id").getAsString(); // Supondo que a API retorne uma ID para cada piada
                jokesList.add(new Joke(setup, punchline, id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jokesList;
    }

    private void displayJokeDetail(Joke joke) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/jokes/joke_result.fxml"));
            AnchorPane root = loader.load();

            // Send it to the second scene
            JokeResultController controller = loader.getController();
            controller.setJoke(joke);

            // show the second scene
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Joke Detail");
            popupStage.setScene(new Scene(root, 400, 300));
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
