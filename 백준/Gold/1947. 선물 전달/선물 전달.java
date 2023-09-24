import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        if (n < 2) {
            res.append(0);
            return;
        }
        long[] DP = new long[n + 1];
        DP[2] = 1;

        for (int i = 3; i < DP.length; i++) {
            DP[i] = (i - 1) * ((DP[i - 1] + DP[i - 2]) % MOD) % MOD;
        }

        res.append(DP[n]);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}