package chap5.sect1;

import java.math.BigInteger;

/**
 * Demonstrates the difference between shallow and deep equality.
 *
 * @author Drue Coles
 */
public class DeepEquality {

   public static void main(String[] args) {
      int v1 = 123;
      int v2 = 123;
      System.out.printf(" int values: %d == %d is %b %n", v1, v2, v1 == v2);

      BigInteger b1 = BigInteger.valueOf(123);
      BigInteger b2 = BigInteger.valueOf(123);

      // test shallow equality
      System.out.printf("BigIntegers: %s == %s is %b %n", b1, b2, b1 == b2);

      // test deep equality
      System.out.printf("BigIntegers: %s.equals(%s) is %b %n", b1, b2, b1.equals(b2));
   }
}
