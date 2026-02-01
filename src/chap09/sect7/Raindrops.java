package chap09.sect7;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.DARKGREEN;
import static javafx.scene.paint.Color.PALEGREEN;
import static javafx.scene.paint.Color.SEAGREEN;

/**
 * Displays raindrops rendered by small circles filled with a radial gradient. The user adds
 * raindrops by clicking the mouse. Clicking inside an existing raindrop selects it. The selected
 * raindrop can be dragged with the mouse or deleted by pressing the Backspace or Delete key.
 *
 * @author Drue Coles
 */
public class Raindrops extends Application {

    private Circle selectedRaindrop; // null if no circle is selected

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        final int width = 500;
        final int height = 300;
        Scene scene = new Scene(root, width, height, BLACK);

        // handles mouse clicks by making selected raindrop opaque and all others semitransparent,
        // or creating a new raindrop if an existing one was not selected
        class MouseClickHandler implements EventHandler<MouseEvent> {
            @Override
            public void handle(MouseEvent event) {
                boolean foundInside = false;
                selectedRaindrop = null;

                // check if click occurred inside an existing raindrop
                double x = event.getX();
                double y = event.getY();
                for (Node node : root.getChildren()) {
                    if (node.contains(x, y)) {
                        node.setOpacity(1.0);
                        selectedRaindrop = (Circle) node;
                        foundInside = true;
                    } else {
                        node.setOpacity(0.25); // unselected raindrops are semitransparent
                    }
                }

                // if not inside an existing raindrop, create a new one
                if (!foundInside) {
                    final int minRadius = 20;
                    final int maxRadius = 100;
                    int radius = ThreadLocalRandom.current().nextInt(minRadius, maxRadius + 1);

                    Circle raindrop = getRaindrop(x, y, radius);
                    root.getChildren().add(raindrop);
                    selectedRaindrop = raindrop;
                }
            }
        }
        root.setOnMouseClicked(new MouseClickHandler());

        // handles Delete or Backspace key press by deleting selected raindrop
        class KeyHandler implements EventHandler<KeyEvent> {
            @Override
            public void handle(KeyEvent event) {
                if (selectedRaindrop != null) {
                    KeyCode code = event.getCode();
                    if (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE) {
                        root.getChildren().remove(selectedRaindrop);
                        selectedRaindrop = null;
                    }
                }
            }
        }
        scene.setOnKeyPressed(new KeyHandler());

        stage.setTitle("Raindrops");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns a circle with a radial gradient fill pattern and a mouse-drag handler.
     */
    private static Circle getRaindrop(double x, double y, double r) {
        Circle raindrop = new Circle(x, y, r);

        // colors used in radial gradient, from center outward
        Color[] colors = {DARKGREEN, SEAGREEN, PALEGREEN};

        // each stop pairs a color with a normalized distance (0.0–1.0) from center of gradient
        Stop[] stops = new Stop[3];
        for (int i = 0; i < stops.length; i++) {
            double pos = (i + 1) / 3.0; // positions evenly spaced at 1/3, 2/3, and 1.0 of radius
            stops[i] = new Stop(pos, colors[i]);
        }

        // center-based radial gradient using absolute coordinates
        CycleMethod cm = CycleMethod.NO_CYCLE;
        raindrop.setFill(new RadialGradient(0, 0, x, y, r, false, cm, stops));

        // handles mouse drag events for new raindrop
        class DragHandler implements EventHandler<MouseEvent> {
            @Override
            public void handle(MouseEvent event) {
                double mx = event.getX();
                double my = event.getY();
                raindrop.setCenterX(mx);
                raindrop.setCenterY(my);

                // gradient is recomputed during dragging so it remains within raindrop as it moves
                raindrop.setFill(new RadialGradient(0, 0, mx, my, r, false, cm, stops));
            }
        }
        raindrop.setOnMouseDragged(new DragHandler());

        return raindrop;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
