package chap5.sect5;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulates the first roll in the game of Craps. The player rolls two dice and the outcome depends
 * on the sum: natural (7 or 11) - player wins; craps (2, 3, or 12) - player loses; otherwise, the
 * game continues according to a different rule.
 *
 * @author Drue Coles
 * @version 4.0 - uses logical operators in boolean expressions
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

      if (sum == 7 || sum == 11) {
         System.out.println(winMessage);
      } else if (sum == 2 || sum == 3 || sum == 12) {
         System.out.println(loseMessage);
      } else {
         System.out.println(continueMessage);
      }
   }
}
