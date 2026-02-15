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
      final int comeOutRoll = die1 + die2;

      System.out.printf("You rolled %d + %d = %d. %n", die1, die2, comeOutRoll);

      final String winMessage = "Natural. You win.";
      final String loseMessage = "Craps. You lose.";
      String result = "The game continues."; // provisional

      // each condition is tested independently
      if (comeOutRoll == 7) {
         result = winMessage;
      }
      if (comeOutRoll == 11) {
         result = winMessage;
      }
      if (comeOutRoll == 2) {
         result = loseMessage;
      }
      if (comeOutRoll == 3) {
         result = loseMessage;
      }
      if (comeOutRoll == 12) {
         result = loseMessage;
      }
      System.out.println(result);
   }
}
