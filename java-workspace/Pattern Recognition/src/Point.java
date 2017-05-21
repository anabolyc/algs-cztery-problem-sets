/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();
    
    private class BySlope implements Comparator<Point> {
        @Override
        public int compare(Point a, Point b) {
            double slopeA = Point.this.slopeTo(a);
            double slopeB = Point.this.slopeTo(b);
            
            if (slopeA < slopeB)
                return -1;
            else if (slopeA > slopeB)
                return 1;
            else 
                return 0;
        }
    }

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y)
            return Double.NEGATIVE_INFINITY;
        else if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        else
            return (double) (that.y - this.y) / (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    @Override
    public int compareTo(Point that) {
        if (this.y < that.y)
            return -1;
        else if (this.y > that.y)
            return 1;
        else
            if (this.x < that.x)
                return -1;
            else if(this.x > that.x)
                return 1;
            else
                return 0;
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // unit test
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        if (Double.NEGATIVE_INFINITY < Double.NEGATIVE_INFINITY)
            StdOut.print("-");
        else if (Double.NEGATIVE_INFINITY > Double.NEGATIVE_INFINITY)
            StdOut.print("+");
        else
            StdOut.print("=");
    }
}
