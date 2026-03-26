package chap09.sect6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *  Displays the time elapsed on Earth given the time measured on a spaceship moving at constant
 *  velocity relative to Earth.
 *
 * @author Drue Coles
 */
public class TimeDilationCalculator extends Application {

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        final int width = 450;
        final int height = 125;
        Scene scene = new Scene(root, width, height);

        // configure grid gaps and alignment
        final int hvGap = 10;
        root.setVgap(hvGap);
        root.setHgap(hvGap);
        root.setAlignment(Pos.CENTER);
        root.requestFocus();

        // style strings for result label
        String base = "-fx-font-size: 16px; -fx-font-weight:bold;";
        String normal = base + "-fx-text-fill: #444444";
        String error = base + "-fx-text-fill: #aa0000";

        // create and initialize nodes to be added to scene
        final double defaultVelocity = 0.95;
        Label vLabel = new Label("Velocity as a fraction of light speed");
        TextField vTextField = new TextField(String.valueOf(defaultVelocity));

        final double defaultTime = 10;
        Label tLabel = new Label("Travel time in years");
        TextField tTextField = new TextField(String.valueOf(defaultTime));

        String initLabelText = getElapsedTime(defaultVelocity, defaultTime);
        Label resultLabel = new Label(initLabelText);
        resultLabel.setStyle(normal);

        Button button = new Button("Elapsed Time on Earth");

        // place nodes in grid pane
        root.add(vLabel, 0, 0);
        root.add(vTextField, 1, 0);
        root.add(tLabel, 0, 1);
        root.add(tTextField, 1, 1);
        root.add(button, 0, 2);
        root.add(resultLabel, 1, 2);

        // handles button clicks by reading fields and updating result label
        class ButtonHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent event) {
                String vText = vTextField.getText();
                String tText = tTextField.getText();

                // check for empty fields
                if (vText.isEmpty() || tText.isEmpty()) {
                    setLabel(resultLabel, error, "MISSING INPUT");
                    return;
                }

                // exception thrown if input cannot be parsed as double
                double v = Double.parseDouble(vText);
                double t = Double.parseDouble(tText);

                // check for invalid input in the text fields
                if (t < 0 || v < 0 || v >= 1) {
                    setLabel(resultLabel, error, "INVALID INPUT");
                    return;
                }

                // input is valid, so update result label
                setLabel(resultLabel, normal, getElapsedTime(v, t));
            }
        }
        button.setOnAction(new ButtonHandler());

        stage.setTitle("Time Dilation Calculator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calculates the time elapsed on Earth from the time measured on a spaceship moving at constant
     * velocity relative to Earth, using a formula from Einstein's Special Theory of Relativity.
     *
     * @param v the velocity of the spaceship as a fraction of light speed (0 ≤ v < 1)
     * @param u the time measured on the ship in years
     * @return a formatted string giving the time elapsed on Earth
     */
    private static String getElapsedTime(double v, double u) {
        double elapsedTimeOnEarth = Math.sqrt(u * u / (1 - v * v));
        return String.format("%.2f years", elapsedTimeOnEarth);
    }

    /**
     * Sets a label's style and text.
     */
    private static void setLabel(Label label, String style, String text) {
        label.setStyle(style);
        label.setText(text);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
