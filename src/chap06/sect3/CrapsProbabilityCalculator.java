package chap06.sect3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Estimates by Monte Carlo simulation the probability of winning the game of Craps.
 *
 * @author Drue Coles
 */
public class CrapsProbabilityCalculator {

    public static void main(String[] args) {
        final int numGames = 100_000_000;
        int wins = 0;

        System.out.printf("Simulating %,d trials of the game of Craps... %n", numGames);
        for (int i = 0; i < numGames; i++) {
            if (playerWins()) {
                wins++;
            }
        }

        double probability = (double) wins / numGames * 100;
        System.out.printf("Estimated probability of winning: %.3f %n", probability);
    }

    /**
     * Simulates the game of Craps.
     *
     * @return true if player wins, false otherwise
     */
    public static boolean playerWins() {
        final int comeOutRoll = rollDice();

        if (comeOutRoll == 7 || comeOutRoll == 11) {
            return true;
        }
        if (comeOutRoll == 2 || comeOutRoll == 3 || comeOutRoll == 12) {
            return false;
        }

        int roll = rollDice();
        while (roll != comeOutRoll && roll != 7) {
            roll = rollDice();
        }
        return roll == comeOutRoll;
    }

    /**
     * Simulates rolling a pair of dice.
     *
     * @return the sum of numbers rolled
     */
    public static int rollDice() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int die1 = rand.nextInt(1, 7);
        int die2 = rand.nextInt(1, 7);
        return die1 + die2;
    }
}
