import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int[] ints = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = ints[0];
        int m = ints[1];
        int k = ints[2];

        solution(n, m, k);

        printRes();
    }

    private static void solution(int n, int m, Integer k) {
        BigInteger[][] DP = new BigInteger[n + 1][m + 1]; // DP[i][j] : a가 i개 있고 z가 j개 있을 떄 가능한 문열들 수

        for (int i = 0; i <= m; i++) {
            DP[0][i] = new BigInteger("1");
        }

        for (int i = 0; i <= n; i++) {
            DP[i][0] = new BigInteger("1");
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                DP[i][j] = DP[i - 1][j].add(DP[i][j - 1]);
            }
        }

        if (DP[n][m].compareTo(new BigInteger(k.toString())) < 0) {
            res.append(-1);
            return;
        }

        int i = n;
        int j = m;
        k = k - 1; // k를 인덱스화

        BigInteger target = new BigInteger(k.toString());

        while (i != 0 && j != 0) {
            BigInteger aArea = DP[i - 1][j];

            if (aArea.compareTo(target) <= 0) { // k가 z범위에 있음
                res.append('z');
                j--;
                target = target.subtract(aArea);
            } else {        //  k가 a범위에 있음
                res.append('a');
                i--;
            }
        }

        if (i == 0) {
            while (j-- > 0) {
                res.append('z');
            }
        } else {
            while (i-- > 0) {
                res.append('a');
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}