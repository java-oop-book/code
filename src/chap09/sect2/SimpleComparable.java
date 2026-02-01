package chap09.sect2;

/**
 * Imposes a total ordering on the objects of any class that implements this interface.
 *
 * @author Drue Coles
 */
public interface SimpleComparable {

   /**
    * Compares this object with another object for order. Returns a negative integer, zero, or a
    * positive integer depending on whether this is less than, equal to, or greater than another.
    */
   int compareTo(Object o);
}
