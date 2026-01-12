package chap3.sect3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Rolls a pair of virtual dice and outputs the result.
 *
 * @author Drue Coles
 */
public class DiceRoller {

   public static void main(String[] args) {
      ThreadLocalRandom rand = ThreadLocalRandom.current();

      // roll dice and compute sum
      int die1 = rand.nextInt(1, 7);
      int die2 = rand.nextInt(1, 7);
      int sum = die1 + die2;

      System.out.println("First die: " + die1);
      System.out.println("Second die: " + die2);
      System.out.println("Sum: " + sum);
   }
}
