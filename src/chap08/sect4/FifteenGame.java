package chap08.sect4;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Back end for the Game of Fifteen. The game consists of a 4 x 4 grid with 15 numbered tiles and
 * an empty space. A move in the game consists of sliding a tile into the empty space. The goal is
 * to rearrange the tiles into ascending order with the empty space in the lower-right corner.
 *
 * @author Drue Coles
 */
public class FifteenGame {

   public static final int ROWS = 4;
   public static final int COLS = 4;
   public static final int EMPTY_SPACE = 0;
   private static final int DEFAULT_SHUFFLE_COUNT = 99;

   private final int[][] grid;

   /**
    * Initializes the game to a solvable configuration by performing a default number of random tile
    * moves starting from the winning configuration.
    */
   public FifteenGame() {
      this(DEFAULT_SHUFFLE_COUNT);
   }

   /**
    * Initializes the game to a solvable configuration by performing a given number of random tile
    * moves starting from the winning configuration.
    *
    * @param n the number of random moves to apply
    */
   public FifteenGame(int n) {
      grid = new int[ROWS][COLS];

      // initialize grid to winning configuration
      int tile = 1;
      for (int[] row : grid) {
         for (int j = 0; j < COLS; j++) {
            row[j] = tile++;
         }
      }
      grid[ROWS - 1][COLS - 1] = EMPTY_SPACE;

      shuffle(n);
   }

   /**
    * Shuffles the grid by making n random moves.
    */
   private void shuffle(int n) {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      while (n > 0) {
         int tileToMove = rand.nextInt(1, ROWS * COLS);
         boolean tileMoved = slide(tileToMove);
         if (tileMoved) {
            n--;
         }
      }
   }

   /**
    * Returns the tile number at a specified location.
    */
   public int tileAt(int row, int col) {
      return grid[row][col];
   }

   /**
    * Moves a tile into the adjacent empty space. Invalid moves are ignored.
    *
    * @return true if the move is valid, false otherwise
    */
   public final boolean slide(int tile) {
      if (tile < 1 || tile > ROWS * COLS - 1) {
         return false;
      }

      // find positions of empty space and tile to slide
      int spaceRow = 0;
      int spaceCol = 0;
      int tileRow = 0;
      int tileCol = 0;

      for (int i = 0; i < ROWS; i++) {
         for (int j = 0; j < COLS; j++) {
            if (grid[i][j] == EMPTY_SPACE) {
               spaceRow = i;
               spaceCol = j;
            }
            if (grid[i][j] == tile) {
               tileRow = i;
               tileCol = j;
            }
         }
      }

      // move is valid if rows are the same and columns differ by one, or vice versa
      int rowDiff = Math.abs(tileRow - spaceRow);
      int colDiff = Math.abs(tileCol - spaceCol);
      if (rowDiff + colDiff == 1) {
         grid[tileRow][tileCol] = EMPTY_SPACE;
         grid[spaceRow][spaceCol] = tile;
         return true;
      }
      return false;
   }

   /**
    * Returns true if each tile is in its correct final position.
    */
   public boolean over() {
      int nextNum = 1;
      for (int[] row : grid) {
         for (int tile : row) {
            if (tile != nextNum && tile != EMPTY_SPACE) {
               return false;
            }
            nextNum++;
         }
      }
      return true;
   }

   /**
    * Returns the current game state.
    */
   @Override
   public String toString() {
      String gameState = "";
      for (int[] row : grid) {
         for (int tile : row) {
            if (tile == EMPTY_SPACE) {
               gameState += "   ";
            } else {
               gameState += String.format("%02d ", tile);
            }
         }
         gameState += '\n';
      }
      return gameState;
   }
}
