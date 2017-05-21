
public class Test1 {

    public static void main(String[] args) {
        String filename = "c:\\_temp\\kdtree\\input1M.txt";
        In in = new In(filename);
        Stopwatch sw;
        
        String format = "points loaded: %s points in %.2f seconds\n";
        sw = new Stopwatch();
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            Point2D p = new Point2D(in.readDouble(), in.readDouble());
            brute.insert(p);
            kdtree.insert(p);
        }
        StdOut.printf(format, brute.size(), sw.elapsedTime());
        
        RectHV rect = new RectHV(0.4, 0.4, 0.6, 0.6);
        format = "points hit: %s;     time spent: %.3f sec;\n";
        
        sw = new Stopwatch();
        int count = 0;
        for (Point2D p: brute.range(rect))
            count++;

        StdOut.printf(format, count, sw.elapsedTime());
        
        count = 0;
        sw = new Stopwatch();
        for (Point2D p : kdtree.range(rect))
            count++;

        StdOut.printf(format, count, sw.elapsedTime());

        //------------------------------------------------------------------------------------------
        Point2D query = new Point2D(0.3, 0.3);
        format = "found point (%.7f, %.7f) in %.3f seconds\n";
        
        sw = new Stopwatch();
        Point2D p1 = brute.nearest(query);
        StdOut.printf(format, p1.x(), p1.y(), sw.elapsedTime());
        
        sw = new Stopwatch();
        Point2D p2 = kdtree.nearest(query);
        StdOut.printf(format, p2.x(), p2.y(), sw.elapsedTime());
        
        sw = new Stopwatch();
        for (int i = 1; i < 1000; i++) {
            query = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            kdtree.nearest(query);
        }
        StdOut.printf("made 1000 queries in %.3f seconds", sw.elapsedTime());
    }

}
