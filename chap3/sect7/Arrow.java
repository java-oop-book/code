package chap3.sect7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * Displays an arrow in a circle of light. Sizes and coordinates are expressed relative to the size
 * of the scene, so the arrow scales correctly if the size is changed.
 *
 * @author Drue Coles
 */
public class Arrow extends Application {

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        final int sceneSize = 300;
        Scene scene = new Scene(root, sceneSize, sceneSize, Color.BLACK);

        // center of the scene
        final double centerX = sceneSize / 2.0;
        final double centerY = sceneSize / 2.0;

        // circle of light
        final double padding = 5.0;
        final double radius = sceneSize / 2.0 - padding;
        final Circle circle = new Circle(centerX, centerY, radius, Color.YELLOW);

        // arrow dimensions
        final double tipWidth = sceneSize / 2.0;
        final double shaftWidth = sceneSize / 5.0;
        final double shaftHeight = sceneSize / 2.0 - 4 * padding;

        // arrow tip: an isosceles triangle centered horizontally with apex at the top
        final Polygon arrowTip = new Polygon(
                centerX, 3 * padding,             // apex coordinates
                centerX - tipWidth / 2, centerY,  // base left coordinates
                centerX + tipWidth / 2, centerY   // base right coordinates
        );

        // arrow shaft: a vertical rectangle centered horizontally below the tip
        final double topLeftX = centerX - shaftWidth / 2;
        final double topLeftY = centerY;
        final Rectangle arrowShaft = new Rectangle(topLeftX, topLeftY, shaftWidth, shaftHeight);

        // combine tip and shaft into a single shape
        final Shape arrow = Shape.union(arrowTip, arrowShaft);
        arrow.setFill(Color.GREEN);
        arrow.setStroke(Color.BLACK);

        final double strokeWidth = 6.0;
        final double rotationAngle = 45.0;
        arrow.setStrokeWidth(strokeWidth);
        arrow.setRotate(rotationAngle); // rotates arrow around its center

        root.getChildren().addAll(circle, arrow);

        stage.setTitle("Arrow");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
