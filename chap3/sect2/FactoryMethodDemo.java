package chap3.sect2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

/**
 * Demonstrates the use of factory methods to create Currency and LocalDate objects.
 *
 * @author Drue Coles
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        // display four currency symbols
        String eur = Currency.getInstance("EUR").getSymbol(); // euro
        String gbp = Currency.getInstance("GBP").getSymbol(); // pound
        String jpy = Currency.getInstance("JPY").getSymbol(); // yen
        String usd = Currency.getInstance("USD").getSymbol(); // dollar
        System.out.printf("Currency symbols: %s %s %s %s %n", eur, gbp, jpy, usd);

        // display a historical date and today's date
        LocalDate apollo11 = LocalDate.of(1969, 7, 20);
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEEE, MMMM d, uuuu");
        System.out.printf("First moon landing: %s%n", apollo11.format(fmt));
        System.out.printf("Today: %s%n", today.format(fmt));
    }
}
