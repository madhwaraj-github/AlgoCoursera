/**
 * Model a percolation system using the union-find algorithm
 * 
 * @author raj (Madhwaraj Rao) Coursera Algorithms class programming assignment
 *         June, 2015
 * 
 */
public class Percolation {

    /**
     * dimension of the N-by-N grid
     */
    private final int N;

    /**
     * virtual top id
     */
    private int virtualTopId;

    /**
     * boolean array to denote open sites
     */
    private boolean[] open;
    
    /**
     * boolean array to denote bottom reached sites
     */
    private boolean[] bottomed;

    /**
     * weighted quick union UF implementation
     */
    private WeightedQuickUnionUF wqu;

    /**
     * 
     * create N-by-N grid, with all sites blocked
     * 
     * @param n
     *            dimension of the grid
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid grid dimension");
        }
        N = n;
        open = new boolean[N * N];
        bottomed = new boolean[N * N + 2];
        // virtual top ids
        virtualTopId = N * N;
 
        // add virtual top sites
        int nSites = N * N + 1;
        wqu = new WeightedQuickUnionUF(nSites);

    }

    /**
     * open site (row i, column j) if it is not open already
     * 
     * @param i
     *            row index (1-based)
     * @param j
     *            column index (1-based)
     */
    public void open(int i, int j) {
        boolean localBottomed = false;
        int id = xyToId(i, j);
        
        if (open[id]) {
            return;
        }
        open[id] = true;
        
        int root = wqu.find(id);
        if (i == N) {
            // wqu.union(virtualBottomId, id);
            bottomed[root] = true;
            localBottomed = true;
        }
        
        int idx = -1;
        try {
            idx = xyToId(i, j - 1);
            if (open[idx]) {
                localBottomed |= bottomed[wqu.find(idx)];
                wqu.union(id, idx);
            }
        } catch (IndexOutOfBoundsException ioobe) {
            // expected
            idx = -1;
        }
        try {
            idx = xyToId(i, j + 1);
            if (open[idx]) {
                localBottomed |= bottomed[wqu.find(idx)];
                wqu.union(id, idx);
            }
        } catch (IndexOutOfBoundsException ioobe) {
            // expected
            idx = -1;
        }
        try {
            idx = xyToId(i - 1, j);
            if (open[idx]) {
                localBottomed |= bottomed[wqu.find(idx)];
                wqu.union(id, idx);
            }
        } catch (IndexOutOfBoundsException ioobe) {
            // expected
            idx = -1;
        }
        try {
            idx = xyToId(i + 1, j);
            if (open[idx]) {
                localBottomed |= bottomed[wqu.find(idx)];
                wqu.union(id, idx);
            }
        } catch (IndexOutOfBoundsException ioobe) {
            // expected
            idx = -1;
        }
        
        if (localBottomed) {
            root = wqu.find(id);
            bottomed[root] |= localBottomed;
        }
        
        // union with the virtual sites if necessary
        if (i == 1) {
            wqu.union(virtualTopId, root);
            root = wqu.find(id);
            bottomed[root] |= localBottomed;
        }
    }

    /**
     * is site (row i, column j) open?
     * 
     * @param i
     *            row index (1-based)
     * @param j
     *            column index (1-based)
     * @return true if site(i, j) is open
     */
    public boolean isOpen(int i, int j) {
        int id = xyToId(i, j);
        return open[id];
    }

    /**
     * is site (row i, column j) full?
     * 
     * @param i
     *            row index (1-based)
     * @param j
     *            column index (1-based)
     * @return true if site(i, j) is full
     */
    public boolean isFull(int i, int j) {
        int id = xyToId(i, j);
        return wqu.connected(virtualTopId, id);
    }

    /**
     * does the system percolate?
     * 
     * @return true if it percolates
     */
    public boolean percolates() {
        // return wqu.connected(virtualTopId, virtualBottomId);
        return bottomed[wqu.find(virtualTopId)];
    }

    /**
     * convert from a 1-based 2 dimensional coordinate to a 0-based single
     * dimensional coordinate (array index) The 1-d array is a row major
     * representation of the 2-d array
     * 
     * @param i
     *            row index
     * @param j
     *            column index
     * @return single dimension index
     * @throws IndexOutOfBoundsException
     */
    private int xyToId(int i, int j) {
        if ((i <= 0) || (i > N)) {
            throw new IndexOutOfBoundsException("invalid row index");
        }
        if ((j <= 0) || (j > N)) {
            throw new IndexOutOfBoundsException("invalid column index");
        }

        int id = ((i - 1) * N) + (j - 1);
        // assert ((id >= 0) && (id <= (N * N) - 1));
        return id;
    }

    /**
     * for testing only
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {

    }

}
