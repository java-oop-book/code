package chap08.sect3;

/**
 * Calculates the probability that Thor beats Zeus. Thor has nine 4-sided dice and Zeus has six
 * 6-sided dice. They roll their dice and add up the numbers. The highest total wins.
 *
 * @author Drue Coles
 */
public class ThorVsZeus {

   public static void main(String[] args) {
      CupOfDice thorCup = new CupOfDice(9, 4); // nine 4-sided dice
      CupOfDice zeusCup = new CupOfDice(6); // six 6-sided dice

      // Monte Carlo simulation
      final int games = 10_000_000;
      int thorWins = 0;
      for (int i = 0; i < games; i++) {
         if (firstPlayerWins(thorCup, zeusCup)) {
            thorWins++;
         }
      }

      double prob = (double) thorWins / games * 100;
      System.out.printf("Thor beats Zeus with probability %.2f%% %n", prob);
   }
   
   /**
    * Rolls two cups and returns true if the first total is higher than the second.
    */
   private static boolean firstPlayerWins(CupOfDice c1, CupOfDice c2) {
      c1.roll();
      c2.roll();
      int s1 = sum(c1.getDice());
      int s2 = sum(c2.getDice());
      return s1 > s2;
   }

   /**
    * Returns the sum of values in an array.
    */
   private static int sum(int[] values) {
      int total = 0;
      for (int val : values) {
         total += val;
      }
      return total;
   }
}
