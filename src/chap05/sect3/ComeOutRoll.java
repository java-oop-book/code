package chap05.sect3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulates the first roll in the game of Craps. The player rolls two dice and the outcome depends
 * on the sum: natural (7 or 11) - player wins; craps (2, 3, or 12) - player loses; otherwise, the
 * game continues according to a different rule.
 *
 * @author Drue Coles
 * @version 2.0 - uses if-else chain
 */
public class ComeOutRoll {

   public static void main(String[] args) {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      int die1 = rand.nextInt(1, 7);
      int die2 = rand.nextInt(1, 7);
      final int comeOutRoll = die1 + die2;

      System.out.printf("You rolled %d + %d = %d. %n", die1, die2, comeOutRoll);

      final String winMessage = "Natural. You win.";
      final String loseMessage = "Craps. You lose.";
      final String continueMessage = "The game continues";

      if (comeOutRoll == 7) {
         System.out.println(winMessage);
      } else if (comeOutRoll == 11) {
         System.out.println(winMessage);
      } else if (comeOutRoll == 2) {
         System.out.println(loseMessage);
      } else if (comeOutRoll == 3) {
         System.out.println(loseMessage);
      } else if (comeOutRoll == 12) {
         System.out.println(loseMessage);
      } else {
         System.out.println(continueMessage);
      }
   }
}
