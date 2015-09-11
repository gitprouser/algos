/**
 * Created on 9/10/15.
 */
public class PercolationTest {
    public static void main(String args[]) {
        Percolation percolation = new Percolation(10);
//        percolation.setDebugEnabled();
//        percolation.createSiteCoordinate(1, 1).convert2dto1dPosition();
//        percolation.createSiteCoordinate(10, 10).convert2dto1dPosition();
        System.out.println("one coordinate" + percolation.createSiteCoordinate(1,1));
        System.out.println("== Neighbors ==" + percolation.createSiteCoordinate(1,1).getNeighbors().length);
        for(Percolation.Site neighbor : percolation.createSiteCoordinate(3,1).getNeighbors()) {
            System.out.println(neighbor);
        }
    }
}
