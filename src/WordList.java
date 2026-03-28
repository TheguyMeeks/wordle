import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;



public class WordList {
    private List<String> wordBank; // private so nothing touches it outside the class

    public WordList(String filepath) { // constructor takes a file path so it knows where to load words from.
        this.wordBank =  new ArrayList<>(); // initializes this objs list as an empty ArrayList, to be filled when we load words
        loadWords(filepath); // call load words to fill our words
    }

    private void loadWords(String filepath) {
        File file = new File(filepath);
        try {
            Scanner reader = new Scanner(file);

            // read through the file and add words to the word bank
            while (reader.hasNextLine()) {
                String currentLine = reader.nextLine();
                String[] parts = currentLine.split(",");

                // loop to cleanly move the contents of parts into this.wordBank
                for (String word : parts) {
                    word = word.trim().toLowerCase();
                    this.wordBank.add(word);
                }
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getRandomWord() {
        Random randomizer = new Random();
        int index = randomizer.nextInt(wordBank.size()); // picks a random num between 0 and the size of the wordBank list. and stores it in index
        return wordBank.get(index); // returns the word at that index in wordBank
    }

    // check that the players word was actually a word and not a pile of rubbish
    public boolean isValidWord(String guess) {
        String formattedGuess = guess.toLowerCase();
        return wordBank.contains(formattedGuess);
    }

}
