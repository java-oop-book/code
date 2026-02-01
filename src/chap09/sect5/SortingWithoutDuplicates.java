package chap09.sect5;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Prompts the user for a line of text and outputs the words in sorted order without duplicates.
 *
 * @author Drue Coles
 */
public class SortingWithoutDuplicates {

   public static void main(String[] args) {
      System.out.print("Enter a line of text: ");

      // read a line of text, split it into words, and store them in an array for constructing a set
      String[] words = new Scanner(System.in).nextLine().split(" ");
      Set<String> set = new TreeSet<>(Arrays.asList(words));

      // display words in sorted order
      System.out.print("Your words: ");
      for (String word : set) {
         System.out.print(word + " ");
      }
      System.out.println();
   }
}
