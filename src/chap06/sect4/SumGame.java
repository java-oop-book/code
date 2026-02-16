package chap06.sect4;

/**
 * Calculates the exact rounded probability of winning the Sum Game. In this game, the player rolls
 * three dice and wins if one of the rolled numbers equals the sum of the other two.
 *
 * @author Drue Coles
 */
public class SumGame {

   public static void main(String[] args) {
      System.out.println("Calculating the probability of winning the Sum Game...");
      final int sides = 6;
      int winningRolls = 0;
      for (int d1 = 1; d1 <= sides; d1++) {
         for (int d2 = 1; d2 <= sides; d2++) {
            for (int d3 = 1; d3 <= sides; d3++) {
               if (d1 == d2 + d3 || d2 == d1 + d3 || d3 == d1 + d2) {
                  winningRolls++;
               }
            }
         }
      }

      final int totalRolls = sides * sides * sides;
      double probability = (double) winningRolls / totalRolls;
      System.out.printf("Number of winning rolls: %d %n", winningRolls);
      System.out.printf("Number of possible rolls: %d %n", totalRolls);
      System.out.printf("Probability of winning: %.4f %n", probability);
   }
}
