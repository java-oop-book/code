package chap06.sect2;

import java.util.Scanner;

/**
 * Calculates the sum of consecutive positive integers up to a user-specified limit.
 *
 * @author Drue Coles
 */
public class Summer {

   public static void main(String[] args) {

      System.out.print("Enter a positive integer: ");
      Scanner in = new Scanner(System.in);
      final int limit = in.nextInt();

      int sum = 0;
      for (int next = 1; next <= limit; next++) {
         sum += next;
      }

      System.out.printf("1 + 2 + 3 + ... + %,d = %,d %n", limit, sum);
   }
}
