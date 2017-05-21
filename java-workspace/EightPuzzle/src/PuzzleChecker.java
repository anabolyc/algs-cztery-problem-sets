public class PuzzleChecker {

    public static void main(String[] args) {
        String[] files = { "c:\\_temp\\8puzzle\\puzzle00.txt"
                , "c:\\_temp\\8puzzle\\puzzle01.txt"
                , "c:\\_temp\\8puzzle\\puzzle02.txt"
                , "c:\\_temp\\8puzzle\\puzzle03.txt"
                , "c:\\_temp\\8puzzle\\puzzle04.txt"
                , "c:\\_temp\\8puzzle\\puzzle05.txt"
                , "c:\\_temp\\8puzzle\\puzzle06.txt"
                , "c:\\_temp\\8puzzle\\puzzle07.txt"
                , "c:\\_temp\\8puzzle\\puzzle08.txt"
                , "c:\\_temp\\8puzzle\\puzzle09.txt"
                , "c:\\_temp\\8puzzle\\puzzle10.txt"
                , "c:\\_temp\\8puzzle\\puzzle11.txt"
                , "c:\\_temp\\8puzzle\\puzzle12.txt"
                , "c:\\_temp\\8puzzle\\puzzle13.txt"
                , "c:\\_temp\\8puzzle\\puzzle14.txt"
                , "c:\\_temp\\8puzzle\\puzzle15.txt"
                , "c:\\_temp\\8puzzle\\puzzle16.txt"
                , "c:\\_temp\\8puzzle\\puzzle17.txt"
                , "c:\\_temp\\8puzzle\\puzzle18.txt"
                , "c:\\_temp\\8puzzle\\puzzle19.txt"
                , "c:\\_temp\\8puzzle\\puzzle20.txt"
                , "c:\\_temp\\8puzzle\\puzzle21.txt"
                , "c:\\_temp\\8puzzle\\puzzle22.txt"
                , "c:\\_temp\\8puzzle\\puzzle23.txt"
                , "c:\\_temp\\8puzzle\\puzzle24.txt"
                , "c:\\_temp\\8puzzle\\puzzle25.txt"
                , "c:\\_temp\\8puzzle\\puzzle26.txt"
                , "c:\\_temp\\8puzzle\\puzzle27.txt"
                , "c:\\_temp\\8puzzle\\puzzle28.txt"
                , "c:\\_temp\\8puzzle\\puzzle29.txt"
                , "c:\\_temp\\8puzzle\\puzzle2x2-unsolvable1.txt"
                , "c:\\_temp\\8puzzle\\puzzle2x2-unsolvable2.txt"
                , "c:\\_temp\\8puzzle\\puzzle2x2-unsolvable3.txt"
                , "c:\\_temp\\8puzzle\\puzzle30.txt"
                , "c:\\_temp\\8puzzle\\puzzle31.txt"
                , "c:\\_temp\\8puzzle\\puzzle32.txt"
                , "c:\\_temp\\8puzzle\\puzzle33.txt"
                , "c:\\_temp\\8puzzle\\puzzle34.txt"
                , "c:\\_temp\\8puzzle\\puzzle35.txt"
                , "c:\\_temp\\8puzzle\\puzzle36.txt"
                , "c:\\_temp\\8puzzle\\puzzle37.txt"
                , "c:\\_temp\\8puzzle\\puzzle38.txt"
                , "c:\\_temp\\8puzzle\\puzzle39.txt"
                , "c:\\_temp\\8puzzle\\puzzle3x3-unsolvable.txt"
                , "c:\\_temp\\8puzzle\\puzzle3x3-unsolvable1.txt"
                , "c:\\_temp\\8puzzle\\puzzle3x3-unsolvable2.txt"
                , "c:\\_temp\\8puzzle\\puzzle40.txt"
                , "c:\\_temp\\8puzzle\\puzzle41.txt"
                , "c:\\_temp\\8puzzle\\puzzle42.txt"
                , "c:\\_temp\\8puzzle\\puzzle43.txt"
                , "c:\\_temp\\8puzzle\\puzzle44.txt"
                , "c:\\_temp\\8puzzle\\puzzle45.txt"
                , "c:\\_temp\\8puzzle\\puzzle46.txt"
                , "c:\\_temp\\8puzzle\\puzzle47.txt"
                , "c:\\_temp\\8puzzle\\puzzle48.txt"
                , "c:\\_temp\\8puzzle\\puzzle49.txt"
                , "c:\\_temp\\8puzzle\\puzzle4x4-78.txt"
                , "c:\\_temp\\8puzzle\\puzzle4x4-80.txt"
                , "c:\\_temp\\8puzzle\\puzzle4x4-hard1.txt"
                , "c:\\_temp\\8puzzle\\puzzle4x4-hard2.txt"
                , "c:\\_temp\\8puzzle\\puzzle4x4-unsolvable.txt"
                , "c:\\_temp\\8puzzle\\puzzle50.txt" };
        
        for (String filename : files) {

            In in = new In(filename);
            int N = in.readInt();
            int[][] tiles = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    tiles[i][j] = in.readInt();
                }
            }
            
            Stopwatch sw = new Stopwatch();
            // solve the slider puzzle
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);
            StdOut.printf("%s : %s moves : %.2f seconds\n", filename, solver.moves(), sw.elapsedTime());
        }
    }
}
