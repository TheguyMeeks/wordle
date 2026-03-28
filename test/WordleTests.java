import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordleTests {
    @Test
    void evaluateGuessCorrect() {
        // make the guess the exact same with the secret word
        Game game = new Game("Spike");
        LetterResult[] result = game.submitGuess("Spike"); // runs evaluateGuess through submitGuess first, which first deals with capitalization

        LetterResult[] expected = {LetterResult.CORRECT, LetterResult.CORRECT, LetterResult.CORRECT, LetterResult.CORRECT, LetterResult.CORRECT};

        assertArrayEquals(result,expected);
    }
    @Test
    void evaluateGuessAbsent() {
        // make the guess completely diff from the secret word
        Game game = new Game("Spike");
        LetterResult[] result = game.submitGuess("Flood"); // runs evaluateGuess through submitGuess first, which first deals with capitalization

        LetterResult[] expected = {LetterResult.ABSENT, LetterResult.ABSENT, LetterResult.ABSENT, LetterResult.ABSENT, LetterResult.ABSENT};

        assertArrayEquals(result,expected);
    }

    @Test
    void evaluateGuessPresent() {
        // make the guess completely diff from the secret word
        Game game = new Game("Spike");
        LetterResult[] result = game.submitGuess("Spoil"); // runs evaluateGuess through submitGuess first, which first deals with capitalization

        LetterResult[] expected = {LetterResult.CORRECT, LetterResult.CORRECT, LetterResult.ABSENT, LetterResult.PRESENT, LetterResult.ABSENT};

        assertArrayEquals(result,expected);
    }


}
