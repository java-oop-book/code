package chap06.sect4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Draws a checkerboard.
 *
 * @author Drue Coles
 */
public class Checkerboard extends Application {

   @Override
   public void start(Stage stage) {
      final int size = 300;
      Pane root = new Pane();
      Scene scene = new Scene(root, size, size, Color.LIGHTGRAY);

      // drawing constants
      final int padding = 25;
      final int numSquares = 8; // squares per row and column
      final int squareSize = (size - 2 * padding) / numSquares; // size of squares
      final int boardSpan = numSquares * squareSize; // length of row or column

      // black board (red squares added later)
      Rectangle board = new Rectangle(padding, padding, boardSpan, boardSpan);
      board.setFill(Color.BLACK);
      addDropShadow(board);
      root.getChildren().add(board);

      // fill in red squares
      for (int row = 0; row < numSquares; row++) {
         for (int col = 0; col < numSquares; col++) {
            if (row % 2 != col % 2) { // if red position...
               // coordinates of top-left corner
               int x = padding + col * squareSize;
               int y = padding + row * squareSize;
               
               Rectangle redSquare = new Rectangle(x, y, squareSize, squareSize);
               redSquare.setFill(Color.FIREBRICK);
               root.getChildren().add(redSquare);
            }
         }
      }

      stage.setTitle("Checkerboard");
      stage.setScene(scene);
      stage.show();
   }

   /**
    * Adds a drop shadow to a given rectangle.
    */
   private static void addDropShadow(Rectangle rectangle) {
      DropShadow dropShadow = new DropShadow();
      dropShadow.setOffsetY(8);
      dropShadow.setOffsetX(8);
      dropShadow.setColor(Color.DARKSLATEGRAY);
      rectangle.setEffect(dropShadow);
   }

   public static void main(String[] args) {
      launch(args);
   }
}
