public class Solver {
    
    private MinPQ<Node> queue = new MinPQ<Node>();
    private Node result = null;
    private boolean isSolvable = true; 
    
    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private Node prev;
        
        public Node(Board board) {
            this.board = board;
            this.moves = 0;
            this.prev = null;
        }
        
        public Node(Board board, int moves, Node node) {
            this.board = board;
            this.moves = moves;
            this.prev = node;
        }
        
        private int priority() {
            return this.moves + this.board.manhattan(); 
            //return this.moves + this.board.hamming();
        }
        
        public int compareTo(Node that) {
            return this.priority() - that.priority();
        }
    }
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        queue.insert(new Node(initial));
        while (true) {
            Node min = queue.delMin();
            if (min == null)
                break;
            if (min.board.isGoal()) {
                result = min;
                break;
            } else if (min.board.twin().isGoal()) {
                isSolvable = false;
                break;
            } else { 
                for (Board B: min.board.neighbors()) {
                    Board prevBoard = null;
                    if (min.prev != null)
                        prevBoard = min.prev.board; 
                    if (!B.equals(prevBoard)) 
                        queue.insert(new Node(B, min.moves + 1, min));
                }
            }
        }
    }            
    
    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }            
    
    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (isSolvable())
            return result.moves;
        else
            return -1;
    }                      

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if (!isSolvable) return null;
        Stack<Board> solution = new Stack<Board>();
        Node current = result;
        while (true) {
            solution.push(current.board);
            current = current.prev;
            if (current == null)
                break;
        }
        return solution;
    }       
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        int[][] test = {{0, 2, 3}, {4, 5, 6}, {7, 8, 1}};
        Board B = new Board(test);
        Solver S = new Solver(B);
        StdOut.printf("Initial Board  : \n%s\n", B.toString());
        StdOut.printf("isSolvable     : %b\n", S.isSolvable());
        StdOut.printf("moves          : %s\n", S.moves());
        for (Board B0 : S.solution()) {
            StdOut.printf("step           : \n%s\n", B0.toString());    
            
        }
    } 
}