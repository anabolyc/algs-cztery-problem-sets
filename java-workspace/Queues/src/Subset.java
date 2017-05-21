
public class Subset {
    public static void main(String[] args) {
        int K = Integer.parseInt(args[0]);
        RandomizedQueue<String> engine = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
        //String[] items = StdIn.readString().split(" ");
            engine.enqueue(StdIn.readString());
        }
        //for (String item: items)
         //  engine.enqueue(item);
        for (int i = 0; i < K; i++)
            StdOut.println(engine.dequeue());
    }
}
