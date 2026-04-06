package chap06.sect3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Approximates the value of π using a Monte Carlo simulation. A random point (x, y) is chosen from
 * the unit square. The probability that the point lies inside the quarter circle of radius 1 is
 * π/4. Therefore, π ≈ 4 × (fraction of points inside the quarter circle).
 *
 * @author Drue Coles
 */
public class PiApproximator {

    public static void main(String[] args) {
        final int numPoints = 100_000_000;
        int pointsInCircle = 0;

        ThreadLocalRandom rand = ThreadLocalRandom.current();
        System.out.printf("Generating %,d random points from the unit square... %n", numPoints);
        for (int i = 0; i < numPoints; i++) {
            // random point (x, y) in unit square
            double x = rand.nextDouble();
            double y = rand.nextDouble();

            if (x * x + y * y < 1) { // inside quarter circle
                pointsInCircle++;
            }
        }

        double piApprox = 4.0 * pointsInCircle / numPoints;

        // A double-precision floating-point number has about 16 decimal digits of precision, but
        // the last digit may be unreliable due to binary rounding, so 15 is specified.
        String label1 = "The value of π estimated by Monte Carlo simulation";
        String label2 = "Double-precision floating-point value nearest to π";
        System.out.printf("%s: %.15f %n", label1, piApprox);
        System.out.printf("%s: %.15f %n", label2, Math.PI);
    }
}
