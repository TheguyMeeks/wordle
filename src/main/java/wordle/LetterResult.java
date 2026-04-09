package wordle;

public enum LetterResult {
    CORRECT,  // right letter, right place (green)
    PRESENT, // right letter wrong place
    ABSENT; // letter not in the word at all (gray)


}
