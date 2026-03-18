package chap10.sect6;

/**
 * A game of Cram played on a grid of dots. Players take turns claiming edges. A move is legal if
 * the chosen edge is unclaimed and not adjacent to any already claimed edge. A player who cannot
 * move loses.
 *
 * @author Drue Coles
 */
public class Cram extends GridOfDots {

    /**
     * Constructs a Cram game on a grid with the given dimensions.
     */
    public Cram(int rows, int cols) {
        super(rows, cols);
    }

    /**
     * Returns the name of this game.
     */
    @Override
    public String getGameName() {
        return "Cram";
    }

    /**
     * Returns true if claiming the given edge is legal: the edge must be unclaimed and not adjacent
     * to any already claimed edge.
     */
    @Override
    public boolean isLegalMove(Edge edge) {
        if (edge.isClaimed()) {
            return false;
        }
        for (Edge e : edges) {
            if (e.isClaimed() && edge.sharesEndpointWith(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Attempts to claim the edge with the given endpoints for the current player. Returns true if
     * the move was made successfully, false otherwise.
     */
    @Override
    public boolean makeMove(int r1, int c1, int r2, int c2) {
        Edge edge = getEdge(r1, c1, r2, c2);
        if (edge == null || !isLegalMove(edge)) {
            return false;
        }
        edge.setOwner(getPlayerToMove());
        togglePlayer();
        return true;
    }
}
