package javabook.chap4.sect1;

import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/**
 * Draws five ovals, each with a random color and a random angle of rotation.
 *
 * @author Drue Coles
 */
public class RandomOvals extends Application {

   @Override
   public void start(Stage stage) {
      StackPane root = new StackPane();
      final int size = 300;
      Scene scene = new Scene(root, size, size, Color.BLACK);

      final int padding = 20; // space between oval and edge of scene
      final int ovalWidth = size - 2 * padding;
      final int ovalHeight = ovalWidth / 3;
     
      root.getChildren().add(randomOval(ovalWidth, ovalHeight));
      root.getChildren().add(randomOval(ovalWidth, ovalHeight));
      root.getChildren().add(randomOval(ovalWidth, ovalHeight));
      root.getChildren().add(randomOval(ovalWidth, ovalHeight));
      root.getChildren().add(randomOval(ovalWidth, ovalHeight));

      stage.setTitle("Random Ovals");
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Creates and returns an oval shape with a randomly assigned color and angle of rotation.
    */
   private static Ellipse randomOval(double width, double height) {
      // constructor expects horizontal/vertical radius, not full width/height
      Ellipse ellipse = new Ellipse(width / 2, height / 2);
      ellipse.setFill(null);
      ellipse.setStrokeWidth(4);
      ellipse.setStroke(randomColor());
      ellipse.setRotate(ThreadLocalRandom.current().nextInt(360));
      return ellipse;
   }
   
   /**
    * Creates and returns a random color.
    */
   private static Color randomColor() {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      double r = rand.nextDouble();
      double g = rand.nextDouble();
      double b = rand.nextDouble();

      // brightened for contrast with black background
      return new Color(r, g, b, 1).brighter().brighter();
   }   

   public static void main(String[] args) {
      launch(args);
   }
}
