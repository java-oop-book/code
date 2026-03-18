package chap10.sect6;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for Grid of Dots games. Manages a rectangular grid of dots connected by edges
 * that players take turns claiming.
 *
 * @author Drue Coles
 */
public abstract class GridOfDots {
    public enum Player {
        PLAYER_ONE, PLAYER_TWO;

        @Override
        public String toString() {
            return name().replace('_', ' ');
        }
    }

    private Player playerToMove = Player.PLAYER_ONE;

    protected final int rows, cols;
    protected final List<Edge> edges = new ArrayList<>();

    /**
     * Constructs a grid of dots with the given dimensions and populates it with all horizontal and
     * vertical edges.
     */
    public GridOfDots(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols - 1; c++)
                edges.add(new Edge(r, c, r, c + 1)); // horizontal

        for (int r = 0; r < rows - 1; r++)
            for (int c = 0; c < cols; c++)
                edges.add(new Edge(r, c, r + 1, c)); // vertical
    }

    /**
     * Returns the number of rows in this grid.
     */
    public int getRows() { return rows; }

    /**
     * Returns the number of columns in this grid.
     */
    public int getCols() { return cols; }

    /**
     * Returns all edges in this grid.
     */
    public List<Edge> getEdges() { return edges; }

    /**
     * Returns the player whose turn it is to move.
     */
    public Player getPlayerToMove() { return playerToMove; }

    /**
     * Returns the edge with the given endpoints, or null if no such edge exists. The endpoints may
     * be given in either order.
     */
    public Edge getEdge(int r1, int c1, int r2, int c2) {
        // swap endpoints if necessary so that (r1, c1) < (r2, c2) in lex order
        if (r1 > r2 || (r1 == r2 && c1 > c2)) {
            int tempR = r1;
            int tempC = c1;
            r1 = r2;
            c1 = c2;
            r2 = tempR;
            c2 = tempC;
        }
        for (Edge edge : edges) {
            int[] p = edge.getEndpoints();
            if (p[0] == r1 && p[1] == c1 && p[2] == r2 && p[3] == c2) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Switches the turn to the other player.
     */
    protected void togglePlayer() {
        playerToMove = (playerToMove == Player.PLAYER_ONE) ? Player.PLAYER_TWO : Player.PLAYER_ONE;
    }

    /**
     * Returns true if there are no legal moves remaining.
     */
    public boolean gameOver() {
        for (Edge e : edges) {
            if (e.isUnclaimed() && isLegalMove(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the winner of the game, or null if the game is not yet over. The winner is the player
     * who made the last move (that is, not the player whose turn it is).
     */
    public Player winner() {
        if (!gameOver()) {
            return null;
        }
        return getPlayerToMove() == Player.PLAYER_ONE ? Player.PLAYER_TWO : Player.PLAYER_ONE;
    }

    /**
     * Returns the name of this game.
     */
    public abstract String getGameName();

    /**
     * Returns true if claiming the given edge is a legal move in the current game state.
     */
    public abstract boolean isLegalMove(Edge edge);

    /**
     * Attempts to claim the edge with the given endpoints for the current player. Returns true if
     * the move was made successfully, false otherwise.
     */
    public abstract boolean makeMove(int r1, int c1, int r2, int c2);
}
