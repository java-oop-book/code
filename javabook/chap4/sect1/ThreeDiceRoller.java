package edu.commonwealthu.chap4.sect1;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Rolls three dice for two players and displays them in ascending order.
 *
 * @author Drue Coles
 */
public class  ThreeDiceRoller {

   public static void main(String[] args) {

      ThreadLocalRandom rand = ThreadLocalRandom.current();
      final int sides = 6;

      // first player
      int die1 = rand.nextInt(1, sides + 1);
      int die2 = rand.nextInt(1, sides + 1);
      int die3 = rand.nextInt(1, sides + 1);

      // sort the values
      int lo = Math.min(die1, Math.min(die2, die3));
      int hi = Math.max(die1, Math.max(die2, die3));
      int mid = (die1 + die2 + die3) - lo - hi;

      System.out.printf("Player 1 rolls %d-%d-%d%n", lo, mid, hi);

      // second player
      die1 = rand.nextInt(1, sides + 1);
      die2 = rand.nextInt(1, sides + 1);
      die3 = rand.nextInt(1, sides + 1);

      // sort the values
      lo = Math.min(die1, Math.min(die2, die3));
      hi = Math.max(die1, Math.max(die2, die3));
      mid = (die1 + die2 + die3) - lo - hi;

      System.out.printf("Player 2 rolls %d-%d-%d%n", lo, mid, hi);
   }
}
