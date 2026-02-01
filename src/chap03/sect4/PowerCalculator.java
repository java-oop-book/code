package chap03.sect4;

import java.util.Scanner;

/**
 * Performs integer exponentiation with a user-specified base and exponent.
 *
 * @author Drue Coles
 */
public class PowerCalculator {

   public static void main(String[] args) {
      // prompt user and read input
      System.out.print("Enter two integers (base and exponent): ");
      Scanner in = new Scanner(System.in);
      int base = in.nextInt();
      int exponent = in.nextInt();

      // format and display result as an equation
      int result = (int) Math.pow(base, exponent);
      System.out.printf("%d^%d = %,d %n", base, exponent, result);
   }
}
