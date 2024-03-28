module com.example.jokes {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.api to javafx.fxml;
    exports com.example.api;
}
