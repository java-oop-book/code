package chap4.sect2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Rolls three dice for two players and displays them in ascending order.
 *
 * @author Drue Coles
 */
public class ThreeDiceRoller2 {

   public static void main(String[] args) {
      System.out.printf("Player 1 rolls %s.%n", rollDice());
      System.out.printf("Player 2 rolls %s.%n", rollDice());
   }

   /**
    * Rolls three dice.
    *
    * @return a string with the dice values in ascending order
    */
   private static String rollDice() {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      final int sides = 6;
      int die1 = rand.nextInt(1, sides + 1);
      int die2 = rand.nextInt(1, sides + 1);
      int die3 = rand.nextInt(1, sides + 1);

      int lo = Math.min(die1, Math.min(die2, die3));
      int hi = Math.max(die1, Math.max(die2, die3));
      int mid = (die1 + die2 + die3) - lo - hi;

      return lo + "-" + mid + "-" + hi;
   }
}
