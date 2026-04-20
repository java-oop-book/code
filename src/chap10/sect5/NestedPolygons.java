package chap10.sect5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Displays regular polygons centered in the viewing area. Each polygon is transparent with a
 * randomly colored border. There are controls for coloring the borders and making them dashed or
 * solid.
 *
 * @author Drue Coles
 */
public class NestedPolygons extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        // container with checkbox for dashed/solid borders
        Pane topPane = getPane();
        CheckBox checkBox = new CheckBox("Solid");
        checkBox.setSelected(true);
        topPane.getChildren().add(checkBox);
        root.setTop(topPane);

        // center pane has a rectangle for background fill and the nested polygons
        Pane centerPane = new Pane();
        final int width = 600;
        final int height = 600;
        final int padding = 10;

        // background rectangle (also holds the polygons)
        centerPane.setPrefSize(width, height);
        Rectangle rect = new Rectangle(padding, padding, width - 2 * padding, height - 2 * padding);

        centerPane.getChildren().add(rect);
        root.setCenter(centerPane);

        // create and add polygons to center pane
        int radius = 25;
        final int deltaRadius = 25;
        final int minSides = 3;
        final int maxSides = 12;
        for (int sides = minSides; sides <= maxSides; sides++) {
            RegularPolygon rp = new RegularPolygon(width / 2, height / 2, radius, sides);
            radius += deltaRadius;
            centerPane.getChildren().add(rp);
            rp.setStroke(randomColor());
            rp.setStrokeWidth(4);
            rp.setFill(null);
        }

        // bottom pane with color picker and button
        Pane bottomPane = getPane();
        ColorPicker colorPicker = new ColorPicker();
        Button button = new Button("Randomize");
        bottomPane.getChildren().addAll(colorPicker, button);
        root.setBottom(bottomPane);

        // iterate over all polygons and set solid/dashed borders
        checkBox.setOnAction(e -> {
            for (Node node : centerPane.getChildren()) {
                if (node instanceof RegularPolygon regularPolygon) {
                    if (checkBox.isSelected()) {
                        regularPolygon.getStrokeDashArray().clear(); // solid
                    } else {
                        regularPolygon.getStrokeDashArray().add(10.0); // dashed
                    }
                }
            }
        });

        // select color for all borders
        colorPicker.setOnAction(e -> {
            Color c = colorPicker.getValue();
            for (Node node : centerPane.getChildren()) {
                if (node instanceof RegularPolygon regularPolygon) {
                    regularPolygon.setStroke(c);
                }
            }
        });

        // randomize all border colors
        button.setOnAction(e -> {
            for (Node node : centerPane.getChildren()) {
                if (node instanceof RegularPolygon regularPolygon) {
                    regularPolygon.setStroke(randomColor());
                }
            }
        });

        // When the container is clicked, the border of each polygon is changed to its grayscale
        // equivalent.
        centerPane.setOnMouseClicked(e -> {
            for (Node node : centerPane.getChildren()) {
                if (node instanceof Polygon p) {
                    Color c = (Color) p.getStroke();
                    double brightness = c.getBrightness();
                    p.setStroke(Color.color(brightness, brightness, brightness));
                }
            }
        });

        stage.setTitle("Nested Polygons");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Configures and returns a flow pane.
     */
    private static Pane getPane() {
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 0, 10, 0));
        pane.setHgap(50);
        return pane;
    }

    /**
     * Creates and returns a color with random RGB components.
     */
    private Color randomColor() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        double r = rand.nextDouble();
        double g = rand.nextDouble();
        double b = rand.nextDouble();
        return Color.color(r, g, b);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
