/**
 * Calculate stats for the Percolation implementation
 * 
 * @author raj Coursera Algorithm class June, 2015
 * 
 */

public class PercolationStats {

    /**
     * array to hold fraction of open sites
     */
    private double[] fractionOpenSites = null;

    /**
     * computed mean
     */
    private double meanValue;

    /**
     * computed std deviation
     */
    private double stdDevValue;

    /**
     * computed confidences
     */
    private double confLo;
    private double confHi;

    /**
     * perform T independent experiments on an N-by-N grid
     * 
     * @param N
     *            grid dimension
     * @param T
     *            number of times to run percolation
     * @throws IllegalArgumentException
     */
    public PercolationStats(int N, int T) {
        if (N <= 0) {
            throw new IllegalArgumentException("illegal argument for N");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("illegal argument for T");
        }

        fractionOpenSites = new double[T];
        int numSites = N * N;

        for (int k = 0; (k < T); ++k) {
            Percolation perc = new Percolation(N);
            int openSites = 0;
            while (!perc.percolates()) {
                int i, j;
                while (true) {
                    i = StdRandom.uniform(1, N + 1);
                    j = StdRandom.uniform(1, N + 1);
                    if (!perc.isOpen(i, j)) {
                        perc.open(i, j);
                        ++openSites;
                        break;
                    }
                }
            }
            fractionOpenSites[k] = ((double) openSites) / (double) numSites;
        } // for

        // compute mean
        meanValue = StdStats.mean(fractionOpenSites);

        // compute std deviation and confidences
        if (T == 1) {
            stdDevValue = Double.NaN;
            confLo = Double.NaN;
            confHi = Double.NaN;
        } else {
            stdDevValue = StdStats.stddev(fractionOpenSites);
            double delta = (1.96d * stdDevValue) / Math.sqrt((double) T);
            confLo = meanValue - delta;
            confHi = meanValue + delta;
        }
    }

    /**
     * sample mean of percolation threshold
     * 
     * @return mean
     */
    public double mean() {
        return meanValue;
    }

    /**
     * sample standard deviation of percolation threshold
     * 
     * @return stddev
     */
    public double stddev() {
        return stdDevValue;
    }

    /**
     * low endpoint of 95% confidence interval
     * 
     * @return low endpoint
     */
    public double confidenceLo() {
        return confLo;
    }

    /**
     * high endpoint of 95% confidence interval
     * 
     * @return hi endpoint
     */
    public double confidenceHi() {
        return confHi;
    }

    /**
     * test client
     * 
     * @param args
     *            N, T
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "illegal number of arguments to main()");
        }
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(N, T);
        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println("95% confidence interval = "
                + percStats.confidenceLo() + ", " + percStats.confidenceHi());
    }

}
