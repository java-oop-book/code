package chap09.sect1;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A list of line segments stored in ascending order by length.
 *
 * @author Drue Coles
 */
public class SortedListOfLineSegments {

   private final ArrayList<LineSegment> lines = new ArrayList<>();

   /**
    * Returns the size of this list.
    */
   public int size() {
      return lines.size();
   }

   /**
    * Returns the line segment at a given position in this list.
    */
   public LineSegment get(int i) {
      return lines.get(i);
   }

   /**
    * Adds a line segment at its correct position in length order.
    */
   public void add(LineSegment ls) {
      int insertionPoint = 0;
      double length = ls.length();

      while (insertionPoint < size()) {
         double otherLength = get(insertionPoint).length();
         if (length <= otherLength) {
            break;
         }
         insertionPoint++;
      }

      lines.add(insertionPoint, ls);
   }

   /**
    * Test program that adds ten line segments with random endpoints to a list and then traverses
    * the list to verify that the objects are stored in length order.
    */
   public static void main(String[] args) {
      SortedListOfLineSegments list = new SortedListOfLineSegments();
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      final int numSegments = 10;
      for (int i = 0; i < numSegments; i++) {
         // random coordinates for two endpoints
         double a = rand.nextDouble(0, 10);
         double b = rand.nextDouble(0, 10);
         double u = rand.nextDouble(0, 10);
         double v = rand.nextDouble(0, 10);
         list.add(new LineSegment(a, b, u, v));
      }

      // verify length order
      for (int i = 0; i < list.size(); i++) {
         System.out.println(list.get(i));
      }
   }
}
