package chap06.sect2;

/**
 * Approximates the value of π using the Leibniz series: π = 4 - 4/3 + 4/5 - 4/7 + 4/9 - ...
 *
 * @author Drue Coles
 */
public class PiApproximator {

    public static void main(String[] args) {
        final int n = 100_000_000; // number of terms of the series to add
        double piApprox = 0.0; // sum of terms
        int denominator = 1; // denominator of current term
        int sign = 1;  // sign of current term

        // compute the n-th partial sum (the sum of the first n terms)
        for (int i = 0; i < n; i++) {
            piApprox += sign * 4.0 / denominator; // add next term
            denominator += 2;
            sign = -sign;
        }

        // A double-precision floating-point number has about 16 decimal digits of precision, but
        // the last digit may be unreliable due to binary rounding, so 15 is specified.
        String label1 = String.format("Leibniz series approximation (%,d terms)", n);
        String label2 = "Double-precision floating-point value nearest to π";
        System.out.printf("%50s: %.15f%n", label1, piApprox);
        System.out.printf("%50s: %.15f%n", label2, Math.PI);
    }
}
