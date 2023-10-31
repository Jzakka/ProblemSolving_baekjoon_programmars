import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        BigInteger[] DP = new BigInteger[68];

        solution(DP, 68);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            res.append(DP[n]).append("\n");
        }

        printRes();
    }

    private static void solution(BigInteger[] DP, int n) {
        DP[0] = DP[1] = new BigInteger("1");
        DP[2] = new BigInteger("2");
        DP[3] = new BigInteger("4");

        for (int i = 4; i < n; i++) {
            DP[i] = DP[i - 1].add(DP[i - 2]).add(DP[i - 3]).add(DP[i - 4]);
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