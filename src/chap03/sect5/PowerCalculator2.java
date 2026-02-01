package chap03.sect5;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Performs unbounded integer exponentiation with a user-specified base and exponent.
 *
 * @author Drue Coles
 */
public class PowerCalculator2 {

   public static void main(String[] args) {
      // prompt the user and read input
      System.out.print("Enter two integers (base and exponent): ");
      Scanner in = new Scanner(System.in);
      BigInteger base = in.nextBigInteger();
      int exponent = in.nextInt();
      
      BigInteger result = base.pow(exponent);

      // format and display result as an equation
      System.out.printf("%d^%d = %,d %n", base, exponent, result);
   }
}
