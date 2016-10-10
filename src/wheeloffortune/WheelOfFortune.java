/*
 * CMPSC 261, Section 1
 * Fall 2016
 * Instructor: Phil O'Connell
 * TODO: DON'T FORGET TO PUT YOUR NAME AND ID
 * Student: Ion Sirotkin
 * ID: ivs5069
 */
package wheeloffortune;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortune {

    //method to print out instructions
    public static void displayInstructions(PuzzleBoard puzzle, int moneyEarned) {

        System.out.println("\n\t\t======================"
                + "\n\t\t=  Wheel Of Fortune  ="
                + "\n\t\t======================\n");

        puzzle.getPuzzleBoard();

        //Print out how much money the user made
        System.out.printf("\nYou have made $%d ", moneyEarned);

        System.out.println("\n\n1. Spin the wheel"
                + "\n2. Buy a vowel"
                + "\n3. Solve the puzzle"
                + "\n4. Quit");
        //Commented out debugging options
        // + "\n8. Toggle puzzle reveal"
        //+ "\n9. Test letter input");

    }

    //method to get users input
    public static int getUserInput(PuzzleBoard puzzle, int moneyEarned) {
        int userInp = 0;
        Scanner numbInp = new Scanner(System.in); //Scanner object for ints and floats

        //Display to the user the instructions
        displayInstructions(puzzle, moneyEarned);

        System.out.print("Enter choice: ");

        //Exception handling if the user inputs something other than an int
        try {
            userInp = numbInp.nextInt();

            //input validation to make sure user selects a valid int
            while (userInp < 1 || userInp > 9) {
                System.out.println("\n\nInvalid input, pick a number 1 through 9.");
                displayInstructions(puzzle, moneyEarned);
                userInp = numbInp.nextInt();
            }
        } catch (InputMismatchException E) {
            System.out.println("\n\nInvalid input, pick a number 1 through 9.");
            //Clear out the Scanner, otherwise it will infinitly loop
            numbInp.next();
            //Return to the top of the while loop
            userInp = getUserInput(puzzle, moneyEarned);

        }

        return userInp;
    }

    /**
     * Method to guess a letter
     *
     * @param puzzle
     * @return userChar
     */
    public static String getLetter(PuzzleBoard puzzle) {
        String userChar = "a";
        Scanner charInput = new Scanner(System.in); //Scanner object for char inputs

        //Create flag whether letter was guessed yet or not
        boolean guessedFlag = false;

        //Ask the user for a letter and store that input in userChar
        System.out.print("Please enter a single letter to guess: ");
        userChar = charInput.next().toUpperCase();
        //Make sure the letter was not guessed yet
        guessedFlag = puzzle.checkGuessed(userChar);

        //Ensure that the input was a single alphabetical character and not guessed yet
        while (userChar.length() > 2 || guessedFlag == true || !(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z").contains(userChar.toUpperCase()))) {

            //If statement if the input validation failed on guess flag
            if (guessedFlag == true) {
                System.out.print("Letter already guessed. Please input another letter: ");
                userChar = charInput.next().toUpperCase();
                guessedFlag = puzzle.checkGuessed(userChar);

            } else {
                System.out.print("Please only enter a single alphabetical character. Please input again: ");
                userChar = charInput.next().toUpperCase();
                guessedFlag = puzzle.checkGuessed(userChar);
            }
        }

        System.out.println("The letter you chose was: " + userChar);
        return userChar;
    }

    /**
     * Method for the user to buy a vowel
     *
     * @param puzzle
     * @param moneyEarned
     * @return vowel
     */
    public static int buyLetter(PuzzleBoard puzzle, int moneyEarned) {
        //If the user actually has money, let him buy a vowel
        if (moneyEarned > 0) {
            String vowel;
            vowel = getLetter(puzzle);

            //Input validation to make sure it is a vowel that is bought
            while (!(Arrays.asList("A", "E", "I", "E", "O", "U").contains(vowel))) {
                System.out.print("Please enter a vowel: ");
                vowel = getLetter(puzzle);
            }

            puzzle.guessLetter(vowel.toUpperCase());

            return (moneyEarned - 250);
        } else {
            System.out.println("Insufficient funds. You need $250 to buy a vowel.");
            return 0;
        }

    }

    /**
     * Method to spin the wheel
     *
     * @param puzzle
     * @param moneyEarned
     * @return moneyEarned
     */
    public static int spinWheel(PuzzleBoard puzzle, int moneyEarned) {
        //Create an array to store the wheel wedges
        //Wedges held as STRINGS, numbers will be parsed when they will be used.
        //If wedge != 'bankrupt' OR wedge != 'LOSE A TURN': parse int wedge
        String[] wedge = {"$5000", "$600", "$500", "$300", "$500", "$800", "$550", "$400", "$300", "$900", "$500", "$300", "$900", "BANKRUPT", "$600", "$400", "$300", "LOSE A TURN", "$800", "$350", "$450", "$700", "$300", "$600"};
        int timesLanded = 0;

        String letterGuess;
        //Initialize a random object
        Random ran = new Random();

        //Get a random wedge by picking a random index in the array
        String spunWedge = wedge[ran.nextInt(wedge.length)];

        System.out.println("You landed on: " + spunWedge);

        letterGuess = getLetter(puzzle).toUpperCase();

        //Input validation to make sure the user does not guess a vowel
        while (Arrays.asList("A", "E", "I", "E", "O", "U").contains(letterGuess)) {
            System.out.println("Enter a letter that is not a vowel.");
            letterGuess = getLetter(puzzle).toUpperCase();
        }
        //If the user lands on any of the wedges that contain money
        if (!"BANKRUPT".equals(spunWedge) && !"LOSE A TURN".equals(spunWedge)) {
            //Guess a letter and set moneyEarned to the amount of times it was rolled times the worth of the value
            moneyEarned = Integer.parseInt(spunWedge.substring(1)) * puzzle.guessLetter(letterGuess);
        } //If the user lands on bankrupt      
        else if ("BANKRUPT".equals(spunWedge)) {
            //Set money equal to zero     
            moneyEarned = 0;
        } else {
            puzzle.guessLetter(letterGuess);
        }

        return moneyEarned;
    }

    public static boolean guessPuzzle(PuzzleBoard puzzle) {
        //Flag to hold whether the user lost or not.
        boolean gameOverFlag = false;
        String letterGuess;
        System.out.println("Please print out your guess of the board. If you guess a letter wrong you lose.");

        //Loop over the length of the string
        while (true) {
            //If the letter is masked let the user guess, if not masked then go to the next unmasked letter

            //Display to the user the board
            puzzle.getPuzzleBoard();
            letterGuess = getLetter(puzzle).toUpperCase();
            puzzle.guessLetter(letterGuess);

            //Check if the letter is contained within the puzzle selected. If it is contained return a true for gameOverFlag
            if (puzzle.checkLetterContained(letterGuess) == false) {
                System.out.println("Wrong. Game over.");
                gameOverFlag = true;
                break;
            }
            gameOverFlag = puzzle.getWinFlag();
            //Break if gameOverFlag is hit
            if (gameOverFlag) {
                break;
            }
        }
        return gameOverFlag;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PuzzleBoard puzzle = new PuzzleBoard();

        int moneyEarned = 0;
        //Quit flag for the while loop
        boolean quit = false;
        while (!quit) {
            quit = puzzle.getWinFlag();
            int userInput = getUserInput(puzzle, moneyEarned);
            //If user selects spin wheel
            switch (userInput) {
                //if user selects buy a vowel
                case 1:
                    System.out.println("You have selected: Spin the wheel.");
                    moneyEarned += spinWheel(puzzle, moneyEarned);
                    break;
                //if user selects solve puzzle
                case 2:
                    System.out.println("You have selected: Buy a vowel.");
                    moneyEarned = buyLetter(puzzle, moneyEarned);
                    break;
                case 3:
                    System.out.println("You have selected: Solve the puzzle.");
                    //Store the result of guess puzzle as boolean in quit
                    //If quit is returned as true, break the while loop
                    quit = guessPuzzle(puzzle);
                    break;
                case 4:
                    System.out.println("You have selected: Quit");
                    System.out.println("Quitting the game");
                    quit = true;
                    break;
                /**
                 *
                 * Commented out debugging options case 8:
                 * System.out.println("You have selected: Puzzle Reveal");
                 * puzzle.toggleReveal(); break; case 9: System.out.println("You
                 * have selected: Test letter input."); System.out.println("You
                 * have selected: " + getLetter(puzzle)); break; default: break;
                 */
            }
        }
    }

}
