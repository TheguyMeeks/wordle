import java.util.Scanner;

public class Main {

    public static void main(String[] args) { // apparently required. execution always starts here
        Scanner player = new Scanner(System.in); // uses scanner to read input from the terminal.

        String userFilepath;

        // determine difficulty
        System.out.println("please select difficulty, 5,6 or 7?: ");
        String difficulty = player.nextLine();

        switch (difficulty) {
            case "7":
                userFilepath = "some 7 letter word file";
                break;
            case "6":
                userFilepath = "some 6 letter words file";
                break;
            default:
                userFilepath = "words.txt";
        }

        try {
            WordList wordList = new WordList(userFilepath);
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
            System.out.println("Could not find words file. check that the file exists");
        }

    }

}
