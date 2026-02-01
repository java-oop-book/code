package chap05.sect3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Flips two coins and outputs the results.
 *
 * @author Drue Coles
 */
public class CoinFlip {

   public static void main(String[] args) {
      ThreadLocalRandom rand = ThreadLocalRandom.current();

      // first flip: using if-else statement
      System.out.print("Coin flip 1: ");
      if (rand.nextBoolean()) {
         System.out.println("HEADS");
      } else {
         System.out.println("TAILS");
      }

      // second flip: using if-else operator
      System.out.println("Coin flip 2: " + (rand.nextBoolean() ? "HEADS" : "TAILS"));
   }
}
