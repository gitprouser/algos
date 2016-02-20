import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created on 9/6/15.
 */
public class Percolation {

    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int TOP = 0, LEFT = 1, RIGHT = 2, BOTTOM = 3;
    private int N;

    private int[][] sites;
    WeightedQuickUnionUF weightedQuickUnionUF;

    private boolean isDebugEnabled;
    private Site topOFSitePercolator = new Site(-1, -1);

    /**
     * Create N-by-N grid, with all sites blocked.
     *
     * @param N - Number of rows or columns
     */
    public Percolation(int N) throws IllegalArgumentException {
        if (N < 1) {
            throw new IllegalArgumentException("N <= 0");
        }
        this.N = N;
        this.sites = new int[N][N];
        weightedQuickUnionUF = new WeightedQuickUnionUF((N * N) + 1);
    }

    /**
     * Open site (row row, column j) if it is not already open
     *
     * @param row - row number
     * @param col - col number
     */
    public void open(int row, int col) {
        Site site = new Site(row, col);
        if (sites[site.row][site.col] == BLOCKED) {
            sites[site.row][site.col] = OPEN;
        }

        for (Site neighbour : site.getNeighbors()) {
            if(neighbour != null && __isOpen(neighbour)) {
                weightedQuickUnionUF.union(site.convert2dto1dPosition(), neighbour.convert2dto1dPosition());
            }
        }
    }

    /**
     * Is site (row, col) open?
     *
     * @param row - row number
     * @param col - col number
     * @return
     */
    public boolean isOpen(int row, int col) {
        return __isOpen(new Site(row, col));
    }

    private boolean __isOpen(Site site) {
        return site.isSentinalNode()? true : (sites[site.row][site.col] == OPEN) ;
    }

    /**
     * Is site (row row, column col) full?
     *
     * @param row - row number
     * @param col - col number
     * @return
     */
    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            return weightedQuickUnionUF.connected(new Site(row, col).convert2dto1dPosition(), topOFSitePercolator.convert2dto1dPosition());
        }
        return false;
    }

    /**
     * Does the system percolate?
     *
     * @return boolean true means percolates false means no percolation
     */
    public boolean percolates() {
        for(int i = 1; i <= N; i++) {
            if (isOpen(N, i)) {
                return weightedQuickUnionUF.connected(new Site(N, i).convert2dto1dPosition(), topOFSitePercolator.convert2dto1dPosition());
            }
        } return false;
    }

    Site createSiteCoordinate(int row, int col) {
        return new Site(row, col);
    }

    void setDebugEnabled() {
        isDebugEnabled = true;
    }

    class Site {
        int row, col;

        Site(int row, int col) {
            if (row == -1 && col == -1) {   // isSentinial node
                this.row = row;
                this.col = col;
                return;
            }
            validateRowsAndCols(row, col);
            this.row = row - 1;
            this.col = col - 1;
            if (isDebugEnabled)
                StdOut.println(this);
        }

        private void validateRowsAndCols(int row, int col) throws IndexOutOfBoundsException {
            if (row < 1 || row > N)
                throw new IndexOutOfBoundsException("The row number provided is outside the range 1 to " + N);

            if (col < 1 || col > N)
                throw new IndexOutOfBoundsException("The col number provided is outside the range 1 to " + N);
        }

        int convert2dto1dPosition() {
            if (row == -1 && col == -1)  // The sentinial node
                return N * N;

            return (this.row * N) + this.col;
        }

        public String toString() {
            return "Site coordinates (" + this.row + ", " + this.col + ")";
        }

        Site[] getNeighbors() {
            int baseOneOffset = 1;
            Site[] neighbors = new Site[4];

            if (row > 0)
                neighbors[TOP] = new Site(this.row - 1 + baseOneOffset, this.col + baseOneOffset);
            if (col > 0)
                neighbors[LEFT] = new Site(this.row + baseOneOffset, this.col - 1 + baseOneOffset);
            if (row < N - 1)
                neighbors[BOTTOM] = new Site(this.row + 1 + baseOneOffset, this.col + baseOneOffset);
            if (col < N - 1)
                neighbors[RIGHT] = new Site(this.row + baseOneOffset, this.col + 1 + baseOneOffset);
            if (row == 0)
                neighbors[TOP] = topOFSitePercolator;

            return neighbors;
        }
        boolean isSentinalNode() {
            return (this.row == -1 && this.col == -1);
        }
    }
}
