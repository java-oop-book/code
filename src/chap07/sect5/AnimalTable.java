package chap07.sect5;

import java.util.Arrays;

/**
 * Displays a table of animal names before and after sorting each row.
 *
 * @author Drue Coles
 */
public class AnimalTable {

   public static void main(String[] args) {
      String[][] animals = {
         {"gorilla", "buffalo", "lobster", "cheetah"},
         {"penguin", "quetzal", "meerkat", "octopus"},
         {"vulture", "whippet", "raccoon", "wallaby"}
      };

      System.out.println(toString(animals));

      for (String[] row : animals) {
         Arrays.sort(row);
      }
      System.out.println(toString(animals));
   }

   /**
    * Returns a string representation of a 2D array with rows separated by newlines.
    */
   private static String toString(String[][] arr) {
      String result = "";
      for (String[] row : arr) {
         for (String item : row) {
            result += item + " ";
         }
         result += "\n"; // end of row
      }
      return result;
   }
}
