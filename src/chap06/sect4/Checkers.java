package chap06.sect4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Draws a grid of black and red checkers.
 *
 * @author Drue Coles
 */
public class Checkers extends Application {

    @Override
    public void start(Stage stage) {
        final int size = 300;

        final int numRows = 8;
        final int numCols = 8;
        final int diameter = size / numRows;
        final int radius = diameter / 2;

        Pane root = new Pane();
        Scene scene = new Scene(root, size, size);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // pixel coordinates of circle in position (row, col)
                int x = radius + col * diameter;
                int y = radius + row * diameter;

                Color color = (row % 2 == col % 2 ? Color.BLACK : Color.RED);
                Circle circle = new Circle(x, y, radius - 1, color);
                root.getChildren().add(circle);
            }
        }

        stage.setTitle("Checkers");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
