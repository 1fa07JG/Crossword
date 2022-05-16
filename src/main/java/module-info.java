module com.example.crossword {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires kernel;
    requires layout;
    requires java.desktop;

    opens com.example.crossword to javafx.fxml;
    exports com.example.crossword;
}