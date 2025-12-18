package javabook.chap3.sect7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Displays a tricolor flag (three parallel stripes) with random colors.
 *
 * @author Drue Coles
 */
public class RandomTricolorFlag extends Application {

   @Override
   public void start(Stage stage) {
      Pane root = new Pane();
      Scene scene = new Scene(root); // scene size is determined by the root's contents

      // common flag dimensions (3:2 aspect ratio)
      final int width = 450;
      final int height = 300;

      // vertical stripes divide the flag evenly across its width
      final double stripeWidth = width / 3.0;
      final double stripeHeight = height;
      Rectangle stripe1 = new Rectangle(0, 0, stripeWidth, stripeHeight);
      Rectangle stripe2 = new Rectangle(stripeWidth, 0, stripeWidth, stripeHeight);
      Rectangle stripe3 = new Rectangle(2 * stripeWidth, 0, stripeWidth, stripeHeight);

      // random colors for the outer stripes, with the middle color interpolated
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      Color c1 = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
      Color c3 = Color.color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
      Color c2 = c1.interpolate(c3, 0.5);
      stripe1.setFill(c1);
      stripe2.setFill(c2);
      stripe3.setFill(c3);

      root.getChildren().addAll(stripe1, stripe2, stripe3);

      stage.setTitle("Random Tricolor Flag");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
