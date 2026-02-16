package chap06.sect1;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Prompts the user for an initial balance and simulates the gambler's ruin: the gambler wins a
 * dollar for heads and loses a dollar for tails, and continues flipping until going broke. The
 * program outputs the number of coin flips and the maximum balance.
 *
 * @author Drue Coles
 */
public class GamblersRuin {

    public static void main(String[] args) {
        System.out.print("Enter initial balance: $");
        Scanner in = new Scanner(System.in);
        int balance = in.nextInt();
        int max = balance;
        int coinFlips = 0;

        // simulate coin flips until balance reaches 0
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (balance > 0) {
            if (rand.nextBoolean()) {
                balance++;
            } else {
                balance--;
            }
            coinFlips++;
            
            if (balance > max) {
                max = balance;
            }
        }

        System.out.printf("Number of flips: %,d %n", coinFlips);
        System.out.printf("Maximum balance: $%,d %n", max);
    }
}
