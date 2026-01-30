package chap5.sect5;

import java.util.Scanner;

/**
 * Calculates a PASS/FAIL grade based on three exam scores. There are two possible ways to pass: the
 * average score is at least 80 and no score is less than 70, or the average of the two highest
 * scores is at least 90.
 *
 * @author Drue Coles
 */
public class PassOrFail {

    public static void main(String[] args) {
        System.out.print("Enter exam scores: ");
        Scanner in = new Scanner(System.in);
        int s1 = in.nextInt();
        int s2 = in.nextInt();
        int s3 = in.nextInt();

        if (isValid(s1) && isValid(s2) && isValid(s3)) {
            int min = Math.min(s1, Math.min(s2, s3));

            // average of all three scores and average of the two highest scores
            double avg3 = (s1 + s2 + s3) / 3.0;
            double avg2 = (s1 + s2 + s3 - min) / 2.0;

            if ((avg3 >= 80 && min >= 70) || avg2 >= 90) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            }
        } else {
            System.out.println("Invalid scores.");
        }
    }

    /**
     * Returns true if score is between 0 and 100 inclusive.
     */
    private static boolean isValid(int score) {
        return score >= 0 && score <= 100;
    }
}
