package chap09.sect1;

/**
 * An immutable line segment.
 *
 * @author Drue Coles
 */
public class LineSegment {

   // coordinates of endpoints
   private final double x1;
   private final double y1;
   private final double x2;
   private final double y2;

   /**
    * Constructs a line segment with endpoints (x1, y1) and (x2, y2).
    */
   public LineSegment(double x1, double y1, double x2, double y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
   }

   /**
    * Returns the length of this line segment.
    */
   public double length() {
      double dx = x2 - x1;
      double dy = y2 - y1;
      return Math.sqrt(dx * dx + dy * dy);
   }

   /**
    * Returns the slope of this line segment.
    */
   public double slope() {
      return (y2 - y1) / (x2 - x1);
   }

   /**
    * Returns a description of this line segment.
    */
   @Override
   public String toString() {
      String endPoint1 = String.format("(%.2f, %.2f)", x1, y1); 
      String endPoint2 = String.format("(%.2f, %.2f)", x2, y2); 
      String length = String.format("%.2f", length()); 
      String slope = String.format("%.2f", slope());
      String formatString = "[%s; %s; length = %s; slope = %s]";
      return String.format(formatString, endPoint1, endPoint2, length, slope);
   }

   /**
    * Test program to illustrate conversion of a line segment to a string and treatment of a line
    * segment with infinite slope.
    */
   public static void main(String[] args) {
      LineSegment s = new LineSegment(2.3, 5.8, 3.1, 9.7);
      System.out.println(s);

      // a vertical line segment has infinite slope
      LineSegment t = new LineSegment(2.3, 5.8, 2.3, 9.7);
      System.out.println(t);
   }
}
