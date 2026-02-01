package chap05.sect5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Prompts the user for a future date and outputs the number of intervening days.
 *
 * @author Drue Coles
 */
public class FutureDate {

   public static void main(String[] args) {
      System.out.print("Enter a future date (MM DD YYYY): ");
      Scanner in = new Scanner(System.in);
      int month = in.nextInt();
      int day = in.nextInt();
      int year = in.nextInt();

      LocalDate futureDate = LocalDate.of(year, month, day);
      LocalDate today = LocalDate.now();

      if (!futureDate.isAfter(today)) {
         System.out.println("That is not a future date. Goodbye.");
         return;
      }

      long daysUntil = ChronoUnit.DAYS.between(today, futureDate);
      DateTimeFormatter f = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
      System.out.printf("%,d days until %s.%n", daysUntil, f.format(futureDate));
   }
}
