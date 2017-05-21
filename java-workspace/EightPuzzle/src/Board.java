import java.util.Arrays;

public class Board {

    private int size; 
    private int[][] board;
    
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {         
        size = blocks.length;
        board = new int[size][size];
        arrayCopy(blocks, board);
    }

    private static void arrayCopy(int[][] aSource, int[][] aDestination) {
        for (int i = 0; i < aSource.length; i++)
            aDestination[i] = Arrays.copyOf(aSource[i], aSource[i].length);
    }
    
    // board dimension N
    public int dimension() {               
        return size;
    }

    // number of blocks out of place
    public int hamming() {
        int result = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (board[i][j] != 0 && board[i][j] != j + i * size + 1)
                    result++;
        return result;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) 
                if (board[i][j] != 0) {
                    int x = board[i][j] - 1;
                    int pi = x / size;
                    int pj = x % size;
                    result += Math.abs(i - pi) + Math.abs(j - pj);
                }
        return result;
    }

    // is this board the goal board?
    public boolean isGoal() {    
        return hamming() == 0;
    }

    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int[][] boardcopy = new int[size][size];
        arrayCopy(board, boardcopy);
        int rown = 0;
        if (boardcopy[0][0] == 0 || boardcopy[0][1] == 0)
            rown = 1;
        boardcopy[rown][0] = board[rown][1];
        boardcopy[rown][1] = board[rown][0];
        return new Board(boardcopy);
    }   
    
    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board b = (Board) y;
        return this.toString().equals(b.toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> result = new Stack<Board>();
        int i0 = 0, j0 = 0;
        for (int i = 0; i < size; i++) 
            for (int j = 0; j < size; j++)
                if (board[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                    break;
                }
                    
        if (i0 > 0) {
            int[][] boardcopy = new int[size][size];
            arrayCopy(board, boardcopy);
            boardcopy[i0 - 1][j0] = board[i0][j0];
            boardcopy[i0][j0] = board[i0 - 1][j0];
            result.push(new Board(boardcopy));
        }
        
        if (j0 > 0) {
            int[][] boardcopy = new int[size][size];
            arrayCopy(board, boardcopy);
            boardcopy[i0][j0 - 1] = board[i0][j0];
            boardcopy[i0][j0] = board[i0][j0 - 1];
            result.push(new Board(boardcopy));
        }
        
        if (i0 < size - 1) {
            int[][] boardcopy = new int[size][size];
            arrayCopy(board, boardcopy);
            boardcopy[i0 + 1][j0] = board[i0][j0];
            boardcopy[i0][j0] = board[i0 + 1][j0];
            result.push(new Board(boardcopy));
        }
        
        if (j0 < size - 1) {
            int[][] boardcopy = new int[size][size];
            arrayCopy(board, boardcopy);
            boardcopy[i0][j0 + 1] = board[i0][j0];
            boardcopy[i0][j0] = board[i0][j0 + 1];
            result.push(new Board(boardcopy));
        }
        return result;
    }   
    
    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
 
    public static void main(String[] args) {
        //int[][] test = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        /*
        int[][] test = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        Board B = new Board(test);
        Board B0 = B.twin();
        StdOut.printf("dimension    : %s\n", B.dimension());
        StdOut.printf("hamming      : %s\n", B.hamming());
        StdOut.printf("manhattan    : %s\n", B.manhattan());
        StdOut.printf("isGoal       : %b\n", B.isGoal());
        StdOut.printf("board        : \n%s\n", B.toString());
        StdOut.printf("twin board   : \n%s\n", B0.toString());
        StdOut.printf("B eq B       : %b\n", B.equals(B));
        StdOut.printf("B eq B0      : %b\n", B.equals(B0));
        for (Board B1 : B.neighbors()) {
            StdOut.printf("neighbor     : \n%s\n", B1.toString());
            StdOut.printf("hamming      : %s\n", B1.hamming());
            StdOut.printf("manhattan    : %s\n", B1.manhattan());
            StdOut.printf("isGoal       : %b\n", B1.isGoal());
            StdOut.println();
        } */   
    }
}