package chap07.sect3;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Estimates the probability that at least two people in a random sample share the same birthday.
 *
 * @author Drue Coles
 */
public class BirthdayParadox {

   public static void main(String[] args) {
      System.out.print("Number of birthdays to sample: ");
      Scanner in = new Scanner(System.in);
      final int sampleSize = in.nextInt();
      final int trials = 10_000_000;
      int matches = 0;

      // Monte Carlo simulation
      System.out.printf("Simulating %,d trials... %n", trials);
      for (int i = 0; i < trials; i++) {
         if (sampleHasMatch(sampleSize)) {
            matches++;
         }
      }

      double prob = (double) matches / trials * 100;
      System.out.printf("Probability of a birthday match: %.2f%% %n", prob);
   }

   /**
    * Samples random birthdays and returns true if any day is chosen twice.
    */
   private static boolean sampleHasMatch(int sampleSize) {
      final int daysInYear = 365;
      boolean[] previouslyChosen = new boolean[daysInYear];

      for (int i = 0; i < sampleSize; i++) {
         int j = ThreadLocalRandom.current().nextInt(daysInYear); // choose random birthday
         if (previouslyChosen[j]) {
            return true;
         }
         previouslyChosen[j] = true;
      }
      return false;
   }
}
