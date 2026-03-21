package chap07.sect1;

/**
 * Calculates the probability of each possible sum when rolling a pair of dice.
 *
 * @author Drue Coles
 */
public class DiceSums {

   public static void main(String[] args) {
      // possible sums
      int sum2 = 0;
      int sum3 = 0;
      int sum4 = 0;
      int sum5 = 0;
      int sum6 = 0;
      int sum7 = 0;
      int sum8 = 0;
      int sum9 = 0;
      int sum10 = 0;
      int sum11 = 0;
      int sum12 = 0;

      // for each possible outcome, increment the corresponding counter
      for (int die1 = 1; die1 <= 6; die1++) {
         for (int die2 = 1; die2 <= 6; die2++) {
             switch (die1 + die2) {
                 case 2 -> sum2++;
                 case 3 -> sum3++;
                 case 4 -> sum4++;
                 case 5 -> sum5++;
                 case 6 -> sum6++;
                 case 7 -> sum7++;
                 case 8 -> sum8++;
                 case 9 -> sum9++;
                 case 10 -> sum10++;
                 case 11 -> sum11++;
                 case 12 -> sum12++;
             }
         }
      }

      // calculate and format results
      System.out.printf("%3s %12s%n", "SUM", "PROBABILITY");
      final int possibleRolls = 36;
      final String rowFormat = "%3d %6.1f%% %n";

      double prob2 = (double) sum2 / possibleRolls * 100;
      System.out.printf(rowFormat, 2, prob2);

      double prob3 = (double) sum3 / possibleRolls * 100;
      System.out.printf(rowFormat, 3, prob3);

      double prob4 = (double) sum4 / possibleRolls * 100;
      System.out.printf(rowFormat, 4, prob4);

      double prob5 = (double) sum5 / possibleRolls * 100;
      System.out.printf(rowFormat, 5, prob5);

      double prob6 = (double) sum6 / possibleRolls * 100;
      System.out.printf(rowFormat, 6, prob6);

      double prob7 = (double) sum7 / possibleRolls * 100;
      System.out.printf(rowFormat, 7, prob7);

      double prob8 = (double) sum8 / possibleRolls * 100;
      System.out.printf(rowFormat, 8, prob8);

      double prob9 = (double) sum9 / possibleRolls * 100;
      System.out.printf(rowFormat, 9, prob9);

      double prob10 = (double) sum10 / possibleRolls * 100;
      System.out.printf(rowFormat, 10, prob10);

      double prob11 = (double) sum11 / possibleRolls * 100;
      System.out.printf(rowFormat, 11, prob11);

      double prob12 = (double) sum12 / possibleRolls * 100;
      System.out.printf(rowFormat, 12, prob12);
   }
}
