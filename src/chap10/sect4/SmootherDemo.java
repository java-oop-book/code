package chap10.sect4;

import chap10.sect3.DataSmoother;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Demonstrates upcasting, downcasting, runtime polymorphism, and the instanceof operator using data
 * smoothers.
 *
 * @author Drue Coles
 */
public class SmootherDemo {

    public static void main(String[] args) {
        double[] data = new double[10];
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        System.out.print("Original data: ");
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextDouble(10);
            System.out.printf("%.2f ", data[i]);
        }
        System.out.println();

        final int windowSize = 5;

        // upcast (from subtype to supertype) is implicit
        DataSmoother smoother = new AveragingSmoother(windowSize);
        System.out.print("Smoothed data: ");
        printSmoothedData(smoother, data);

        DataSmoother smoother2 = new RangeSmoother(windowSize, 2.5, 7.5);
        System.out.print("Smoothed data: ");
        printSmoothedData(smoother2, data);

        // This would not compile:
        //
        //      double[] range = smoother2.getRange();
        //
        // But smoother2 refers to a RangeSmoother, which has a getRange method, so what is wrong?

        DataSmoother smoother3 = (rand.nextBoolean() ? smoother : smoother2);

        // unsafe downcasting: potential class cast exception
        // RangeSmoother rangeSmoother = (RangeSmoother) smoother3;

        // safe downcasting with instanceof
        if (smoother3 instanceof RangeSmoother) {
            RangeSmoother rangeSmoother = (RangeSmoother) smoother3;
            System.out.printf("Range: %s %n", Arrays.toString(rangeSmoother.getRange()));
        }

        // safe downcasting with pattern variable and instanceof
        if (smoother3 instanceof RangeSmoother rangeSmoother) {
            System.out.printf("Range: %s %n", Arrays.toString(rangeSmoother.getRange()));
        }
    }

    /**
     * Uses a given data smoother to output a smoothed sequence of measurements.
     */
    private static void printSmoothedData(DataSmoother smoother, double[] data) {
        for (double x : data) {
            System.out.printf("%.2f ", smoother.addMeasurement(x)); // runtime polymorphism
        }
        System.out.println();
    }
}
