package wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class WordleAppController {

    /**
     * NORMAL FIELDS
     */
    private int boxSize = 65;

    /**
     * FXML FIELDS
     */
    @FXML private GridPane wordsGrid;

    // ===================
    // FXML METHODS RAHHH
    // ===================
    @FXML
    public void initialize() {
        drawGame();
    }


    private void drawGame() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                StackPane stackPane = new StackPane();
                Label textBox = new Label();
                textBox.setStyle("-fx-border-color: #d1d4d8; -fx-alignment: center; -fx-border-width: 2");

                stackPane.getChildren().add(textBox);
                stackPane.setPrefSize(boxSize, boxSize);
                textBox.setPrefSize(boxSize, boxSize);

                wordsGrid.add(stackPane, col, row);
            }
        }
    }


}
