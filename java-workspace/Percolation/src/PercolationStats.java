/*************************************************************************
 *  Compilation    : javac Percolation.java
 *  Execution      : java Percolation
 *  
 *  Author         : Andrey Malyshenko
 *  Email          : andrey.malysheko@gmail.com
 *  Date           : 2014/02/05
 *  
 *
 *  This package is part of Practical assignment in online course Algorithms, Part I
 *  by Kevin Wayne, Robert Sedgewick
 *  https://class.coursera.org/algs4partI-004
 *
 *  Please fins assignment details here:
 *  http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 *  
 *************************************************************************/

public class PercolationStats {

    private double[] results;
    private int GRID_SIZE = 0;            // pseudo-const for saving grid size after class initialzied
    private int RUNS_COUNT = 0;           // pseudo-const for saving test runs count
    
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
    if ((N <= 0) || (T <= 0))
    throw new IllegalArgumentException();
    
        GRID_SIZE = N;
        RUNS_COUNT = T;
        results = new double[RUNS_COUNT];
        for (int i = 0; i < RUNS_COUNT; i++) {
            Percolation instance = new Percolation(GRID_SIZE);
            while (!instance.percolates()) {
                instance.open(StdRandom.uniform(GRID_SIZE) + 1, StdRandom.uniform(GRID_SIZE) + 1);
            }
            //System.out.format("Open Count: %d; Total Count: %d; %% = %f", instance.openCount, N * N, instance.openCount *100. / N / N);
            //System.out.println();
            for (int k = 1; k <= N; k++)
                for (int j = 1; j <= N; j++)
                    if (instance.isOpen(k, j))
                        results[i]++;
            results[i] = (double) results[i] / GRID_SIZE / GRID_SIZE;
        }
    }
    
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev() / Math.sqrt(RUNS_COUNT));
    }             
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev() / Math.sqrt(RUNS_COUNT));
    }             
    
    // test client, described below
    public static void main(String[] args) {
        int gridSize, runsCount; 
        gridSize = Integer.parseInt(args[0]);
        runsCount = Integer.parseInt(args[1]);
        
        PercolationStats PS = new PercolationStats(gridSize, runsCount);
        System.out.format("mean                    = %f\n", PS.mean());
        System.out.format("stddev                  = %f\n", PS.stddev());
        System.out.format("95%% confidence interval = %f, %f\n", PS.confidenceLo(), PS.confidenceHi());
    }   
}
