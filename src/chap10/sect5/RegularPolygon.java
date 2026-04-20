package chap10.sect5;

import javafx.scene.shape.Polygon;

/**
 * A regular polygon.
 *
 * @author Drue Coles
 */
public class RegularPolygon extends Polygon {
    private final double x;
    private final double y;
    private final double radius;
    private final int sides;

    /**
     * Constructs a regular polygon centered at (x, y).
     *
     * @param radius distance from center to corners
     * @param sides the number of sides
     */
    public RegularPolygon(int x, int y, int radius, int sides) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.sides = sides;
        setCorners();
    }

    /**
     * Calculates the corner points for this regular polygon.
     */
    private void setCorners() {
        double centralAngle = 360.0 / sides;
        for (int i = 0; i < sides; i++) {
            // location (a, b) of the i-th corner.
            double angle = Math.toRadians(i * centralAngle);
            double a = x + radius * Math.cos(angle);
            double b = y - radius * Math.sin(angle);
            getPoints().addAll(a, b);
        }
    }
}
