package javabook.chap8.sect5;

import java.util.Arrays;

/**
 * Calculates the probability of rolling a full house. Five numbers form a full house if two are
 * equal and the remaining three are equal.
 *
 * @author Drue Coles
 */
public class FullHouse {

   public static void main(String[] args) {
      CupOfDice cup = new CupOfDice(5);

      // Monte Carlo simulation
      final int trials = 10_000_000;
      int wins = 0;
      for (int i = 0; i < trials; i++) {
         cup.roll();
         if (isFullHouse(cup.getDice())) {
            wins++;
         }
      }

      double prob = (double) wins / trials * 100;
      System.out.printf("Full house probability: %.2f%% %n", prob);
   }

   /**
    * Returns true if a given array contains a full house.
    */
   private static boolean isFullHouse(int[] values ) {
      Arrays.sort(values);
      boolean twoThenThree = values[0] == values[1] && values[2] == values[4];
      boolean threeThenTwo = values[0] == values[2] && values[3] == values[4];
      return twoThenThree || threeThenTwo;
   }
}
