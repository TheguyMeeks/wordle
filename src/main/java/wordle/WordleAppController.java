package wordle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;

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
    @FXML private BorderPane root;
    @FXML private GridPane wordsGrid;
    @FXML private Label statusLeft;
    @FXML public HBox keyboardRowOne;
    @FXML public HBox keyboardRowTwo;
    @FXML public HBox keyboardRowThree;


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

        String keysInRowOne = "QWERTYUIOP";
        String keysInRowTwo = "ASDFGHJKL";
        String[] keysInRowThree = {"ENTER", "Z", "X", "C", "V", "B", "N", "M", "DELETE",};

        char[] lettersRowOne = keysInRowOne.toCharArray();
        char[] lettersRowTwo = keysInRowTwo.toCharArray();

        for (char c : lettersRowOne) {
            Button key = new Button();
            String str = String.valueOf(c);
            key.setText(str);
            key.setOnAction(this::handleKeyboardClicked);
            key.setStyle("-fx-background-color: #c6c9cc; -fx-pref-width: 45; -fx-pref-height: 57; -fx-text-fill: black; -fx-font-size: 17; -fx-font-weight: 700;");

            keyboardRowOne.getChildren().add(key);
        }
        for (char c : lettersRowTwo) {
            Button key = new Button();
            String str = String.valueOf(c);
            key.setText(str);
            key.setOnAction(this::handleKeyboardClicked);
            key.setStyle("-fx-background-color: #c6c9cc; -fx-pref-width: 45; -fx-pref-height: 57; -fx-text-fill: black; -fx-font-scale: 100; -fx-font-size: 17; -fx-font-weight: 700;");

            keyboardRowTwo.getChildren().add(key);
        }
        for (String s : keysInRowThree) {
            Button key = new Button();
            String str = String.valueOf(s);
            key.setText(str);
            key.setOnAction(this::handleKeyboardClicked);
            if (!(s.equals(keysInRowThree[0]) || s.equals(keysInRowThree[keysInRowThree.length - 1]))) {
                key.setStyle("-fx-background-color: #c6c9cc; -fx-pref-width: 45; -fx-pref-height: 57; -fx-text-fill: black; -fx-font-size: 17; -fx-font-weight: 700;");
            } else {
                key.setStyle("-fx-background-color: #c6c9cc; -fx-pref-width: 65; -fx-pref-height: 57; -fx-text-fill: black; -fx-font-size: 13; -fx-font-weight: 700;");
            }

            keyboardRowThree.getChildren().add(key);
        }
    }

    public void playGame(String difficulty) {
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

        Platform.runLater(() -> root.requestFocus());
    }

    public void handleKeyTyped(KeyEvent keyEvent) {
        System.out.println("The word is: " + wordOfTheDay); // for debugging purposes
        KeyCode key = keyEvent.getCode();

        if (key == KeyCode.BACK_SPACE) {
            if (!rowGuess.isEmpty()) {
                rowGuess = rowGuess.substring(0, rowGuess.length() - 1);
                labels[letterRow][letterCounter - 1].setText("");
                letterCounter--;
            }
            return;
        }

        if (keyEvent.getCode().isLetterKey()) {
            if (letterCounter < game.getSecretWordLength()) {
                rowGuess += keyEvent.getText();
                labels[letterRow][letterCounter].setText(keyEvent.getText().toUpperCase());
                letterCounter++;
            }
            return;
        }


        if (key == KeyCode.ENTER) {
            if (rowGuess.length() < game.getSecretWordLength()) {
                statusLeft.setText("Not enough letters");
                return;
            }
            if (!wordList.isValidWord(rowGuess)) {
                System.out.println("your guess was " + rowGuess + " That's not a valid word");
                statusLeft.setText("Not a valid word!");
                return;
            }

            LetterResult[] feedback = game.submitGuess(rowGuess);
            displayEvaluation(feedback);

            if (game.isWon()) {
                statusLeft.setText("That was the word! You win!");
            } else if (game.isOver()) {
                System.out.println("You lost! The word was: " + wordOfTheDay);
                statusLeft.setText("You lost! The word was: " + wordOfTheDay);
            }

            letterRow++;
            letterCounter = 0;
            rowGuess = "";
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

    @FXML
    public void handleKeyboardClicked(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        String letterClicked = clicked.getText();
        System.out.println(letterClicked);

        if (letterClicked.equals("DELETE")) {
            if (!rowGuess.isEmpty()) {
                rowGuess = rowGuess.substring(0, rowGuess.length() - 1);
                labels[letterRow][letterCounter - 1].setText("");
                letterCounter--;
            }
            event.consume();
            return;
        }

        if (letterClicked.equals("ENTER")) {
            if (letterCounter < game.getSecretWordLength()) {
                statusLeft.setText("Not enough letters");
                event.consume();
                return;
            }
            if (!wordList.isValidWord(rowGuess)) {
                System.out.println("your guess was " + rowGuess + " That's not a valid word");
                statusLeft.setText("Not a valid word!");
                event.consume();
                return;
            }

            // now that we're past the cases where the word is too short or not a valid word, we can safely evaluate it
            LetterResult[] feedback = game.submitGuess(rowGuess);
            displayEvaluation(feedback);

            if (game.isWon()) {
                statusLeft.setText("That was the word! You win!");
                System.out.println("That was the word! You win!");
            } else if (game.isOver()) {
                statusLeft.setText("You lost! The word was: " + wordOfTheDay);
            }

            letterRow++;
            letterCounter = 0;
            rowGuess = "";
            event.consume();
            return;
        }

        // if it's not enter or delete it's a letter
        if (letterCounter < game.getSecretWordLength()) {
            rowGuess += letterClicked;
            labels[letterRow][letterCounter].setText(letterClicked);
            letterCounter++;
        }

        System.out.println(event.toString());
        event.consume();
        root.requestFocus();
    }
}
