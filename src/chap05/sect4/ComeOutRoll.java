package chap05.sect4;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulates the first roll in the game of Craps. The player rolls two dice and the outcome depends
 * on the sum: natural (7 or 11) - player wins; craps (2, 3, or 12) - player loses; otherwise, the
 * game continues according to a different rule.
 *
 * @author Drue Coles
 * @version 3.0 - uses both a switch statement and a switch expression for comparison
 */
public class ComeOutRoll {

   public static void main(String[] args) {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      int die1 = rand.nextInt(1, 7);
      int die2 = rand.nextInt(1, 7);
      int sum = die1 + die2;

      System.out.printf("You rolled %d + %d = %d.%n", die1, die2, sum);

      final String winMessage = "You win.";
      final String loseMessage = "You lose.";
      final String continueMessage = "The game continues.";

      // switch statement
      switch (sum) {
         case 7, 11 -> System.out.println(winMessage);
         case 2, 3, 12 -> System.out.println(loseMessage);
         default -> System.out.println(continueMessage);
      }

      // switch expression
      String result = switch (sum) {
         case 7, 11 -> "You win.";
         case 2, 3, 12 -> "You lose.";
         default -> "The game continues.";
      };
      System.out.println(result);
   }
}
