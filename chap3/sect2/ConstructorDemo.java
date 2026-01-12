package chap3.sect2;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Properties;

/**
 * Demonstrates the use of the new operator to create objects and constructors to initialize them.
 *
 * @author Drue Coles
 */
public class ConstructorDemo {

    public static void main(String[] args) {
        BigInteger val = new BigInteger("100000000001");
        System.out.println(val.pow(2));

        BitSet b1 = new BitSet(10);
        b1.set(1);     // -1--------
        b1.set(5, 9);  // -1---1111-
        b1.flip(3, 7); // -1-11--11-
        System.out.println(b1);

        Properties creature = new Properties();
        creature.setProperty("common name", "quoll");
        creature.setProperty("kingdom", "Animalia");
        creature.setProperty("phylum", "Chordata");
        creature.setProperty("class", "Mammalia");
        System.out.println(creature.getProperty("common name"));
    }
}
