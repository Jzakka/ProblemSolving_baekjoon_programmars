import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        int[] inputs = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        long ans = power(inputs[0], inputs[1], inputs[2]);
        res.append(ans);

//        int[] nkm = Arrays.stream(br.readLine().split("\\s+"))
//                .mapToInt(Integer::parseInt)
//                .toArray();
//        int n = nkm[0];
//        int k = nkm[1];
//        int m = nkm[2];
//
//        solution(n, k, m);

        printRes();
    }

    private static long power(int mit, int jisu, int mod) {
        if (jisu == 0) {
            return 1;
        }
        if ((jisu & 1) == 1) {
            return mit * power(mit, jisu - 1, mod) % mod;
        } else {
            long powed = power(mit, jisu / 2, mod);
            return powed * powed % mod;
        }
    }

    private static void solution(int n, int k, int m) {
        int[][] DP = new int[m + 1][m + 1];

        for (int i = 1; i <= m; i++) {
            DP[i][1] = DP[i][i] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 2; j < i; j++) {
                DP[i][j] = (DP[i - 1][j - 1] + DP[i - 1][j]) % m;
            }
        }

        n %= m;
        k %= m;

        if (n == 0) {
            n += m;
        }
        if (k == 0) {
            k += m;
        }
        res.append(DP[n][k]);
    }

    /*
1           1         m = 5
2          1 1
3         1 2 1
4        1 3 3 1
5       1 4 1 4 1
6      1 0 0 0 0 1
7     1 1 0 0 0 1 1
8    1 2 1 0 0 1 2 1
9   1 3 3 1 0 1 3 3 1
10 1 4 1 4 1 1 4 1 4 1

     */

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}