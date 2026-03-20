import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class WordList {
    private List<String> words; // private so nothing touches it outside the class

    public WordList(String filepath) { // constructor takes a file path so it knows where to load words from.
        this.words =  new ArrayList<>(); // initializes this objs list as empty, to be filled when we load words
        // need a loadWords() method that takes in filepath, better to have a separate method for this; a function should do one specific thing
    }

    private void loadWords(String filepath) {
        // TODO
        //reads file and fills this.words
    }

    public String getRandomWord() {
        // TODO
        // get random word from this.words
        return null;
    }

    public boolean isValidWord(String guess) {
        String formattedGuess = guess.toLowerCase();
        return words.contains(formattedGuess);
    }

}
