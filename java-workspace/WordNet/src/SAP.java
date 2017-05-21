
public class SAP {
    private Digraph G;
    private int lastRun = -1;
    private PathFinder pathFinder; 
    
    private class PathFinder {
        private BreadthFirstDirectedPaths paths1, paths2;
        private int X = -1;
        private int L = G.E();
        
        private boolean isIndex(int i) {
            return i >= 0 && i < G.V();
        } 
        
        public PathFinder(int v, int w) {
            if (!isIndex(v) || !isIndex(w))
                throw new java.lang.IllegalArgumentException();
            
            paths1 = new BreadthFirstDirectedPaths(G, v); 
            paths2 = new BreadthFirstDirectedPaths(G, w);
            performSearch(v, w);
        }
        
        public PathFinder(Iterable<Integer> v, Iterable<Integer> w) {
            for(int v0 : v)
                if (!isIndex(v0))
                    throw new java.lang.IllegalArgumentException();
            for(int w0 : w)
                if (!isIndex(w0))
                    throw new java.lang.IllegalArgumentException();
            
            paths1 = new BreadthFirstDirectedPaths(G, v); 
            paths2 = new BreadthFirstDirectedPaths(G, w);
            performSearch(v, w);
        }
        
        private void performSearch(int v, int w) {
            for (int x = 0; x < G.V(); x++) {
                if (paths1.hasPathTo(x) && paths2.hasPathTo(x)) {
                    int length = paths1.distTo(x) + paths2.distTo(x);
                    if (length < L) {
                        L = length;
                        X = x;
                    }
                }
            }
        }
        
        private void performSearch(Iterable<Integer> v, Iterable<Integer> w) {
            for (int x = 0; x < G.V(); x++) {
                if (paths1.hasPathTo(x) && paths2.hasPathTo(x)) {
                    int length = paths1.distTo(x) + paths2.distTo(x);
                    if (length < L) {
                        L = length;
                        X = x;
                    }
                }
            }
        }
        
        public int getAncestor() {
            return X;
        }
        
        public int getLength() {
            if (X == -1)
                return -1;
            else
                return L;
        }
    }
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v + w != lastRun) {
            pathFinder = new PathFinder(v, w);
            lastRun = v + w;
        }
        return pathFinder.getLength();
        
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v + w != lastRun) {
            pathFinder = new PathFinder(v, w);
            lastRun = v + w;
        }
        return pathFinder.getAncestor();
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v.hashCode() + w.hashCode() != lastRun) {
            pathFinder = new PathFinder(v, w);
            lastRun = v.hashCode() + w.hashCode();
        }
        return pathFinder.getLength();
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v.hashCode() + w.hashCode() != lastRun) {
            pathFinder = new PathFinder(v, w);
            lastRun = v.hashCode() + w.hashCode();
        }
        return pathFinder.getAncestor();
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
