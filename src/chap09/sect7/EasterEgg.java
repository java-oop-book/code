package chap09.sect7;

import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/**
 * Displays an egg. Its shape and angle can be manipulated by key presses. Clicking on the egg or
 * background randomizes its color.
 *
 * @author Drue Coles
 */
public class EasterEgg extends Application {

   @Override
   public void start(Stage stage) {
      StackPane root = new StackPane();
      final int width = 500;
      final int height = 300;
      Scene scene = new Scene(root, width, height, Color.DARKGREEN);

      // create the egg and add it to root node
      final int hRadius = 170;
      final int vRadius = 100;
      final Ellipse egg = new Ellipse(hRadius, vRadius);
      egg.setFill(Color.HOTPINK);
      egg.setRotate(45);
      root.getChildren().add(egg);

      // handles key presses by rotating, shrinking, or stretching the egg
      class KeyHandler implements EventHandler<KeyEvent> {
         private static final int MIN_RADIUS = 10;
         private static final int DELTA_RADIUS = 2; // change in radius of egg
         private static final int DELTA_ANGLE = 10; // change in angle of rotation

         @Override
         public void handle(KeyEvent event) {
            final double radiusX = egg.getRadiusX();
            final double radiusY = egg.getRadiusY();
            final double angle = egg.getRotate();

            switch (event.getCode()) {
               // horizontal radius adjustments
               case LEFT  -> egg.setRadiusX(Math.max(MIN_RADIUS, radiusX - DELTA_RADIUS));
               case RIGHT -> egg.setRadiusX(radiusX + DELTA_RADIUS);

               // vertical radius adjustments
               case DOWN -> egg.setRadiusY(Math.max(MIN_RADIUS, radiusY - DELTA_RADIUS));
               case UP   -> egg.setRadiusY(radiusY + DELTA_RADIUS);

               // rotation
               case F11 -> egg.setRotate(angle - DELTA_ANGLE);
               case F12 -> egg.setRotate(angle + DELTA_ANGLE);

               // transform to circle
               case SPACE -> {
                  double n = Math.min(radiusX, radiusY);
                  egg.setRadiusX(n);
                  egg.setRadiusY(n);
               }
            }
         }
      }
      scene.setOnKeyPressed(new KeyHandler());

      // handles mouse clicks by randomizing background color of event source (egg or scene)
      class MouseHandler implements EventHandler<MouseEvent> {
         @Override
         public void handle(MouseEvent event) {
            if (event.getSource() == egg) {
               egg.setFill(randomColor());
               event.consume(); // prevent event dispatch to parent
            } else {
               scene.setFill(randomColor());
            }
         }
      }
      MouseHandler mouseHandler = new MouseHandler();
      scene.setOnMouseClicked(mouseHandler);
      egg.setOnMouseClicked(mouseHandler);

      stage.setTitle("Easter Egg");
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Returns a color with randomly generated RGB components.
    */
   private static Color randomColor() {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      return Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
   }

   public static void main(String[] args) {
      launch(args);
   }
}
