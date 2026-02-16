package chap06.sect2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Plays the game of Craps. The player rolls two dice. There are three possibilities for the
 * come-out roll:
 *    1. Natural (7 or 11) - player wins.
 *    2. Craps (2, 3, or 12) - player loses.
 *    3. In any other case, the player continues rolling until:
 *       a. Rolling the original sum (the point) - player wins.
 *       b. Rolling a 7 (seven out) - player loses.
 *
 * @author Drue Coles
 */
public class Craps {

   public static void main(String[] args) {
      // come-out roll
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      int die1 = rand.nextInt(1, 7);
      int die2 = rand.nextInt(1, 7);
      final int comeOutRoll = die1 + die2;
      System.out.printf("%d + %d = %d %n", die1, die2, comeOutRoll);

      // check if player wins or loses on come-out roll
      if (comeOutRoll == 7 || comeOutRoll == 11) {
         System.out.println("Natural. You win.");
         return;
      }
      if (comeOutRoll == 2 || comeOutRoll == 3 || comeOutRoll == 12) {
         System.out.println("Craps. You lose.");
         return;
      }

      System.out.printf("The point is %d. %n", comeOutRoll);

      int roll = 0; // ensures loop executes at least once

      // keep rolling until player matches point or rolls seven
      while (roll != comeOutRoll && roll != 7) {
         die1 = rand.nextInt(1, 7);
         die2 = rand.nextInt(1, 7);
         roll = die1 + die2;
         System.out.printf("%d + %d = %d %n", die1, die2, roll);
      }

      if (roll == comeOutRoll) {
         System.out.println("You rolled the point. You win.");
      } else {
         System.out.println("Seven out. You lose.");
      }
   }
}
