import java.util.Arrays;

public class Brute {

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
        StdDraw.show(0);
        
        for (int i0 = 0; i0 < N; i0++) 
            for (int i1 = i0 + 1; i1 < N; i1++)
                    for (int i2 = i1 + 1; i2 < N; i2++) {
                        if (Points[i0].slopeTo(Points[i1]) == Points[i1].slopeTo(Points[i2])) {
                            for (int i3 = i2 + 1; i3 < N; i3++) {
                                if (Points[i2].slopeTo(Points[i3]) == Points[i1].slopeTo(Points[i2])) {
                                    StdOut.printf("%s -> %s -> %s -> %s\n", Points[i0], Points[i1], Points[i2], Points[i3]);
                                    Points[i0].drawTo(Points[i3]);
                                    StdDraw.show(0);
                                }
                            }
                        }
                    }
  
    }

}
