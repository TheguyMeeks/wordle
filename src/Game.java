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
}
