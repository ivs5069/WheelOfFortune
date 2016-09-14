/*
 * CMPSC 261, Section 1
 * Fall 2016
 * Instructor: Phil O'Connell
 * TODO: DON'T FORGET TO PUT YOUR NAME AND ID
 * Student: Ion Sirotkin
 * ID: ivs5069
 */
package wheeloffortune;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WheelOfFortune {

    //method to print out instructions
    public static void displayInstructions() {

        System.out.println("Please select an option by entering the number:"
                + "\n1. Spin the Wheel"
                + "\n2. Buy a Vowel"
                + "\n3. Solve the Puzzle"
                + "\n4. Quit the Game");

    }
    //method to get users input

    public static int getUserInput() {
        int userInp = 0;
        Scanner numbInp = new Scanner(System.in); //Scanner object for ints and floats
        boolean run = true;

        //If the loop manages to complete, the user entered a valid input
        while (run == true) {
            //Display to the user the instructions
            displayInstructions();

            //Exception handling if the user inputs something other than an int
            try {
                userInp = numbInp.nextInt();

                //input validation to make sure user selects a valid int
                while (userInp < 1 || userInp > 4) {
                    System.out.println("Invalid input, pick a number 1 through 4.");
                    displayInstructions();
                    userInp = numbInp.nextInt();
                }
            } catch (InputMismatchException E) {
                System.out.println("Invalid input, pick a number 1 through 4.");
                //Clear out the Scanner, otherwise it will infinitly loop
                numbInp.next();
                //Return to the top of the while loop
                continue;
            }
            run = false;
        }

        return userInp;
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
                    System.out.println("You have selected: Spin the Wheel.");
                    break;
            //if user selects solve puzzle
                case 2:
                    System.out.println("You have selected: Buy a Vowel.");
                    break;
                case 3:
                    System.out.println("You have selected: Solve the Puzzle.");
                    break;
                case 4:
                    System.out.println("You have selected: Quit the Game.");
                    System.out.println("Quitting the game");
                    break OUTER;
                default:
                    break;
            }
        }
    }

}
