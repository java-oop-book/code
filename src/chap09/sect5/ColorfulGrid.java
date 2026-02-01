package chap09.sect5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static javafx.scene.paint.Color.AQUAMARINE;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.DARKSLATEGRAY;
import static javafx.scene.paint.Color.DEEPPINK;
import static javafx.scene.paint.Color.DODGERBLUE;
import static javafx.scene.paint.Color.GOLD;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.GREENYELLOW;
import static javafx.scene.paint.Color.INDIGO;
import static javafx.scene.paint.Color.LIME;
import static javafx.scene.paint.Color.MAGENTA;
import static javafx.scene.paint.Color.NAVY;
import static javafx.scene.paint.Color.ORANGE;
import static javafx.scene.paint.Color.PLUM;
import static javafx.scene.paint.Color.POWDERBLUE;
import static javafx.scene.paint.Color.PURPLE;
import static javafx.scene.paint.Color.STEELBLUE;
import static javafx.scene.paint.Color.TEAL;
import static javafx.scene.paint.Color.THISTLE;
import static javafx.scene.paint.Color.TOMATO;
import static javafx.scene.paint.Color.TURQUOISE;
import static javafx.scene.paint.Color.YELLOW;

/**
 * Each cell of a square grid is filled with a unique color selected at random from a small list of
 * possible colors.
 *
 * @author Drue Coles
 */
public class ColorfulGrid extends Application {

    private static final Color[] colors = {
        AQUAMARINE, DARKSLATEGRAY, DEEPPINK, DODGERBLUE, GOLD, GREEN, GREENYELLOW, INDIGO, LIME,
        MAGENTA, NAVY, ORANGE, PLUM, POWDERBLUE, PURPLE, STEELBLUE, TEAL, THISTLE, TOMATO,
        TURQUOISE, YELLOW
    };

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        final int size = 300;
        Scene scene = new Scene(root, size, size, BLACK);

        final int n = 3; // number of rows and columns
        final float padding = 5;
        final float cellSize = (size - (n + 1) * padding) / n;

        Set<Color> setOfColors = getColors(n * n);
        if (setOfColors == null) {
            showAlertIfNotEnoughColors();
            return;
        }

        Color[] arrayOfColors = setOfColors.toArray(new Color[0]);

        // draw n x n grid of square cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // top-left corner of cell (i, j)
                float x = padding + j * (padding + cellSize);
                float y = padding + i * (padding + cellSize);
                Rectangle cell = new Rectangle(x, y, cellSize, cellSize);

                cell.setFill(arrayOfColors[i * n + j]);
                root.getChildren().add(cell);
            }
        }

        stage.setTitle("Colorful Grid");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns a set of distinct colors selected at random from the colors array of this class.
     * Returns null if there are not enough colors in the array.
     *
     * @param n size of set to be returned
     */
    private static Set<Color> getColors(int n) {
        if (n > colors.length) {
            return null; // not enough colors
        }

        Set<Color> set = new HashSet<>();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        while (set.size() < n) {
            int i = rand.nextInt(colors.length);
            set.add(colors[i]);
        }
        return set;
    }

    /**
     * Displays an error message in an alert box.
     */
    private static void showAlertIfNotEnoughColors() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Not enough colors");
        alert.setContentText("Terminating");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
