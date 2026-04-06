package chap10.sect2;

/**
 * An immutable rational number.
 *
 * Class invariant:
 * - denominator is always positive
 * - numerator and denominator are in lowest terms
 *
 * @author Drue Coles
 */
public class RationalNumber extends Number implements Comparable<RationalNumber> {

    public static final RationalNumber ZERO = new RationalNumber(0);
    public static final RationalNumber ONE = new RationalNumber(1);
    public static final RationalNumber ONE_FOURTH = new RationalNumber(1, 4);
    public static final RationalNumber ONE_THIRD = new RationalNumber(1, 3);
    public static final RationalNumber ONE_HALF = new RationalNumber(1, 2);

    private final int numerator;
    private final int denominator;

    /**
     * Constructs a rational number with a specified numerator and denominator. Assumes that the
     * denominator is non-zero; behavior is undefined if this precondition is violated.
     *
     * @param numerator   an arbitrary integer
     * @param denominator a non-zero integer
     */
    public RationalNumber(int numerator, int denominator) {

        // The greatest common divisor of numerator and denominator will be factored out so that each
        // rational number has a unique representation.
        int gcd = gcd(numerator, denominator);

        // It is convenient to store the denominator as a positive integer, so if needed we can take
        // the additive inverse of both numerator and denominator.
        if (denominator < 0) {
            this.numerator = -numerator / gcd;
            this.denominator = -denominator / gcd;
        } else {
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }
    }

    /**
     * Constructs an integer rational number (denominator is 1).
     */
    public RationalNumber(int num) {
        this(num, 1);
    }

    /**
     * Returns the greatest common divisor of two integers.
     */
    private static int gcd(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        // Euclid's algorithm
        int a = Math.max(x, y);
        int b = Math.min(x, y);
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Returns the sum of this rational number and another.
     */
    public RationalNumber add(RationalNumber o) {
        int n = numerator * o.denominator + o.numerator * denominator;
        int d = denominator * o.denominator;
        return new RationalNumber(n, d);
    }

    /**
     * Returns the result of subtracting a rational number from this.
     */
    public RationalNumber subtract(RationalNumber o) {
        int n = numerator * o.denominator - o.numerator * denominator;
        int d = denominator * o.denominator;
        return new RationalNumber(n, d);
    }

    /**
     * Returns the product of this rational number and another.
     */
    public RationalNumber multiply(RationalNumber o) {
        int n = numerator * o.numerator;
        int d = denominator * o.denominator;
        return new RationalNumber(n, d);
    }

    /**
     * Returns the result of dividing this rational number by another.
     */
    public RationalNumber divide(RationalNumber o) {
        return multiply(o.inverse());
    }

    /**
     * Returns the multiplicative inverse of this. Assumes this is non-zero.
     */
    public RationalNumber inverse() {
        return new RationalNumber(denominator, numerator);
    }

    /**
     * Returns this rational number in standard format.
     */
    @Override
    public String toString() {
        if (numerator == 0) {
            return "0";
        }
        if (denominator == 1) {
            return "" + numerator;
        }

        return numerator + "/" + denominator;
    }

    /**
     * Returns true if this is numerically equal to another.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RationalNumber other = (RationalNumber) o;

        // No need to cross multiply since all objects are represented by a numerator and denominator
        // in lowest terms.
        return numerator == other.numerator && denominator == other.denominator;
    }

    /**
     * Returns hash code for this object.
     */
    @Override
    public int hashCode() {
        return 89 * numerator + denominator;
    }

    /**
     * Returns a negative number, zero, or a positive number depending on whether this rational
     * number is less than, equal to, or greater than another.
     */
    @Override
    public int compareTo(RationalNumber t) {
        int a = numerator * t.denominator;
        int b = denominator * t.numerator;
        return Integer.compare(a, b);
    }

    /**
     * Converts this rational number to an int, discarding any fractional part.
     */
    @Override
    public int intValue() {
        return numerator / denominator;
    }

    /**
     * Converts this rational number to a long, discarding any fractional part.
     */
    @Override
    public long longValue() {
        return (long) numerator / denominator;
    }

    /**
     * Converts this rational number to a float by performing floating-point division on the
     * numerator and denominator; the result is rounded to the nearest representable float.
     */
    @Override
    public float floatValue() {
        return (float) numerator / denominator;
    }

    /**
     * Converts this rational number to a double by performing double-precision floating-point
     * division on the numerator and denominator; the result is rounded to the nearest representable
     * double.
     */
    @Override
    public double doubleValue() {
        return (double) numerator / denominator;
    }
}
