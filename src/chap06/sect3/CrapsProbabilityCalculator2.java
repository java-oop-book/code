package chap06.sect3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Estimates by Monte Carlo simulation the probability of winning the game of Craps. The program
 * also outputs the expected number of rolls per game and greatest number of rolls observed.
 *
 * @author Drue Coles
 */
public class CrapsProbabilityCalculator2 {

    public static void main(String[] args) {
        final int numGames = 100_000_000;
        int wins = 0;
        int rolls = 0;  // total across all games
        int maxNumRolls = 0; // maximum in a single game

        System.out.println("Simulating the game of Craps...");
        for (int i = 0; i < numGames; i++) {
            int result = playCraps();
            if (result > 0) {
                wins++;
            }
            int positiveResult = Math.abs(result);
            rolls += positiveResult;
            maxNumRolls = Math.max(maxNumRolls, positiveResult);
        }

        double probability = (double) wins / numGames;
        double expectedLength = (double) rolls / numGames;
        System.out.printf("Games played: %,d %n", numGames);
        System.out.printf("Estimated probability of winning: %.4f %n", probability);
        System.out.printf("Expected number of rolls per game: %.3f %n", expectedLength);
        System.out.printf("Greatest number of rolls: %d %n", maxNumRolls);
    }

    /**
     * Plays the game of Craps.
     *
     * @return number of rolls (positive = player wins, negative = player loses)
     */
    private static int playCraps() {
        final int comeOutRoll = rollDice();

        if (comeOutRoll == 7 || comeOutRoll == 11) {
            return 1; // positive for winning
        }
        if (comeOutRoll == 2 || comeOutRoll == 3 || comeOutRoll == 12) {
            return -1; // negative for losing
        }

        int numRolls = 1;
        do {
            int roll = rollDice();
            numRolls++;
            if (roll == comeOutRoll) { // player wins
                return numRolls;
            }
            if (roll == 7) { // player loses
                return -numRolls;
            }
        } while (true);
    }

    /**
     * Rolls a pair of dice.
     *
     * @return the sum of the numbers rolled
     */
    private static int rollDice() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int die1 = rand.nextInt(1, 7);
        int die2 = rand.nextInt(1, 7);
        return die1 + die2;
    }
}
