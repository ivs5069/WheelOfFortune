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
    private String[] puzzles = {"THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG"};

    //Variable to hold the word in the clear
    private ArrayList<String> puzzleSelected = new ArrayList();

    //Variable to hold the hidden letters
    private ArrayList<String> hiddenLetters = new ArrayList();

    //Initialize an object from the random class
    Random ran = new Random();

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

    public void getPuzzleBoard() {
        for (int i = 0; i < hiddenLetters.size(); i++){
            System.out.print(hiddenLetters.get(i));
        }
        System.out.println(" ");
    }

}
