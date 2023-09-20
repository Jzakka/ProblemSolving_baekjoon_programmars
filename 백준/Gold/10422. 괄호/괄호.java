import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        long[] DP = new long[5001];
        solution(DP);

        for (int i = 0; i < n; i++) {
            res.append(DP[Integer.parseInt(br.readLine())]).append("\n");
        }

        printRes();
    }

    private static void solution(long[] DP) {
        DP[0] = 1;

        for (int i = 2; i <= 5000; i+=2) {
            for (int j = 0; j < (i-2)/2; j+=2) {
                DP[i] += (2 * (DP[j] * DP[i - 2 - j])%MOD)%MOD;
                DP[i] %= MOD;
            }
            DP[i] += (DP[(i - 2) / 2] * DP[(i - 2) / 2]) % MOD;
            DP[i] %= MOD;
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}