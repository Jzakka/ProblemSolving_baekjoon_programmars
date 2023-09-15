import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;
    public static void main(String[] args) throws Exception {
        long n, p, q;
        long[] inputs = Arrays.stream(br.readLine().split("\\s+")).mapToLong(Long::parseLong).toArray();
        n = inputs[0];
        p = inputs[1];
        q = inputs[2];

        solution(n,p,q);

        printRes();
    }

    private static void solution(long n, long p, long q) {
        Map<Long, Long> DP = new HashMap<>();

        long ans = topDown(DP, n, p, q);

        res.append(ans);
    }

    private static long topDown(Map<Long, Long> DP, long n, final long p, final long q) {
        if (n == 0) {
            return 1;
        }
        if (DP.containsKey(n)) {
            return DP.get(n);
        }

        long left = topDown(DP, n / p, p, q);
        long right = topDown(DP, n / q, p, q);

        long ans = left + right;
        DP.put(n, ans);
        return ans;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}