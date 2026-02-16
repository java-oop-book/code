package chap06.sect3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Estimates the probability of winning the Sum Game by Monte Carlo simulation. In this game, the
 * player rolls three dice and wins if one of the rolled numbers equals the sum of the other two.
 *
 * @author Drue Coles
 */
public class SumGame {

   public static void main(String[] args) {
      final int numGames = 100_000_000;
      int wins = 0;

      System.out.println("Simulating the Sum Game...");
      for (int i = 0; i < numGames; i++) {
         if (sumGame()) {
            wins++;
         }
      }

      double probability = (double) wins / numGames;
      System.out.printf("Games played: %,d %n", numGames);
      System.out.printf("Estimated probability of winning: %.4f%% %n", probability);
   }

   /**
    * Simulates the Sum Game.
    *
    * @return true if the player wins and false otherwise
    */
   private static boolean sumGame() {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      int a = rand.nextInt(1, 7);
      int b = rand.nextInt(1, 7);
      int c = rand.nextInt(1, 7);
      return a == b + c || b == a + c || c == a + b;
   }
}
