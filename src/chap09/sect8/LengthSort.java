package chap09.sect8;

import java.util.Arrays;

/**
 * Sorts a list of strings by length.
 *
 * @author Drue Coles
 */
public class LengthSort {

   public static void main(String[] args) {
      String[] fruit = {"mango", "banana", "tangerine", "kiwi", "apple"};

      Arrays.sort(fruit, (String s, String t) -> Integer.compare(s.length(), t.length()));

      for (String f : fruit) {
         System.out.println(f);
      }
   }
}
