package chap03.sect6;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Outputs the current date and the elapsed time since the user's birthdate.
 * 
 * @author Drue Coles
 */
public class DaysAlive {

   public static void main(String[] args) {
      // read user's birthdate
      Scanner in = new Scanner(System.in);
      System.out.print("Enter date of birth in MM DD YYYY format: ");
      int month = in.nextInt();
      int day = in.nextInt();
      int year = in.nextInt();
      LocalDate birthday = LocalDate.of(year, month, day);

      // output today's date
      LocalDate today = LocalDate.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
      System.out.printf("Today is %s. %n", today.format(formatter));

      // output current age
      Period period = Period.between(birthday, today);
      System.out.printf("You are %d years old. %n", period.getYears());

      // calculate total number of days since birth
      long daysAlive = ChronoUnit.DAYS.between(birthday, today);
      System.out.printf("You have been alive for %,d days. %n", daysAlive);
   }
}
