import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final String UNIST = "UNIST";
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        long k = Long.parseLong(br.readLine().split("\\s+")[1]);
        long[] v = Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();

        solution(k, v);

        printRes();
    }

    private static void solution(long k, long[] v) {
        Arrays.sort(v);

        long maxPerformance = 0;

        for (int i = 1; i < v.length; i++) {
            long aSpeed = v[0] * i;
            long bSpeed = v[i] * (v.length - i);

            if (maxPerformance < aSpeed + bSpeed) {
                maxPerformance = aSpeed + bSpeed;
            }
        }

        long r = k % maxPerformance;
        long q = k / maxPerformance;

        res.append(r == 0 ? q : q + 1);
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
