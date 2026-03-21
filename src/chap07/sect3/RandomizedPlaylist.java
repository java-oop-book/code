package chap07.sect3;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Prompts the user for the number of songs in a randomized playlist and outputs the expected number
 * of songs that will be played until every song is heard at least once.
 *
 * @author Drue Coles
 */
public class RandomizedPlaylist {

   public static void main(String[] args) {
      System.out.print("Number of songs in the playlist: ");
      Scanner in = new Scanner(System.in);
      int n = in.nextInt();

      final int trials = 1_000_000;
      int total = 0;  // songs played in all trials

      // Monte Carlo simulation
      System.out.printf("Simulating %,d trials... %n", trials);
      for (int i = 0; i < trials; i++) {
         total += playSongs(n);
      }

      double result = (double) total / trials;
      System.out.printf("Expected number of songs until all are played: %.2f %n", result);
   }

   /**
    * Samples songs at random from a playlist until every song is heard at least once.
    *
    * @param n number of songs in the playlist
    * @return total number of songs played
    */
   private static int playSongs(int n) {
      boolean[] alreadyPlayed = new boolean[n];
      int totalSongsPlayed = 0;
      int distinctSongsPlayed = 0;

      while (distinctSongsPlayed < n) {
         int songIndex = ThreadLocalRandom.current().nextInt(n);
         totalSongsPlayed++;
         if (!alreadyPlayed[songIndex]) {
            distinctSongsPlayed++;
         }
         alreadyPlayed[songIndex] = true;
      }
      return totalSongsPlayed;
   }
}
