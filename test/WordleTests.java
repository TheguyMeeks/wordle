import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordleTests {
    @Test
    void evaluateGuessCorrect() {
        // make the guess the exact same with the secret word
        Game game = new Game("Spike");
        LetterResult[] result = game.submitGuess("Spike"); // runs evaluateGuess through submitGuess first, which first deals with capitalization

        LetterResult[] expected = {LetterResult.CORRECT,LetterResult.CORRECT,LetterResult.CORRECT,LetterResult.CORRECT,LetterResult.CORRECT};

        assertArrayEquals(result,expected);
    }

}
