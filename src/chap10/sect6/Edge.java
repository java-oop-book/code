package chap10.sect6;

import static chap10.sect6.GridOfDots.Player;

/**
 * An edge in a Grid of Dots game.
 *
 * @author Drue Coles
 */
public class Edge {
    private final int r1, c1, r2, c2;  // endpoints stored in lex order: (r1, c1) < (r2, c2)
    private Player owner; // null if unclaimed

    /**
     * Constructs an edge with endpoints (r1, c1) and (r2, c2). The endpoints are assumed to be
     * distinct and will be swapped if necessary so that (r1, c1) < (r2, c2) in lex order.
     */
    public Edge(int r1, int c1, int r2, int c2) {
        if (r1 < r2 || (r1 == r2 && c1 < c2)) {
            this.r1 = r1; this.c1 = c1;
            this.r2 = r2; this.c2 = c2;
        } else {
            this.r1 = r2; this.c1 = c2;
            this.r2 = r1; this.c2 = c1;
        }
    }

    /**
     * Returns the endpoints of this edge as {r1, c1, r2, c2} in lex order.
     */
    public int[] getEndpoints() {
        return new int[]{r1, c1, r2, c2};
    }

    /**
     * Returns the owner of this edge, or null if unclaimed.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Claims this edge for the given player.
     */
    public void setOwner(Player player) {
        owner = player;
    }

    /**
     * Returns true if this edge has not been claimed.
     */
    public boolean isUnclaimed() {
        return owner == null;
    }

    /**
     * Returns true if this edge has been claimed.
     */
    public boolean isClaimed() {
        return owner != null;
    }

    /**
     * Returns true if this edge shares an endpoint with the given edge. An edge is not considered
     * to share an endpoint with itself.
     */
    public boolean sharesEndpointWith(Edge other) {
        return other != this && (other.touches(r1, c1) || other.touches(r2, c2));
    }

    /**
     * Returns true if (r, c) is an endpoint of this edge.
     */
    private boolean touches(int r, int c) {
        return (r1 == r && c1 == c) || (r2 == r && c2 == c);
    }
}
