module com.example.wordlejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wordlejavafx to javafx.fxml;
    exports com.example.wordlejavafx;
}