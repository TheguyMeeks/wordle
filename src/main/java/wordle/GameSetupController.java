package wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;

public class GameSetupController {

    private String userDifficulty;

    @FXML private Label statusLabel;
    @FXML private ComboBox<String> wordTypeOptions;
    @FXML private HBox wordTypeBox;
    @FXML private Label titleLabel;


    // ===================
    // FXML METHODS RAHHH
    // ===================
    @FXML
    public void initialize() {
        wordTypeOptions.getItems().addAll("5-letter words", "6-letter words", "7-letter words");
    }


    // ====================================================
    // NON-FXML METHODS RAHHH!
    // ====================================================

    public void launchGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(WordleApp.class.getResource("WordleAppView.fxml"));
            Stage gameStage = (Stage) titleLabel.getScene().getWindow(); // get the window
            Scene gameScene = new Scene(loader.load()); // read the fxml file and store the contents in a scene
            gameStage.setScene(gameScene); // set the contents of the screen on the window

            WordleAppController controller = loader.getController();
            controller.playGame(wordTypeOptions.getValue());

            gameScene.setOnKeyPressed(keyEvent -> {
                System.out.print(keyEvent.getText() + " keycode: ");
                System.out.print(keyEvent.getCode() + "\n");
                controller.handleKeyTyped(keyEvent);
                // System.out.println(Arrays.deepToString(controller.labels));
            });


        } catch (Exception e) {
            statusLabel.setText("Error starting game. Please try again");
        }

    }

}
