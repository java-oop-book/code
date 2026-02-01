package chap09.sect3;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Outputs a sorted list of random rational numbers.
 *
 * @author Drue Coles
 */
public class RationalSortDemo {

   public static void main(String[] args) {
      // fill array with 5 random rational numbers
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      RationalNumber[] rNumbers = new RationalNumber[5];
      for (int i = 0; i < rNumbers.length; i++) {
         int numerator = rand.nextInt(1, 10);
         int denominator = rand.nextInt(1, 10);
         rNumbers[i] = new RationalNumber(numerator, denominator);
      }

      // sort the array and output contents
      Arrays.sort(rNumbers);
      for (RationalNumber num : rNumbers) {
         System.out.println(num);
      }
   }
}
