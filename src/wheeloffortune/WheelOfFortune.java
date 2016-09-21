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
    public static void displayInstructions() {
        PuzzleBoard puzzle = new PuzzleBoard();
        System.out.println("\t\t======================"
                + "\n\t\t=  Wheel Of Fortune  ="
                + "\n\t\t======================\n");

        puzzle.getPuzzleBoard();
        System.out.println("\n\n1. Spin the wheel"
                + "\n2. Buy a vowel"
                + "\n3. Solve the puzzle"
                + "\n4. Quit"
                + "\n9. Test letter input");

    }

    //method to get users input
    public static int getUserInput() {
        int userInp = 0;
        Scanner numbInp = new Scanner(System.in); //Scanner object for ints and floats

        //Display to the user the instructions
        displayInstructions();

        System.out.print("Enter choice: ");

        //Exception handling if the user inputs something other than an int
        try {
            userInp = numbInp.nextInt();

            //input validation to make sure user selects a valid int
            while (userInp < 1 || userInp > 9) {
                System.out.println("\n\nInvalid input, pick a number 1 through 9.");
                displayInstructions();
                userInp = numbInp.nextInt();
            }
        } catch (InputMismatchException E) {
            System.out.println("\n\nInvalid input, pick a number 1 through 9.");
            //Clear out the Scanner, otherwise it will infinitly loop
            numbInp.next();
            //Return to the top of the while loop
            userInp = getUserInput();

        }

        return userInp;
    }

    /**
     * Method to guess a letter
     *
     * @return userChar
     */
    public static String getLetter() {
        String userChar = "a";
        Scanner charInput = new Scanner(System.in); //Scanner object for char inputs

        //Ask the user for a letter and store that input in userChar
        System.out.print("Please enter a single letter to guess: ");
        userChar = charInput.next();

        //Ensure that the input was a single alphabetical character
        while (userChar.length() > 2 || !(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z").contains(userChar.toUpperCase()))) {
            System.out.print("Please only enter a single alphabetical character. Please input again: ");
            userChar = charInput.next();
        }

        System.out.println("The letter you chose was: " + userChar);
        return userChar;
    }

    /**
     * Method to spin the wheel
     */
    public static String spinWheel() {
        //Create an array to store the wheel wedges
        //Wedges held as STRINGS, numbers will be parsed when they will be used.
        //If wedge != 'bankrupt' OR wedge != 'LOSE A TURN': parse int wedge
        String[] wedge = {"$5000", "$600", "$500", "$300", "$500", "$800", "$550", "$400", "$300", "$900", "$500", "$300", "$900", "BANKRUPT", "$600", "$400", "$300", "LOSE A TURN", "$800", "$350", "$450", "$700", "$300", "$600"};

        //Initialize a random object
        Random ran = new Random();

        //Get a random wedge by picking a random index in the array
        String spunWedge = wedge[ran.nextInt(wedge.length)];

        //return the random wedge
        return spunWedge;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {



        OUTER:
        while (true) {
            int userInput = getUserInput();
            //If user selects spin wheel
            switch (userInput) {
                //if user selects buy a vowel
                case 1:
                    System.out.println("You have selected: Spin the wheel.");
                    System.out.println("You landed on: " + spinWheel());
                    break;
                //if user selects solve puzzle
                case 2:
                    System.out.println("You have selected: Buy a vowel.");
                    break;
                case 3:
                    System.out.println("You have selected: Solve the puzzle.");
                    break;
                case 4:
                    System.out.println("You have selected: Quit");
                    System.out.println("Quitting the game");
                    break OUTER;
                case 9:
                    System.out.println("You have selected: Test letter input.");
                    System.out.println("You have selected: " + getLetter());
                    break;
                default:
                    break;
            }
        }
    }

}
