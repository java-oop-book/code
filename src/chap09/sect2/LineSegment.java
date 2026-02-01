package chap09.sect2;

/**
 * An immutable line segment.
 *
 * @author Drue Coles
 */
public class LineSegment implements SimpleComparable {

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
    * Returns a negative integer, zero, or a positive integer depending on whether this line's
    * length is less than, equal to, or greater than the length of another.
    */
   @Override
   public int compareTo(Object o) {
      LineSegment other = (LineSegment) o;
      return Double.compare(length(), other.length());
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
}
