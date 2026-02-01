package chap09.sect4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Prompts the user for a sentence and outputs the words of the sentence in ASCII order and
 * case-insensitive ASCII order.
 *
 * @author Drue Coles
 */
public class ComparatorDemo2 {

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter a sentence: ");
      String[] words = in.nextLine().split(" ");
      System.out.println();

      // remove punctuation from words
      for (int i = 0; i < words.length; i++) {
         words[i] = words[i].replaceAll("[,.?!]", "");
      }
     
      Arrays.sort(words);
      display("Words in ASCII order", words);
      
      Arrays.sort(words, new CaseInsensitiveComparator());
      display("Case-insensitive", words);
   }

    /**
     * Displays a given label followed by the contents of an array on one line.
     */
    private static void display(String label, String[] arr) {
        System.out.printf("%20s: ", label);
        for (String str : arr) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}

class CaseInsensitiveComparator implements Comparator<String> {

    /**
     * Returns a negative number, zero, or a positive number depending on whether s1 is less than
     * equal to, or greater than s2 in case-insensitive ASCII order.
     */
   @Override
   public int compare(String s1, String s2) {
      return s1.compareToIgnoreCase(s2);
   }
}
