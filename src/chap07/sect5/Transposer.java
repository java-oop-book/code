package chap07.sect5;

import java.util.Scanner;

/**
 * Accepts a line of text as input and then displays each word 
 * vertically. A "word" is a maximal sequence of non-whitespace 
 * characters.
 *
 * @author Drue Coles
 */
public class Transposer {

   public static void main(String[] args) {
      System.out.print("Enter a line of text: ");
      Scanner in = new Scanner(System.in);
      String input = in.nextLine();
      String[] words = input.split(" ");
      int numWords = words.length;

      // find length of longest word in the list
      int maxWordLength = 0;
      for (String word : words) {
         if (word.length() > maxWordLength) {
            maxWordLength = word.length();
         }
      }

      // Put the i-th word of the input into row i of a 2D array.
      char[][] c = new char[numWords][maxWordLength];
      for (int row = 0; row < numWords; row++) {
         for (int col = 0; col < maxWordLength; col++) {
            if (words[row].length() > col) {
               c[row][col] = words[row].charAt(col);
            } else {
               c[row][col] = ' ';
            }
         }
      }

      // Output the array with rows and columns interchanged.
      for (int col = 0; col < maxWordLength; col++) {
         for (int row = 0; row < numWords; row++) {
            System.out.print(c[row][col] + " ");
         }
         System.out.println();
      }
   }
}
