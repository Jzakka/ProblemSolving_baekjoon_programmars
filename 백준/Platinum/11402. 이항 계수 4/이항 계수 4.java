import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        long[] nkm = Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
        long n = nkm[0];
        long k = nkm[1];
        long m = nkm[2];

        solution(n, k, (int)m);

        printRes();
    }

    private static void solution(long n, long k, int m) {
        long[][] DP = new long[m][m+1];

        for (int i = 0; i < DP.length; i++) {
            DP[i][0] = DP[i][i] = 1;
        }

        for (int i = 2; i < DP.length; i++) {
            for (int j = 1; j < i; j++) {
                DP[i][j] = (DP[i-1][j] + DP[i-1][j-1]) % m;
            }
        }

        long ans = 1;
        long bigger = Math.max(n, k);
        while (bigger > 0) {
            long ni = n % m;
            long ki = k % m;

            if (ni < ki) {
                res.append(0);
                return;
            }

            ans *= DP[(int) ni][(int) ki];
            ans %= m;
            n /= m;
            k /= m;
            bigger /= m;
        }

        res.append(ans);
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