import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        long[] DP = new long[1_000_001];
        DP[0] = 1l;
        solution(DP, 1_000_000);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            res.append(DP[n]).append("\n");
        }

        printRes();
    }

    private static void solution(long[] DP, int n) {
        for (int i = 0; i < n; i++) {
            if (i + 1 < DP.length) {
                DP[i + 1] += DP[i];
                DP[i + 1] %= MOD;
            }

            if (i + 2 < DP.length) {
                DP[i + 2] += DP[i];
                DP[i + 2] %= MOD;
            }

            if (i + 3 < DP.length) {
                DP[i + 3] += DP[i];
                DP[i + 3] %= MOD;
            }
        }
    }

    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static long[] getLongs() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}