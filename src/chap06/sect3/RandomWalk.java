package chap06.sect3;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Estimates by Monte Carlo simulation the expected number of steps for a random walk that starts
 * at the center of a circle of given radius and continues until it exits the circle.
 *
 * @author Drue Coles
 */
public class RandomWalk {

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter radius of circle: ");
      int radius = in.nextInt();
      final int numWalks = 1000;
      int numSteps = 0; // total over all walks

      // monte carlo simulation
      for (int i = 0; i < numWalks; i++) {
         numSteps += randomWalk(radius);
      }

      double expectedSteps = (double) numSteps / numWalks;
      System.out.printf("Expected length of random walk: %.2f steps.%n", expectedSteps);
   }

   /**
    * Simulates a random walk that starts at the center of a circle of given radius and continues
    * until it exits the circle.
    *
    * @return the number of steps
    */
   private static int randomWalk(int radius) {
      int steps = 0;
      int x = 0; // x-coordinate of current position
      int y = 0; // y-coordinate of current position

      ThreadLocalRandom rand = ThreadLocalRandom.current();

      // take random steps while the point remains in the circle
      while (x * x + y * y <= radius * radius) {
         switch (rand.nextInt(4)) { // move one unit in a random cardinal direction
            case 0 -> x++;
            case 1 -> x--;
            case 2 -> y++;
            case 3 -> y--;
         }
         steps++;
      }

      return steps;
   }
}
