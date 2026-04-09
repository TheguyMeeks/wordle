package wordle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class WordleAppController {

    /**
     * NORMAL FIELDS
     */
    private int boxSize = 65;
    public Label[][] labels;
    private int letterCounter = 0;
    private int letterRow = 0;
    private String rowGuess = "";

    public Game game;
    public WordList wordList;
    private String wordOfTheDay;
    private String gameFilepath;
    private String gameDifficulty;

    /**
     * FXML FIELDS
     */
    @FXML private GridPane wordsGrid;
    @FXML private Label statusLeft;


    // ===================
    // FXML METHODS RAHHH
    // ===================
    @FXML
    public void initialize() {

    }

    @FXML
    void menuQuit(ActionEvent event) {
        Platform.exit();
    }


    // ===================
    // NON-FXML METHODS RAHHH!
    // ===================


    private void drawGame(int wordLength) {
        labels = new Label[6][wordLength]; // that five should be changed with the GETTER
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                StackPane stackPane = new StackPane();
                Label textBox = new Label();
                textBox.setStyle("-fx-border-color: #d1d4d8; -fx-alignment: center; -fx-border-width: 2; -fx-font-size: 30; -fx-font-family: 'Arial Rounded MT Bold'");
                labels[row][col] = textBox;

                stackPane.getChildren().add(textBox);
                stackPane.setPrefSize(boxSize, boxSize);
                textBox.setPrefSize(boxSize, boxSize);

                wordsGrid.add(stackPane, col, row);
            }
        }
    }

    public void playGame(String difficulty) {
//        this.gameDifficulty = difficulty;
        switch (difficulty) {
            case "7" :
                gameFilepath = "words.txt"; // SUBJECT TO CHANGE
                break;
            case "6" :
                gameFilepath = "words.txt"; // SUBJECT TO CHANGE
                break;
            default:
                gameFilepath = "words.txt";
        }

        try {
            wordList = new WordList(gameFilepath);
            wordOfTheDay = wordList.getRandomWord();
            game = new Game(wordOfTheDay);

            drawGame(game.getSecretWordLength());
            

        } catch (Exception e) {
            statusLeft.setText("Could not find words file. check that the file exists");
        }

    }

    public void handleKeyTyped(KeyEvent keyEvent) {
        System.out.println("The word is: " + wordOfTheDay); // for debugging purposes
        KeyCode key = keyEvent.getCode();


        if (keyEvent.getCode().isLetterKey()) {
            rowGuess += keyEvent.getText();
            labels[letterRow][letterCounter].setText(keyEvent.getText().toUpperCase());
            letterCounter++;

        }

        // replace that five with the getter from game later
        if (key == KeyCode.ENTER && rowGuess.length() == game.getSecretWordLength()) {
            if (!wordList.isValidWord(rowGuess)) {
                System.out.println(rowGuess);
                System.out.println("Not a valid word!");
            } else {
                LetterResult[] feedback = game.submitGuess(rowGuess);
                displayEvaluation(feedback);

                // only here to help with debugging rn
                if (game.isWon()) {
                    System.out.println("That was the word! You win!");
                    statusLeft.setText("That was the word! You win!");
                } else {
                    if (game.isOver()) {
                        System.out.println("You lost! The word was: " + wordOfTheDay);
                    }
                }

                letterRow++; // move to the next row
                letterCounter = 0; // reset the column marker
                rowGuess = ""; // reset the row guess
            }
        } else {
            statusLeft.setText("Not enough letters");
        }

        if (key == KeyCode.BACK_SPACE) {
            if (!rowGuess.isEmpty()) {
                rowGuess = rowGuess.substring(0, rowGuess.length() - 1);
                labels[letterRow][letterCounter - 1].setText("");
                letterCounter--;
            }
        }


    }

    private void displayEvaluation(LetterResult[] results) {
        int letter = 0;
        for (LetterResult result : results) {
            switch (result) {
                case CORRECT -> labels[letterRow][letter].setStyle("-fx-background-color: #69a963; " +
                        "-fx-border-color: #69a963; -fx-alignment: center; -fx-border-width: 2; " +
                        "-fx-font-size: 30; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'"
                );
                case PRESENT -> labels[letterRow][letter].setStyle("-fx-background-color: #c7b357;" +
                        " -fx-border-color: #c7b357; -fx-alignment: center; -fx-border-width: 2;" +
                        "-fx-font-size: 30; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'"
                );
                case ABSENT -> labels[letterRow][letter].setStyle("-fx-background-color: #777b7d; " +
                        "-fx-border-color: #777b7d; -fx-alignment: center; -fx-border-width: 2; " +
                        "-fx-font-size: 30; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'"
                );
            }
            letter++;
        }
    }


}
