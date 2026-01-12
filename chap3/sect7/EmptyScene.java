package chap3.sect7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Displays an empty scene, illustrating the minimal structure of a JavaFX application.
 *
 * @author Drue Coles
 */
public class EmptyScene extends Application {

   @Override
   public void start(Stage stage) {
      // create scene graph's root node and attach it to a scene (boilerplate)
      Pane root = new Pane();
      Scene scene = new Scene(root, 500, 300);

      // application-specific code goes here

      // finalize window setup (boilerplate)
      stage.setTitle("An Empty Scene");
      stage.setScene(scene);
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
