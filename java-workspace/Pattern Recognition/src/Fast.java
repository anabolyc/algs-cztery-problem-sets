import java.util.Arrays;

public class Fast {
    
    private static void print(Point[] clone, Point P, int j, int k) {
        Arrays.sort(clone, j - k, j);
        if (P.compareTo(clone[j - k]) < 0) {
            for (int x = k; x > 0; x--)
                StdOut.printf("%s -> ", clone[j - x]);
            StdOut.printf("%s\n", P);
        
            P.drawTo(clone[j - 1]);
            StdDraw.show(0);
        }
    }
    
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] Points = new Point[N];
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            Points[i] = p;
            p.draw();
        }
        Arrays.sort(Points);
        
        Point[] clone = Points.clone();
        for (int i = 0; i < N; i++) {
            //pick one point and sort others
            Point P = Points[i];
            Arrays.sort(clone, P.SLOPE_ORDER);
            // check slope sequence
            int j = 1, k = 1;
            while (j < N) {
                if (P.slopeTo(clone[j]) == P.slopeTo(clone[j - 1])) {
                    k++;
                } else {
                    if (k >= 3)
                        print(clone, P, j, k);
                    k = 1;
                }
                j++;
            }
            if (k >= 3)
                print(clone, P, j, k);
        }

        StdDraw.show(0);
    }
}