package chap07.sect4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Calculates the hailstone sequence starting at a number chosen by the user and outputs the
 * number of terms, the largest term, and the number of records.
 *
 * By definition, each term of a hailstone sequence is obtained from the previous term according to
 * the following rule:
 *
 *    (a) If even, divide by 2.
 *    (b) If odd, multiply by 3 and add 1.
 *
 * The sequence terminates when the number 1 appears. For example: 12, 6, 3, 10, 5, 16, 8, 4, 2, 1.
 *
 * Aside: it is a famous conjecture in mathematics that every hailstone sequence eventually
 * terminates, but nobody has ever been able to prove that this is so.
 *
 * @author Drue Coles
 */
public class Hailstones {

   public static void main(String[] args) {
      System.out.print("Enter a positive integer: ");
      Scanner in = new Scanner(System.in);
      int initialTerm = in.nextInt();

      System.out.printf("Calculating the hailstone sequence starting at %,d... %n", initialTerm);
      ArrayList<Integer> hailstones = getHailstones(initialTerm);

      int numTerms = hailstones.size();
      int maxValue = 0;
      int records = 0;

      for (int n : hailstones) {
         if (n > maxValue) {
            maxValue = n;
            records++;
         }
      }

      System.out.printf("Number of terms: %,d %n", numTerms);
      System.out.printf("Number of records: %,d %n", records);
      System.out.printf("Maximum value: %,d %n", maxValue);
   }

   /**
    * Returns the hailstone sequence starting at n.
    */
   private static ArrayList<Integer> getHailstones(int n) {
      ArrayList<Integer> hailstones = new ArrayList<>();
      hailstones.add(n);
      while (n > 1) {
         if (n % 2 == 0) {
            n /= 2;
         } else {
            n = 3 * n + 1;
         }
         hailstones.add(n);
      }
      return hailstones;
   }
}
