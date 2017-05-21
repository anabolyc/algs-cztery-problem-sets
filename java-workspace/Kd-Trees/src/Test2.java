
public class Test2 {
    public static void main(String[] args) {
        for (int N = 1; N < 1e10; N*=2) {
            int sum = 0;
            for (int i = 1; i*i <= N; i = i*2)
                for (int j = 0; j < i; j++)
                    sum++;
            StdOut.printf("%s\t%s\n", N, sum);
        }
    }
}
