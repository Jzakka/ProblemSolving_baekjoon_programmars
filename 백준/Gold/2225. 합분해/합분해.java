import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        String[] inputs = br.readLine().split("\\s+");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);

        solution(n, k);

        printRes();
    }

    private static void solution(int n, int k) {
        long[][] DP = new long[201][201];

        long ans = f(DP, n, k);
        res.append(ans);
    }

    private static long f(final long[][] DP,  int n, int k){
        if (DP[n][k] != 0) {
            return DP[n][k];
        }

        if (k == 0 || k == 1) {
            return k;
        }

        for (int i = 0; i <= n; i++) {
            DP[n][k] += f(DP, i, 1) * f(DP, n - i, k - 1);
            DP[n][k] %= MOD;
        }
        return DP[n][k];
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}