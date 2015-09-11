import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created on 9/6/15.
 */
public class Percolation {

    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int TOP_OF_SITE_PERCOLATOR = Integer.MAX_VALUE;
    private static final int BOTTOM_OF_SITE_PERCOLATOR = Integer.MIN_VALUE;


    private int N;


    private int[][] sites;
    WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean isDebugEnabled;

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
        this.sites = new int[N - 1][N - 1];
        weightedQuickUnionUF = new WeightedQuickUnionUF((N - 1) * (N - 1));
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

        for (Site neighbour : site.getNeighbours())
            weightedQuickUnionUF.connected(neighbour.convert2dto1dPosition(), site.convert2dto1dPosition());
    }

    /**
     * Is site (row, col) open?
     *
     * @param row - row number
     * @param col - col number
     * @return
     */
    public boolean isOpen(int row, int col) {
        Site site = new Site(row, col);
        return (sites[site.row][site.col] == OPEN);
    }

    /**
     * Is site (row row, column col) full?
     *
     * @param row - row number
     * @param col - col number
     * @return
     */
    public boolean isFull(int row, int col) {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
    }

    /**
     * Does the system percolate?
     *
     * @return boolean true means percolates false means no percolation
     */
    public boolean percolates() {
        throw new UnsupportedOperationException("TO BE IMPLEMENTED");
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
            validateRowsAndCols(row, col);
            this.row = row - 1;
            this.col = col - 1;
            if (isDebugEnabled)
                StdOut.println(this);
        }

        private void validateRowsAndCols(int row, int col) throws IndexOutOfBoundsException {
            if (row < 1 || row > N) {
                if (isDebugEnabled) {
                    if (row < 1)
                        StdOut.println("row is underbounds " + this.row);
                    else
                        StdOut.println("Row is overbounds " + this.row);
                }
                throw new IndexOutOfBoundsException("The row number provided is outside the range 1 to " + N);
            }

            if (col < 1 || col > N) {
                if (isDebugEnabled) {
                    if (col < 1)
                        StdOut.println("Col is underbounds " + this.col);
                    else
                        StdOut.println("Col is overbounds " + this.col);
                }
                throw new IndexOutOfBoundsException("The col number provided is outside the range 1 to " + N);
            }
        }

        int convert2dto1dPosition() {
            int oneD = (this.row * N) + this.col;
            if (isDebugEnabled)
                StdOut.println(this + " Convert 2d to 1d => '" + oneD + "'");
            return oneD;
        }

        public String toString() {
            return "Site coordinates (" + this.row + ", " + this.col + ")";
        }

        Site[] getNeighbours() {
            if (row == 0 && col == 0) { // Site is at the corners
                return null;
            } else if (row == 0) {      // Site is right at the top
                return null;
            } else if (col == 0) {      // Site is at the left edge
                return null;
            } else if (col == N - 1) {  // Site is at the right edge
                return null;
            } else if (row == N - 1) {  // Site is at the bottom
                return null;
            } else {
                Site[] neighbours = new Site[4];
                neighbours[0] = new Site(this.row - 1, this.col);
                neighbours[1] = new Site(this.row, this.col - 1);
                neighbours[2] = new Site(this.row, this.col + 1);
                neighbours[3] = new Site(this.row + 1, this.col);
                return neighbours;
            }
        }
    }
}
