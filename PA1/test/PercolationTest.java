/**
 * Created on 9/10/15.
 */
public class PercolationTest {
    public static void main(String args[]) {
        Percolation percolation = new Percolation(10);
        percolation.setDebugEnabled();
        percolation.createSiteCoordinate(1, 1).convert2dto1dPosition();
        percolation.createSiteCoordinate(10, 10).convert2dto1dPosition();
    }
}
