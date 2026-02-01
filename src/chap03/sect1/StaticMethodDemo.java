package chap03.sect1;

/**
 * Demonstrates the use of static String methods to construct an air route code from an airline,
 * origin, destination, and flight number.
 *
 * @author Drue Coles
 */
public class StaticMethodDemo {

    public static void main(String[] args) {
        String airline = "AY";
        String origin = "JFK";
        String destination = "HEL";
        int flight = 235;

        // manually concatenating components (less flexible)
        String routeCode = airline + "-" + origin + "-" + destination + "-" + flight;
        System.out.println(routeCode);

        // using static format method
        String formattedRoute = String.format("%s-%s-%s-%d", airline, origin, destination, flight);
        System.out.println(formattedRoute);

        // using static join method
        String joinedRoute = String.join("-", airline, origin, destination, String.valueOf(flight));
        System.out.println(joinedRoute);
    }
}
