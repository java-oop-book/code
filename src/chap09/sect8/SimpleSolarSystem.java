package chap09.sect8;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Displays a top-down view of a simple solar system: a single planet moves in a perfectly circular
 * orbit around its sun. Clicking the mouse causes the planet to reverse direction.
 *
 * @author Drue Coles
 */
public class SimpleSolarSystem extends Application {

   @Override
   public void start(Stage stage) {
      StackPane root = new StackPane();
      final int size = 300;
      Scene scene = new Scene(root, size, size, Color.BLACK);

      Circle planet = new Circle(5, Color.AQUA);
      Circle sun = new Circle(size / 6.0, Color.GOLD);

      // Create a circle representing the planet's orbital path. PathTransition requires the path
      // node to have a fill or stroke. Using Color.TRANSPARENT hides the orbit path while keeping
      // it a valid path for the transition.
      Circle orbit = new Circle(size / 2.0 - 30, Color.TRANSPARENT);

      // Node layering is important: orbit first so planet moves along it, planet above orbit, sun
      // on top so it covers the center.
      root.getChildren().addAll(orbit, planet, sun);

      // path transition for animating the planet clockwise around the sun
      PathTransition pTrans = new PathTransition();
      pTrans.setDuration(Duration.millis(3000));
      pTrans.setPath(orbit);
      pTrans.setNode(planet);
      pTrans.setInterpolator(Interpolator.LINEAR);
      pTrans.setCycleCount(Timeline.INDEFINITE);
      pTrans.setAutoReverse(false);
      pTrans.play();

      // Implementation note: PathTransition expects a Path node. Using a Circle works, but it is
      // interpreted as a path, which can introduce small distortions. For this reason, the planet
      // may appear slightly elongated as it moves.

      // clicking the mouse changes direction of planet (by negating its rate)
      root.setOnMouseClicked(e -> pTrans.setRate(-pTrans.getRate()));

      stage.setTitle("Simple Solar System");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
