import java.util.Scanner;

public class Main {

    public static void main(String[] args) { // apparently required. execution always starts here
        Scanner player = new Scanner(System.in); // uses scanner to read input from the terminal.

        try {
            WordList wordList = new WordList("words.txt");
            String wordOfTheDay = wordList.getRandomWord();
            Game game = new Game(wordOfTheDay);

            // loops until game over
            while(!game.isOver()){
                System.out.print("Enter guess: "); // prompts response on the same line
                String guess = player.nextLine().toLowerCase();

                if (!wordList.isValidWord(guess)) {
                    System.out.println("Not a valid word!");
                    continue;
                }

                LetterResult[] feedback = game.submitGuess(guess);
                // display the feedback somehow? display();
            }
            if (game.isWon()) {
                System.out.println("That was the word! You win!");
                // turn display() should also turn all the words of the last guess green
            } else {
                System.out.println("You lost! The word was: " + wordOfTheDay);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
