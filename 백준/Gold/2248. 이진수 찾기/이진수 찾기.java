import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final long MOD = 987654321L;

    public static void main(String[] args) throws Exception {
        long n, l, i;

        long[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
        n = info[0];
        l = info[1];
        i = info[2];

        // i번째 수는 i번 인덱스의 수이다.
        solution((int) n, (int) l, (int) (i - 1));

        printRes();
    }

    private static void solution(int n, int l, int I) {
        BigInteger[][] DP = new BigInteger[n + 1][l + 1];
        BigInteger one = new BigInteger("1");
        for (int i = 0; i <= n; i++) {
            DP[i][0] = one;
        }
        for (int i = 0; i <= l; i++) {
            DP[0][i] = one;
        }

        for (int i = 1; i < DP.length; i++) {
            for (int j = 1; j < DP[0].length; j++) {
                DP[i][j] = DP[i - 1][j - 1].add(DP[i - 1][j]);
            }
        }

        int x = n;
        int y = l;

        BigInteger idx = new BigInteger(String.valueOf(I));
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            if (idx.compareTo(DP[x - 1][y])<0) {
                x--;
                sb.append("0");
            } else {
                idx = idx.subtract(DP[x - 1][y]);
                x--;
                y--;
                sb.append("1");
            }
        }

        res.append(sb);
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
