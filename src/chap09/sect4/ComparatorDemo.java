package chap09.sect4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates a list of random integers and outputs the contents in unsorted order, sorted
 * numerically, and sorted by magnitude.
 *
 * @author Drue Coles
 */
public class ComparatorDemo {

   public static void main(String[] args) {
      final int size = 5;
      Integer[] a = new Integer[size];
      for (int i = 0; i < size; i++) {
         a[i] = ThreadLocalRandom.current().nextInt(-99,100);
      }
      display("Unsorted", a);
      
      Arrays.sort(a);
      display("Sorted numerically", a);
      
      Arrays.sort(a, new MagnitudeComparator());
      display("Sorted by magnitude", a);
   }

   /**
    * Displays a given label followed by the contents of an array on one line.
    */
   private static void display(String label, Integer[] arr) {
      System.out.printf("%19s: ", label);
      for (int num : arr) {
         System.out.printf("%+d ", num);
      }
      System.out.println();
   }
}

class MagnitudeComparator implements Comparator<Integer> {

   /**
    * Returns a negative number, zero, or a positive number depending on whether the magnitude of
    * i1 is less than, equal to, or greater than the magnitude of i2.
    */
   @Override
   public int compare(Integer i1, Integer i2) {
      return Integer.compare(Math.abs(i1), Math.abs(i2));
   }
}
