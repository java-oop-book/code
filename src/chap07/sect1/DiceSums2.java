package chap07.sect1;

/**
 * Calculates the probability of each possible sum when rolling a pair of dice.
 *
 * @author Drue Coles
 */
public class DiceSums2 {

   public static void main(String[] args) {
      final int faces = 6;
      final int maxSum = 2 * faces;

      // increment counter for each sum
      int[] sums = new int[maxSum + 1];
      for (int die1 = 1; die1 <= faces; die1++) {
         for (int die2 = 1; die2 <= faces; die2++) {
            sums[die1 + die2]++;
         }
      }

      // calculate and format results
      final int possibleRolls = faces * faces;
      final String rowFormat = "%3d %6.1f%% %s %n";

      System.out.printf("%3s %12s%n", "SUM", "PROBABILITY");
      for (int i = 2; i <= maxSum; i++) {
         double prob = (double) sums[i] / possibleRolls * 100;

         // sequence of stars for a bar chart
         int numStars = (int) Math.round(prob);
         String stars = "★".repeat(numStars);

         System.out.printf(rowFormat, i, prob, stars);
      }
   }
}
