
public class Outcast {
    
    private WordNet wordNet;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int index = 0, maxindex = -1, maxdist = 0;
        for (String noun : nouns) {
            int dist = 0;
            for (String noun0: nouns)
                if (noun0.equals(noun))
                    dist += wordNet.distance(noun, noun0);                    
            if (dist > maxdist) {
                maxindex = index;
                maxdist = dist; 
            }
                
            index++;
        }
        return nouns[maxindex];
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
