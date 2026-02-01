package chap09.sect3;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Sorts several arrays of different types and outputs the results
 *
 * @author Drue Coles
 */
public class ArraySortDemo {

   public static void main(String[] args) {
      sortDoubles();
      sortStrings();
      sortBigIntegers();
      sortMilePaces();
   }

   /**
    * Sorts an array of random doubles and outputs the result.
    */
   private static void sortDoubles() {
      double[] numbers = new double[5];
      for (int i = 0; i < numbers.length; i++) {
         numbers[i] = ThreadLocalRandom.current().nextDouble();
      }

      Arrays.sort(numbers);
      System.out.print("Sorted doubles: ");
      for (double num : numbers) {
         System.out.printf("%1.3f ", num);
      }
      System.out.println();
   }

   /**
    * Sorts an array of strings and outputs the result.
    */
   private static void sortStrings() {
      String[] animals = {"vicuna", "quokka", "okapi", "quagga", "ibex"};

      Arrays.sort(animals);
      System.out.print("Sorted strings: ");
      for (String a : animals) {
         System.out.print(a + " ");
      }
      System.out.println();
   }

   /**
    * Sorts an array of BigIntegers and outputs the result.
    */
   private static void sortBigIntegers() {
      BigInteger[] bigInts = new BigInteger[5];

      for (int i = 0; i < bigInts.length; i++) {
        bigInts[i] = getRandomBigInteger(20);
      }

      Arrays.sort(bigInts);
      System.out.println("Sorted BigIntegers:");
      for (BigInteger num : bigInts) {
         System.out.printf("   %,d %n", num);
      }
   }

   /**
    * Returns a BigInteger constructed with a string of random digits.
    */
   private static BigInteger getRandomBigInteger(int numDigits) {
      String digits = "";
      for (int i = 0; i < numDigits; i++) {
         digits += ThreadLocalRandom.current().nextInt(10);
      }
      return new BigInteger(digits);
   }

   /**
    * Sorts an array of MilePace objects and outputs the result.
    */
   private static void sortMilePaces() {
      // pairs of ints representing minute-second combinations
      int[][] minSec = {
         {5, 45}, {6, 1}, {7, 59}, {4, 43}, {5, 9}
      };

      // initialize array with MilePace objects
      MilePace[] milePaces = new MilePace[minSec.length];
      for (int i = 0; i < minSec.length; i++) {
         milePaces[i] = new MilePace(minSec[i][0], minSec[i][1]);
      }

      Arrays.sort(milePaces);
      System.out.print("Sorted MilePaces: ");
      for (MilePace mp : milePaces) {
         System.out.print(mp + " ");
      }
      System.out.println();
   }
}

/**
 * Represents a runner's pace for a mile in minutes and seconds.
 */
class MilePace implements Comparable<MilePace> {

   private final int min;
   private final int sec;

   public MilePace(int min, int sec) {
      this.min = min;
      this.sec = sec;
   }

   @Override
   public String toString() {
      return String.format("%d:%02d", min, sec);
   }

   /**
    * Returns a negative integer, zero, or a positive integer depending on whether this mile pace is
    * faster than, equal to, or slower than another.
    */
   @Override
   public int compareTo(MilePace other) {
      if (this.min == other.min) {
         return this.sec - other.sec;
      }
      return this.min - other.min;
   }
}
