public class KdTree {

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        
        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }
    
    private static final boolean HORIZONTAL = true;
    private int size = 0; 
    private Node root = null;
    
    // construct an empty set of points
    public KdTree() {

    }   
    
    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }   
    
    // number of points in the set
    public int size() {
        return size;
    }   
    
    private Node insert(Point2D p, Node node, Node parent, boolean orientation) {
        if (node == null) {
            size++;
            RectHV rect0;
            if (parent == null) 
                rect0 = new RectHV(0, 0, 1, 1);
            else
                if (!orientation == HORIZONTAL)
                    if (p.x() < parent.p.x())
                        rect0 = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
                    else
                        rect0 = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
                else
                    if (p.y() < parent.p.y())
                        rect0 = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
                    else
                        rect0 = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
            return new Node(p, rect0);
        }
        if (node.p.equals(p)) 
            return node;
        
        if (orientation == HORIZONTAL)
            if (p.x() < node.p.x())
                node.lb = insert(p, node.lb, node, !orientation);
            else
                node.rt = insert(p, node.rt, node, !orientation);
        else
            if (p.y() < node.p.y())
                node.lb = insert(p, node.lb, node, !orientation);
            else
                node.rt = insert(p, node.rt, node, !orientation);
        return node;
    }
    
    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        root = insert(p, root, null, HORIZONTAL);
    }   
    
    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return get(root, p, HORIZONTAL);
    }   

    private boolean get(Node node, Point2D p, boolean orientation) {
        if (node == null) 
            return false;
        if (node.p.equals(p)) 
            return true;
                
        if (orientation == HORIZONTAL)
            if (p.x() < node.p.x())
                return get(node.lb, p, !orientation);
            else
                return get(node.rt, p, !orientation);
        else
            if (p.y() < node.p.y())
                return get(node.lb, p, !orientation);
            else
                return get(node.rt, p, !orientation);
    }
    
    // draw all of the points to standard draw
    public void draw() {
        draw(root, HORIZONTAL);
    }   
    
    private void draw(Node node, boolean orientation) {
        if (node == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        node.p.draw();
        StdDraw.setPenRadius();
        if (orientation == HORIZONTAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }
        
        draw(node.lb, !orientation);
        draw(node.rt, !orientation);
    }
    
    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return range(rect, root);
    }   
    
    private Stack<Point2D> range(RectHV rect, Node node) {
        Stack<Point2D> result = new Stack<Point2D>();
        if (node == null)
            return result;
        if (!node.rect.intersects(rect))
            return result;
        if (rect.contains(node.p))
            result.push(node.p);
        for (Point2D p : range(rect, node.lb))
            result.push(p);
        for (Point2D p : range(rect, node.rt))
            result.push(p);
        return result;
    }
    
    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty())
            return null;
        
        return nearest(p, root, p.distanceSquaredTo(root.p), HORIZONTAL);
    }
    
    // may be use point reference instead of lessThan
    private Point2D nearest(Point2D p, Node node, double lessThan, boolean orientation) {
        if (node == null)
            return null;
        
        if (node.rect.distanceSquaredTo(p) > lessThan)
            return null;
        
        Point2D result = node.p;
        double lessthan = Math.min(lessThan, node.p.distanceSquaredTo(p));

        boolean goLeftFirst;
        if (orientation == HORIZONTAL)
            goLeftFirst = (p.x() < node.p.x());
        else
            goLeftFirst = (p.y() < node.p.y());

        // navigate to nearest tree
        Point2D p0 = nearest(p, goLeftFirst ? node.lb : node.rt, lessthan, !orientation);
        if (p0 != null) {
            double newdistance = p0.distanceSquaredTo(p); 
            if (newdistance < lessthan) {
                result = p0;
                lessthan = newdistance;
            }
        }
        
        Point2D p1 = nearest(p, goLeftFirst ? node.rt : node.lb, lessthan, !orientation);
        if (p1 != null) {
            double newdistance = p1.distanceSquaredTo(p);
            if (newdistance < lessthan) {
                result = p1;    
            }
        }
        return result;
    }
}
