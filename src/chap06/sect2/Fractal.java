package chap06.sect2;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Draws a Sierpinski triangle.
 *
 * @author Drue Coles
 */
public class Fractal extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        final int size = 300;
        Scene scene = new Scene(root, size, size, Color.BLACK);

        // three corners of a triangle
        final int padding = 8;
        final Point2D corner1 = new Point2D(size / 2.0, padding);
        final Point2D corner2 = new Point2D(padding, size - padding);
        final Point2D corner3 = new Point2D(size - padding, size - padding);

        // fractal creation can begin at any point
        Point2D currentPoint = new Point2D(size / 2.0, size / 2.0);

        // 1. choose a random corner
        // 2. move halfway from current point toward that corner
        // 3. draw a dot at the new location
        final int numDots = 100_000;
        for (int i = 0; i < numDots; i++) {
            Point2D randomCorner = randomCorner(corner1, corner2, corner3);
            currentPoint = midpoint(currentPoint, randomCorner);

            // draw a dot at location of current point
            Circle dot = new Circle(currentPoint.getX(), currentPoint.getY(), 1, Color.GOLD);
            root.getChildren().add(dot);
        }

        stage.setTitle("Sierpinski Triangle");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns one of three given points selected at random.
     */
    private static Point2D randomCorner(Point2D a, Point2D b, Point2D c) {
        return switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0 -> a;
            case 1 -> b;
            default -> c;
        };
    }

    /**
     * Returns the midpoint of the line segment connecting p and q.
     */
    private static Point2D midpoint(Point2D p, Point2D q) {
        return new Point2D(
                (p.getX() + q.getX()) / 2,
                (p.getY() + q.getY()) / 2
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
