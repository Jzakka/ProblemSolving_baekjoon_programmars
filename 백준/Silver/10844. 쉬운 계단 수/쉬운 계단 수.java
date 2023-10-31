import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        long[][] DP = new long[10][n + 1];

        for (int i = 0; i <= 9; i++) {
            DP[i][1] = 1;
        }

        for (int i = 1; i < n; i++) {
            int l = 0;
            DP[l + 1][i + 1] += DP[l][i];
            DP[l + 1][i + 1] %= MOD;
            l++;

            while (l < 9) {
                DP[l + 1][i + 1] += DP[l][i];
                DP[l + 1][i + 1] %= MOD;

                DP[l - 1][i + 1] += DP[l][i];
                DP[l - 1][i + 1] %= MOD;

                l++;
            }

            //l == 9
            DP[l - 1][i + 1] += DP[l][i];
            DP[l - 1][i + 1] %= MOD;
        }

        long ans = 0;
        for (int i = 1; i <= 9; i++) {
            ans += DP[i][n];
            ans %= MOD;
        }

        res.append(ans);
    }

    /*
    DP[l][len] : 가장 왼쪽수가 l이고 길이가 len인 계단 수 개수
     */


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