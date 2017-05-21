import java.util.Map;
import java.util.TreeMap;

public class WordNet {

    private final static String COMMA = ",";
    private final static String SPACE = " ";
    
    private SAP sap;
    
    private TreeMap<String, Bag<Integer>> wordMap;
        
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        wordMap = new TreeMap<String, Bag<Integer>>();
        
        In in0 = new In(synsets);
        int count = 0;
        while (!in0.isEmpty()) {
            String[] items = in0.readLine().split(COMMA);
            int id = Integer.parseInt(items[0]);
            for (String noun : items[1].split(SPACE)) {
                if (!wordMap.containsKey(noun)) {
                    Bag<Integer> ids = new Bag<Integer>();
                    ids.add(id);
                    wordMap.put(noun, ids);
                } else {
                    wordMap.get(noun).add(id);
                }
                count++;
            }
        }
        
        Digraph G = new Digraph(count);
        
        In in1 = new In(hypernyms);
        while (!in1.isEmpty()) {
            String[] items = in1.readLine().split(COMMA); 
            for (int i = 1; i < items.length; i++)
                G.addEdge(Integer.parseInt(items[0]), Integer.parseInt(items[i]));
        }
        
        sap = new SAP(G);
    }

    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        return wordMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return wordMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new java.lang.IllegalArgumentException();
        
        return sap.length(wordMap.get(nounA), wordMap.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new java.lang.IllegalArgumentException();
        
        int ancestor = sap.ancestor(wordMap.get(nounA), wordMap.get(nounB));
        for (Map.Entry<String, Bag<Integer>> item : wordMap.entrySet())
            for (int id : item.getValue())
                if (id == ancestor)
                    return item.getKey();
        return null;
    }

    // for unit testing of this class
    public static void main(String[] args) {
        WordNet wordNet = new WordNet("c:\\_temp\\synsets.txt", "c:\\_temp\\hypernyms.txt");
        StdOut.println("ready");
        while (!StdIn.isEmpty()) {
            String v = StdIn.readString();
            String w = StdIn.readString();
            int length   = wordNet.distance(v,  w);
            String ancestor = wordNet.sap(v, w);
            StdOut.printf("length = %d, ancestor = %s\n", length, ancestor);
        }
    }
}
