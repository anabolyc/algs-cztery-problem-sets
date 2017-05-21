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

public class Percolation {
    
    //private final byte BLOCKED = 0;               // const for marking blocked cells
    private static final byte OPEN = 1;             // const for marking open cells

    //private QuickFindUF calcEngine;
    private WeightedQuickUnionUF calcEngine;        // union-find engine instance, uncomment for another engine    
    private byte[][] grid;                          // array representing grid
    private int TOP_INDEX = 0;                      // pseudo-const pointing on index of top conecting point 
    private int BOTTOM_INDEX = 0;                   // pseudo-const pointing on index of bottom conecting point
    private int GRID_SIZE = 0;                      // pseudo-const for grid size
    //private int openCount = 0;                      // current open cells count in grid
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.GRID_SIZE = N;
        grid = new byte[GRID_SIZE][GRID_SIZE];

        int size = GRID_SIZE * GRID_SIZE + 2;
        //calcEngine = new QuickFindUF(size);
        calcEngine = new WeightedQuickUnionUF(size);
        
        // last two entries for connecting top and bottom nodes
        TOP_INDEX = size - 2;
        BOTTOM_INDEX = size - 1;
        //for (int i = 1; i <= GRID_SIZE; i++) {
        //    calcEngine.union(TOP_INDEX, index(1, i));
        //    calcEngine.union(BOTTOM_INDEX, index(GRID_SIZE, i));
        //}
    }   
    
    // index in engine 
    private int index(int i, int j) {
        return GRID_SIZE * (i - 1) + (j - 1);
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if (grid[i - 1][j - 1] != OPEN) {
            grid[i - 1][j - 1] = OPEN;
            if (i == 1)
                calcEngine.union(TOP_INDEX, index(i, j));
            else
                if (isOpen(i - 1, j)) 
                    calcEngine.union(index(i, j), index(i - 1, j));
            if (j > 1)
                if (isOpen(i, j - 1)) 
                    calcEngine.union(index(i, j), index(i, j - 1));
            
            if (i == GRID_SIZE)
                calcEngine.union(BOTTOM_INDEX, index(i, j));
            else
                if (isOpen(i + 1, j)) 
                    calcEngine.union(index(i, j), index(i + 1, j));
            if (j < GRID_SIZE)
                if (isOpen(i, j + 1)) 
                    calcEngine.union(index(i, j), index(i, j + 1));
            //openCount++;
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return grid[i - 1][j - 1] == OPEN;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (grid[i - 1][j - 1] == OPEN)
            return calcEngine.connected(TOP_INDEX, index(i, j));
        else 
            return false;
    }    
    
    // does the system percolate?
    public boolean percolates() {
        return calcEngine.connected(TOP_INDEX, BOTTOM_INDEX);
    }
    
}
