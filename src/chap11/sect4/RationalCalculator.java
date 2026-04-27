package chap11.sect4;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Prompts the user for two rational numbers and outputs the results of various operations.
 * Demonstrates exception handling for invalid input and object construction.
 *
 * @author Drue Coles
 */
public class RationalCalculator {

    public static void main(String[] args) {
        RationalNumber a = readRational();
        RationalNumber b = readRational();

        // basic operations
        System.out.printf("%s + %s = %s %n", a, b, a.add(b));
        System.out.printf("%s - %s = %s %n", a, b, a.subtract(b));
        System.out.printf("%s * %s = %s %n", a, b, a.multiply(b));

        // division (may fail)
        try {
            System.out.printf("%s / %s = %s %n", a, b, a.divide(b));
        } catch (ArithmeticException e) {
            System.err.printf("Cannot divide %s by 0. %n", a);
        }

        // sum of squares
        RationalNumber sumSquares = a.multiply(a).add(b.multiply(b));
        System.out.printf("(%s)^2 + (%s)^2 = %s %n", a, b, sumSquares);
    }

    /**
     * Prompts the user to enter a rational number and returns it. Retries until valid input is
     * provided.
     */
    private static RationalNumber readRational() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter rational number (n d): ");
            try {
                int n = in.nextInt();
                int d = in.nextInt();
                return new RationalNumber(n, d);
            } catch (InputMismatchException e) {
                System.out.println("Integer input required. Try again.");
                in.nextLine(); // clear invalid input
            } catch (ArithmeticException e) {
                System.out.println("Denominator cannot be zero. Try again.");
            }
        }
    }
}
