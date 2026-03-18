package chap10.sect6;

/**
 * A game of Slither played on a grid of dots. Players take turns claiming edges to build a single
 * continuous non-self-intersecting path. The first move may be any edge. Each subsequent move must
 * extend one of the two ends of the path. A player who cannot move loses.
 *
 * @author Drue Coles
 */
public class Slither extends GridOfDots {
    // endpoints of current path (or -1 if no move has been made)
    private int[] endA = {-1, -1};
    private int[] endB = {-1, -1};

    /**
     * Constructs a Slither game on a grid with the given dimensions.
     */
    public Slither(int rows, int cols) {
        super(rows, cols);
    }

    /**
     * Returns the name of this game.
     */
    @Override
    public String getGameName() {
        return "Slither";
    }

    /**
     * Returns true if claiming the given edge is legal. The first move may be any unclaimed edge.
     * Subsequent moves must extend one of the two ends of the path without intersecting it.
     */
    @Override
    public boolean isLegalMove(Edge edge) {
        if (edge.isClaimed()) {
            return false;
        }

        if (endA[0] == -1) { // no moves have been made yet
            return true;
        }

        int[] ep = edge.getEndpoints();

        // The edge can be claimed (legal move) if one endpoint is an endpoint of the path and the
        // other endpoint is not on the path. This can happen in four ways:
        //
        // 1. (ep[0], ep[1]) = (endA[0], endA[1]) and (ep[2], ep[3]) is not on the path.
        // 2. (ep[0], ep[1]) = (endB[0], endB[1]) and (ep[2], ep[3]) is not on the path.
        // 3. (ep[2], ep[3]) = (endA[0], endA[1]) and (ep[0], ep[1]) is not on the path.
        // 4. (ep[2], ep[3]) = (endB[0], endB[1]) and (ep[0], ep[1]) is not on the path.
        boolean b1 = (ep[0] == endA[0] && ep[1] == endA[1] && !isOnPath(ep[2], ep[3]));
        boolean b2 = (ep[0] == endB[0] && ep[1] == endB[1] && !isOnPath(ep[2], ep[3]));
        boolean b3 = (ep[2] == endA[0] && ep[3] == endA[1] && !isOnPath(ep[0], ep[1]));
        boolean b4 = (ep[2] == endB[0] && ep[3] == endB[1] && !isOnPath(ep[0], ep[1]));
        return b1 || b2 || b3 || b4;
    }

    /**
     * Returns true if (r, c) is on the path (that is, an endpoint of any claimed edge).
     */
    private boolean isOnPath(int r, int c) {
        for (Edge e : edges) {
            if (!e.isUnclaimed()) {
                int[] ep = e.getEndpoints();
                if ((ep[0] == r && ep[1] == c) || (ep[2] == r && ep[3] == c)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Attempts to claim the edge with the given endpoints for the current player.
     * Returns true if the move was made successfully, false otherwise.
     */
    @Override
    public boolean makeMove(int r1, int c1, int r2, int c2) {
        Edge edge = getEdge(r1, c1, r2, c2);
        if (edge == null || !isLegalMove(edge)) {
            return false;
        }
        edge.setOwner(getPlayerToMove());

        // update path endpoints
        int[] ep = edge.getEndpoints();
        if (endA[0] == -1) { // first move
            endA[0] = ep[0];
            endA[1] = ep[1];
            endB[0] = ep[2];
            endB[1] = ep[3];
        } else if (ep[0] == endA[0] && ep[1] == endA[1]) {
            endA[0] = ep[2];
            endA[1] = ep[3];
        } else if (ep[2] == endA[0] && ep[3] == endA[1]) {
            endA[0] = ep[0];
            endA[1] = ep[1];
        } else if (ep[0] == endB[0] && ep[1] == endB[1]) {
            endB[0] = ep[2];
            endB[1] = ep[3];
        } else {
            endB[0] = ep[0];
            endB[1] = ep[1];
        }

        togglePlayer();
        return true;
    }
}
