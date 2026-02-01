package chap09.sect2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Test program for the SortedList class.
 *
 * @author Drue Coles
 */
public class SortedListTester {

    public static void main(String[] args) {

        SortedList sortedList = new SortedList();       
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        final int numLines = 10;
        for (int i = 0; i < numLines; i++) {
            // random coordinates for two endpoints
            double a = rand.nextDouble(0, 10);
            double b = rand.nextDouble(0, 10);
            double u = rand.nextDouble(0, 10);
            double v = rand.nextDouble(0, 10);
            sortedList.add(new LineSegment(a, b, u, v));
        }

        // verify order
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println(sortedList.get(i));
        }
        System.out.println();
        
        // insert 3 network users into empty list
        sortedList = new SortedList();
        NetworkUser n1 = new NetworkUser("washington", "usa123");
        NetworkUser n2 = new NetworkUser("adams", "july4hello");
        NetworkUser n3 = new NetworkUser("jefferson", "virginia1743");
        NetworkUser n4 = new NetworkUser("madison", "password");
        sortedList.add(n1);
        sortedList.add(n2);
        sortedList.add(n3);
        sortedList.add(n4);

        // verify order
        for (int i = 0; i < sortedList.size(); i++) {
            NetworkUser n = (NetworkUser) sortedList.get(i);
            System.out.println(n.getLogin());
        }
        System.out.println();
    }
}
