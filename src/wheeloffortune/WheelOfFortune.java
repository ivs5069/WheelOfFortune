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
	public static void displayInstructions(){

		System.out.println("Please select an option by entering the number:"
				+ "\n1. Spin the wheel"
				+ "\n2. Buy a vowel"
				+ "\n3. Solve the puzzle"
				+ "\n4. Quit the game\n");

	}
	//method to get users input
	public static int getUserInput(){
		int userInp = 0;
		Scanner numbInp = new Scanner(System.in); //Scanner object for ints and floats
		boolean run = true;
		while(run == true){
			//Display to the user the instructions
			displayInstructions();

			//Exception handling if the user inputs something other than an int
			try{
				userInp = numbInp.nextInt();


				//input validation to make sure user selects a valid int
				while(userInp < 1 || userInp > 4)
				{
					System.out.println("Invalid input, pick a number 1 through 4.");
					displayInstructions();
					userInp = numbInp.nextInt();
				}
			}
			catch(InputMismatchException E){
				System.out.println("Invalid input, pick a number 1 through 4.");
				numbInp.next();
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

		//Get the users input and assign it to userInput variable
		int userInput = getUserInput();
		System.out.println("You have selected: " + userInput);

	}



}
