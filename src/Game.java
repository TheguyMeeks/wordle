import java.util.ArrayList;
import java.util.List;

public class Game {

    private String secretWord;
    private List<String> guesses;
    private int maxAttempts;

    public Game(String secretWord) {
        this.secretWord = secretWord.toLowerCase(); // stores the secretWord passed into the parameter in the 'box' for the secret word that this game was patiently waiting to use
        this.guesses = new ArrayList<>(); // empty. will fill as the player plays
        this.maxAttempts = 6;
    }

    // we return an array (of size 5) because we are evaluating each letter in the word and it's position
    public LetterResult[] submitGuess(String guess) {
        String formattedGuess = guess.toLowerCase();
        guesses.add(formattedGuess); // add a guess to the list
        return evaluateGuess(formattedGuess); // evaluate said guess
    }

    public LetterResult[] evaluateGuess(String guess) {
        LetterResult[] result = new LetterResult[5]; //array of size 5 because wordle is always five-letter words. should maybe be a variable if this is subject to change

        char[] guessLetters = guess.toCharArray();
        char[] secretLetters = secretWord.toCharArray();

        // start with the first letter of the guessed word with every letter of the secret word. repeat until we run out of guessed-word letters
        for (int i = 0; i < 5; i++) {
            if (guessLetters[i] == secretLetters[i]) {
                result[i] = LetterResult.CORRECT;
                continue;
            }

            boolean matched = false; // we need a way to only label a letter absent if it wasn't found anywhere in the actual word
            for (int j = 0; j < 5; j++) {
                if (guessLetters[i] == secretLetters[j]) {
                    result[i] = LetterResult.PRESENT;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                result[i] = LetterResult.ABSENT;
            }

        }
        return result;
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
