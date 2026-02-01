package chap09.sect8;

import java.util.concurrent.ThreadLocalRandom;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Displays a label with a floating-point number and a button for randomizing the number.
 *
 * @version 2 event handler is created as a named object of an anonymous class
 *
 * @author Drue Coles
 */
public class NumberServer2 extends Application {

   private final Label label = new Label();

   @Override
   public void start(Stage stage) {
      GridPane root = new GridPane();
      root.setPadding(new Insets(10));
      root.setHgap(10);
      Scene scene = new Scene(root);

      // add button and label to root node
      Button button = new Button("serve");
      root.add(button, 0, 0);
      root.add(label, 1, 0);
      randomizeLabel();

      // style button and label
      String style = "-fx-font-size:24; -fx-font-weight: bold; -fx-text-fill: #1f2f4f;";
      button.setStyle(style);
      label.setStyle(style);

      // handles button clicks by randomizing the displayed number
      EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            randomizeLabel();
         }
      };
      button.setOnAction(buttonHandler); // event handler is a named object of an anonymous class

      stage.setTitle("Number Server");
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Randomizes the label with a floating-point number.
    */
   private void randomizeLabel() {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      String num = String.format("%1.8f", rand.nextDouble());
      label.setText(num);
   }

   public static void main(String[] args) {
      launch(args);
   }
}
