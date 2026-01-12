package chap4.sect2;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Rolls three dice for two players and displays them in ascending order.
 *
 * @author Drue Coles
 */
public class ThreeDiceRoller3 {

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter number of sides: ");
      int sides = in.nextInt();
      System.out.printf("Player 1 rolls %s.%n", rollDice(sides));
      System.out.printf("Player 2 rolls %s.%n", rollDice(sides));
   }

   /**
    * Rolls three dice.
    *
    * @return a string with the three values in ascending order
    */
   private static String rollDice(int sides) {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      int die1 = rand.nextInt(1, sides + 1);
      int die2 = rand.nextInt(1, sides + 1);
      int die3 = rand.nextInt(1, sides + 1);

      int lo = Math.min(die1, Math.min(die2, die3));
      int hi = Math.max(die1, Math.max(die2, die3));
      int mid = (die1 + die2 + die3) - lo - hi;

      return lo + "-" + mid + "-" + hi;
   }
}
