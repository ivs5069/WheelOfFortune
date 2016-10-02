package wheeloffortune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author ivs5069
 */
public class PuzzleBoard {

    //Array to hold the possible puzzles the user can choose to guess
    //This may be changed to a hashmap later on to show the catagories
    private String[] puzzles = {"THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG", "PENN STATE ABINGTON", "INFORMATION SCIENCE AND TECHNOLOGY"};

    //Variable to hold the word in the clear
    private ArrayList<String> puzzleSelected = new ArrayList();

    //Variable to hold the hidden letters
    private ArrayList<String> hiddenLetters = new ArrayList();

    //Initialize an object from the random class
    private Random ran = new Random();

    //Flag on the toggle puzzle reveal
    private boolean puzzleRevealFlag = false;

    //Flag on if the user won
    private boolean winFlag = false;

    public PuzzleBoard() {

        String tempPuzzleHolder = puzzles[ran.nextInt(puzzles.length)];
        for (int i = 0; i < tempPuzzleHolder.length(); i++) {
            puzzleSelected.add(String.valueOf(tempPuzzleHolder.charAt(i)));
            puzzleSelected.add(" ");
        }

        for (int i = 0; i < puzzleSelected.size(); i++) {
            if (Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z").contains(puzzleSelected.get(i))) {
                hiddenLetters.add("_");
            } else {
                hiddenLetters.add(" ");
            }
        }

    }

    /**
     * Method to return length of the puzzle
     */
    public int getPuzzleLength() {
        return puzzleSelected.size();
    }

    /**
     * Method to get the win flag
     *
     * @return winflag
     */
    public boolean getWinFlag() {
        return winFlag;
    }

    /**
     * Method to see if a letter is contained within the puzzle
     */
    public boolean checkLetterContained(String letter) {
        boolean containFlag = false;

        for (int i = 0; i < puzzleSelected.size(); i++) {
            //If the letter is contained in the puzzle string, set the flag to true
            if (Arrays.asList(letter).contains(puzzleSelected.get(i))) {
                containFlag = true;
            }
        }
        return containFlag;
    }

    /**
     * Method to display the puzzle board
     */
    public void getPuzzleBoard() {

        //If puzzlereveal flag is false don't reveal the answer
        if (puzzleRevealFlag == false) {
            //Create a for loop to go through each index of the hidden letters arraylist
            for (int i = 0; i < hiddenLetters.size(); i++) {
                //Print out the letter at the index
                System.out.print(hiddenLetters.get(i));
            }
            //Print statement to make sure there's a white space after the puzzle
            System.out.println(" ");
        } //If puzzle reveal flag is true, reveal the puzzle to the user
        else if (puzzleRevealFlag == true) {
            for (int i = 0; i < puzzleSelected.size(); i++) {
                //Print out the revealed letter at the index
                System.out.print(puzzleSelected.get(i));
            }
            //Print statement to make sure there's a white space after the puzzle
            System.out.println(" ");
        }
    }

    /**
     * Method to check if the user guessed the write letter
     *
     * @param letter returns how many times the user guessed the letter
     */
    public int guessLetter(String letter) {
        //Variable to hold how many times the user guessed the letter
        int timesGuessed = 0;
        //Loop through the puzzle selected arraylist
        for (int i = 0; i < puzzleSelected.size(); i++) {
            //If letter is in the index of puzzle selected, set the letter in the hidden puzzle to the correct guessed letter
            if (Arrays.asList(letter).contains(puzzleSelected.get(i))) {
                hiddenLetters.set(i, letter);
                timesGuessed++;
            }
        }
        
        //Check if the user guessed all of the letters
        checkWinStatus();
        
        return timesGuessed;
    }

    /**
     * Method to check whether a letter was guessed yet or not
     *
     * @param letter
     * @return boolean. True if already guessed False if not guessed
     */
    public boolean checkGuessed(String letter) {
        //Boolean to hold whether the letter was guessed yet or not
        boolean guessedFlag = false;
        //Loop through the masked puzzle
        for (int i = 0; i < hiddenLetters.size(); i++) {
            //If the letter is contained in the masked string, set the flag to true
            if (Arrays.asList(letter).contains(hiddenLetters.get(i))) {
                guessedFlag = true;
            }

            //If the letter is not contained at all in the masked string, boolean remains false such as in initialization
        }

        return guessedFlag;
    }

    /**
     * Method to see if the index of the puzzle is masked
     *
     * @param index
     * @return true if masked, false if not masked
     */
    public boolean checkMaskLocated(int index) {
        //if the hidden letter is masked return true
        if (hiddenLetters.get(index) == "_") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if the user won
     */
    private void checkWinStatus() {
        //Set winflag to true, and if the puzzle contains any masks make it false
        winFlag = true;
        for (int i = 0; i < hiddenLetters.size(); i++) {

            if (Arrays.asList("_").contains(hiddenLetters.get(i))) {
                winFlag = false;
            }
        }

        //if win flag is true, and show the board,tell the user congradulations you win!
        if (winFlag) {
            getPuzzleBoard();
            System.out.println("Congradulations you win!");
        }
    }

    /**
     * Method to toggle the mask flag
     */
    public void toggleReveal() {
        //if flag is false set it to true
        if (puzzleRevealFlag == false) {
            puzzleRevealFlag = true;
        } //if it's not false, set it to false
        else {
            puzzleRevealFlag = false;
        }
    }

}
