package chap10.sect6;

import java.util.ArrayList;
import java.util.List;
import static chap10.sect6.Edge.Owner.*;

/**
 * Abstract framework for two-player games played on a rectangular grid of dots. A player makes a
 * move by claiming an available edge between horizontally or vertically adjacent dots. Concrete
 * subclasses define the rules for legal moves and the conditions for winning and losing. Players
 * alternate turns unless otherwise specified by the subclass.
 *
 * @author Drue Coles
 */
public abstract class Dots {
    protected final int rows; // number of rows in the grid
    protected final int cols; // number of columns in the grid

    protected final List<Edge> edges = new ArrayList<>(); // all edges in the grid

    private Edge.Owner playerToMove = PLAYER1;

    /**
     * Constructs a grid of dots with a specified numbers of rows and columns. All edges are
     * initialized as unclaimed.
     */
    public Dots(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        // horizontal lines
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols - 1; c++) {
                edges.add(new Edge(r, c, r, c + 1));
            }
        }

        // vertical lines
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows - 1; r++) {
                edges.add(new Edge(r, c, r + 1, c));
            }
        }
    }

    /**
     * Returns the player whose turn it is.
     */
    public Edge.Owner getPlayerToMove() {
        return playerToMove;
    }

    /**
     * Toggles the player to move.
     */
    protected void togglePlayer() {
        playerToMove = (playerToMove == PLAYER1 ? PLAYER2 : PLAYER1);
    }

    /**
     * Returns the edge connecting the specified endpoints, or null if no such line exists.
     */
    public Edge getEdge(int x1, int y1, int x2, int y2) {
        for (Edge edge : edges) {
            if (edge.connects(x1, y1, x2, y2)) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Returns true if (x, y) is a dot in the grid.
     */
    protected boolean isValidDot(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /**
     * Returns true if (x1, y1) and (x2, y2) are adjacent dots in the grid.
     */
    protected boolean lineExists(int x1, int y1, int x2, int y2) {
        if (!isValidDot(x1, y1) || !isValidDot(x2, y2)) {
            return false;
        }
        return Math.abs(x2 - x1) + Math.abs(y2 - y1) == 1;
    }

    /**
     * Claims the specified line for the current player. Assumes the line is valid and unclaimed.
     */
    protected void claim(Edge line) {
        line.setOwner(playerToMove);
    }

    /**
     * Returns true if at least one legal move exists.
     */
    public abstract boolean hasMove();

    /**
     * Makes a move by claiming the edge between (x1, y1) and (x2, y2). Invalid moves are ignored.
     *
     * @return true if move succeeds
     */
    public abstract boolean makeMove(int x1, int y1, int x2, int y2);

    /**
     * Returns true if the game is over.
     */
    public abstract boolean gameOver();

    /**
     * Returns the winning player, or NONE if the game is not over.
     */
    public abstract Edge.Owner winner();

    /**
     * Returns the losing player, or NONE if the game is not over.
     */
    public abstract Edge.Owner loser();
}
