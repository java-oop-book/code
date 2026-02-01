package chap05.sect2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulates the first roll in the game of Craps. The player rolls two dice and the outcome depends
 * on the sum: natural (7 or 11) - player wins; craps (2, 3, or 12) - player loses; otherwise, the
 * game continues according to a different rule.
 * 
 * @author Drue Coles
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
      String result = "The game continues."; // provisional

      // each condition is tested independently
      if (sum == 7) {
         result = winMessage;
      }
      if (sum == 11) {
         result = winMessage;
      }
      if (sum == 2) {
         result = loseMessage;
      }
      if (sum == 3) {
         result = loseMessage;
      }
      if (sum == 12) {
         result = loseMessage;
      }
      System.out.println(result);
   }
}
