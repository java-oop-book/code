package chap06.sect3;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Estimates by Monte Carlo simulation the expected maximum number of people waiting for a massage
 * while a single therapist provides massages of fixed duration on a first-come first-served
 * basis to people arriving at random intervals.
 *
 * @author Drue Coles
 */
public class WaitingForMassage {

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("Time for each massage (in minutes): ");
      final int duration = in.nextInt();

      System.out.print("Average time between arrivals (in minutes): ");
      final int arrivalInterval = in.nextInt();

      System.out.print("Period of operation (in minutes): ");
      final int period = in.nextInt();
      System.out.println();

      final int trials = 1_000_000;
      int totalMaxWaiting = 0;

      System.out.printf("Simulating %,d operational periods... %n", trials);
      for (int i = 0; i < trials; i++) {
         totalMaxWaiting += simulateMassageQueue(duration, arrivalInterval, period);
      }

      double average = (double) totalMaxWaiting / trials;
      System.out.printf("Expected maximum number of people waiting: %.2f %n", average);
   }

   /**
    * Simulates one operational period.
    *
    * @param duration time in minutes for each massage
    * @param arrivalInterval average time in minutes between arrivals
    * @param period total operation time in minutes
    * @return maximum number of people waiting for a massage at one time
    */
   private static int simulateMassageQueue(int duration, int arrivalInterval, int period) {
      int peopleWaiting = 0;
      int maxPeopleWaiting = 0;
      int timeRemaining = 0; // time remaining for current massage
      ThreadLocalRandom rand = ThreadLocalRandom.current();

      // If there are n minutes on average between arrivals, then there is a 1/n chance of an
      // arrival during any given minute.
      final double arrivalProbability = 1.0 / arrivalInterval;

      // simulate minute-by-minute operation
      for (int t = 0; t < period; t++) {
         // deduct one minute from time remaining for current massage
         if (timeRemaining > 0) {
            timeRemaining--;
         }

         // start new massage if none is in progress and somebody is waiting
         if (timeRemaining == 0 && peopleWaiting > 0) {
            peopleWaiting--;
            timeRemaining = duration;
         }

         // check for new arrival
         if (rand.nextDouble() < arrivalProbability) {
            peopleWaiting++;
            maxPeopleWaiting = Math.max(peopleWaiting, maxPeopleWaiting);
         }
      }
      return maxPeopleWaiting;
   }
}
