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
        int[] DP = new int[n + 1];
        Arrays.fill(DP, 4);
        DP[0] = 0;

        for (int i = 1; i*i <= n; i++) {
            int sqaure = i * i;

            for (int j = sqaure; j <= n; j++) {
                DP[j] = Math.min(DP[j], DP[j - sqaure] + 1);
            }
        }

        res.append(DP[n]);
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