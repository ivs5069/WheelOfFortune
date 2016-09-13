/*
* CMPSC 261, Section 1
* Fall 2016
* Instructor: Phil O'Connell
* TODO: DON'T FORGET TO PUT YOUR NAME AND ID
* Student: Ion Sirotkin
* ID: ivs5069
*/
package wheeloffortune;

import java.util.Scanner;


public class WheelOfFortune {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
      Scanner numbInp = new Scanner(System.in); //Scanner object for ints and floats
      int numbSelect;
      
      System.out.println("Please select an option by entering the number:"
              + "\n1. Spin the wheel"
              + "\n2. Buy a vowel"
              + "\n3. Solve the puzzle"
              + "\n4. Quit the game\n");
      numbSelect = numbInp.nextInt();
      System.out.println("You have selected: " + numbSelect);
      
  }
  
  
}
