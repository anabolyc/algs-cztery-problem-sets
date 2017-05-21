public class PointSET {
    
    private SET<Point2D> points;
    
    // construct an empty set of points
    public PointSET() {
        points = new SET<Point2D>();
    }   
    
    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }   
    
    // number of points in the set
    public int size() {
        return points.size();
    }   
    
    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        points.add(p);
    }   
    
    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return points.contains(p);
    }   
    
    // draw all of the points to standard draw
    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for (Point2D p: points)
            p.draw();
    }   
    
    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> result = new Stack<Point2D>();
        for (Point2D p : points)
            if (rect.contains(p))
                result.push(p);
        return result;
    }   
    
    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty())
            return null;
        
        Point2D result = points.min();
        for (Point2D p0 : points)
            if (p0.distanceSquaredTo(p) < result.distanceSquaredTo(p))
                result = p0;
        return result;
    }   
}
