package chap07.sect5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Displays a time-space diagram of a sequence of bits updated by Rule 90. At discrete steps, each
 * bit becomes the exclusive-or of its two neighbors. The initial configuration consists of a 1 in
 * the middle and 0s everywhere else. From this configuration, a structured pattern emerges over
 * time. (See https://en.wikipedia.org/wiki/Rule_90 for more on Rule 90. For alternative ways to
 * generate the same pattern, see https://en.wikipedia.org/wiki/Sierpiński_triangle.)
 *
 * @author Drue Coles
 */
public class Rule90 extends Application {

    @Override
    public void start(Stage stage) {
        final int width = 800;
        final int height = 385;
        Pane root = new Pane();
        Scene scene = new Scene(root, width, height, Color.LIGHTSTEELBLUE);

        // drawing constants
        final int radius = 3;
        final int diameter = 2 * radius;
        final int numCells = width / diameter;

        // Computes the space-time history of a sequence of bits evolving according to Rule 90.
        // Row k represents the sequence at time k. The sequence starts with a 1 in the center
        // and 0s elsewhere.
        int[][] grid = spaceTimeHistoryRule90(numCells);

        // display the grid
        for (int row = 0; row < numCells; row++) {
            for (int col = 0; col < numCells; col++) {
                if (grid[row][col] == 1) {
                    int x = diameter + col * diameter;
                    int y = diameter + row * diameter;
                    root.getChildren().add(new Circle(x, y, radius));
                }
            }
        }

        stage.setTitle("Rule 90");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates and returns an n x n array of bits, initializing the first row with a 1 in the center
     * and 0s everywhere else. Each subsequent row is obtained from the previous one by applying
     * Rule 90.
     */
    private static int[][] spaceTimeHistoryRule90(int n) {
        int[][] grid = new int[n][n];
        grid[0][n / 2] = 1; // initial condition: a single 1 in the center

        for (int i = 1; i < n; i++) { // fill rows 1, 2, ..., n-1
            for (int j = 0; j < n; j++) { // calculate the j-th bit in row i
                int left = get(grid, i - 1, j - 1);
                int right = get(grid, i - 1, j + 1);
                grid[i][j] = left ^ right; // Rule 90 (XOR)
            }
        }
        return grid;
    }

    /**
     * Returns grid[i][j] if j is in bounds, and 0 otherwise.
     */
    private static int get(int[][] grid, int i, int j) {
        if (j < 0 || j >= grid[i].length) {
            return 0;
        }
        return grid[i][j];
    }

    public static void main(String[] args) {
        launch(args);
    }
}
