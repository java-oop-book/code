package chap8.sect5;

import java.util.Scanner;

/**
 * Performs arithmetic operations on two rational numbers specified by the user.
 *
 * @author Drue Coles
 */
public class RationalCalculator {

   public static void main(String[] args) {

      System.out.print("Enter two rational numbers (n1 d1 n2 d2): ");
      Scanner in = new Scanner(System.in);
      int n1 = in.nextInt();
      int d1 = in.nextInt();
      int n2 = in.nextInt();
      int d2 = in.nextInt();

      RationalNumber a = new RationalNumber(n1, d1);
      RationalNumber b = new RationalNumber(n2, d2);

      // Compute the sum, difference, product, and quotient.
      RationalNumber w = a.add(b);
      RationalNumber x = a.subtract(b);
      RationalNumber y = a.multiply(b);
      RationalNumber z = a.divide(b);

      System.out.printf("%s + %s = %s %n", a, b, w);
      System.out.printf("%s - %s = %s %n", a, b, x);
      System.out.printf("%s * %s = %s %n", a, b, y);
      System.out.printf("%s / %s = %s %n", a, b, z);

      // Compute a^2 + b^2.
      RationalNumber sumSquares = a.multiply(a).add(b.multiply(b));
      System.out.printf("(%s)^2 + (%s)^2 = %s %n", a, b, sumSquares);
   }
}
