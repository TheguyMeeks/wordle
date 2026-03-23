import java.util.ArrayList;
import java.util.List;

public class Game {

    private String secretWord;
    private List<String> guesses;
    private int maxAttempts;

    public Game(String secretWord) {
        this.secretWord = secretWord; // stores the secretWord passed into the parameter in the 'box' for the secret word that this game was patiently waiting to use
        this.guesses = new ArrayList<>(); // empty. will fill as the player plays
        this.maxAttempts = 6;
    }

    // we return an array (of size 5) because we are evaluating each letter in the word and it's position
    public LetterResult[] submitGuess(String guess) {
        guesses.add(guess); // add a guess to the list
        return evaluateGuess(guess); // evaluate said guess
    }

    public LetterResult[] evaluateGuess(String guess) {
        // evaluate the guess that was just submitted
    }

    public boolean isWon() {
        if (guesses.isEmpty()) {
            return false; // if the player has not guessed the game is not over. duh
        }

        int mostRecent = guesses.size() - 1; // index of the most recent guess
        return guesses.get(mostRecent).equals(secretWord);
    }

    public boolean isOver() {
        return isWon() || guesses.size() >= maxAttempts;
    }
}
