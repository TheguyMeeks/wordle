module com.example.wordlejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens wordle to javafx.fxml;
    exports wordle;
}