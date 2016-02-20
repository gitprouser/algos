/**
 * Created on 9/6/15.
 */
public class PercolationStats {
    private static double[] experimentRuntimeArr;
    private int T;
    private int N;

    /**
     * Perform T independent experiments on an N-by-N grid
     *
     * @param N Number of grids
     * @param T Number of experiments
     */
    public PercolationStats(int N, int T) throws IllegalArgumentException {
        if (N <= 0) {
            throw new IllegalArgumentException("Number of elements in grid should be > 0");
        }
        if (T <= 0)
            throw new IllegalArgumentException("Number of experiments should be > 1");
        this.N = N;
        this.T = T;
    }

    /**
     * Sample mean of percolation threshold
     *
     * @return
     */
    public double mean() {
        return StdStats.mean(experimentRuntimeArr);
    }

    /**
     * Sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev() {
        return StdStats.stddev(experimentRuntimeArr);
    }

//    private double sqrt(double n) {
//        if (n < 0) return Double.NaN;
//        double err = 1e-15;
//        double tmp = n;
//        while (Math.abs(tmp - n / tmp) > err * tmp) {
//            tmp = (n / tmp + tmp) / 2.0;
//        }
//        return tmp;
//    }

    /**
     * Low end point of 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    /**
     * High endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }


    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(20, 100);
        double size = (double)(percolationStats.N * percolationStats.N);
        experimentRuntimeArr = new double[percolationStats.T];

        for (int i = 0; i < percolationStats.T; i++) {
            Percolation perc = new Percolation(percolationStats.N);
            int cnt = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, percolationStats.N + 1);
                int col = StdRandom.uniform(1, percolationStats.N + 1);
                if (! perc.isOpen(row, col)) {
                    perc.open(row, col);
                    cnt = cnt + 1;
                }
            }
            experimentRuntimeArr[i] = (double)cnt/size;
        }

//        for(int i = 0; i < experimentRuntimeArr.length;i++)
//            System.out.println(experimentRuntimeArr[i]);

        StdOut.println(" mean : " + percolationStats.mean());
        StdOut.println(" stddev : " + percolationStats.stddev());
        StdOut.println(" 95% confidence interval : " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}
