package chap08.sect4;

import java.util.Scanner;

/**
 * A console-based front end for the Game of Fifteen.
 *
 * @author Drue Coles
 */
public class FifteenPlayer {

   public static void main(String[] args) {
      System.out.println("""
          ***********************
          *** Game of Fifteen ***
          ***********************
          """);

      Scanner in = new Scanner(System.in);
      FifteenGame game = new FifteenGame(3); // easy game for testing
      int tile;
      
      do {
         System.out.println(game);
         System.out.print("Tile to move (0 to quit): ");
         tile = in.nextInt();
         System.out.println();

         boolean successfulMove = game.slide(tile);
         if (tile > 0 && !successfulMove) {
            System.out.println("Invalid move. Try again.\n");
         }
      } while (tile > 0 && !game.over());

      if (game.over()) {
         System.out.println("YOU WIN!");
      } else {
         System.out.println("QUITTERS NEVER WIN!");
      }
   }
}
