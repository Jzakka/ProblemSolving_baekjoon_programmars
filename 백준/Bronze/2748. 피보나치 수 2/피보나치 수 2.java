import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        if (n == 0 || n == 1) {
            res.append(n);
            return;
        }

        BigInteger[] DP = new BigInteger[n + 1];
        DP[0] = new BigInteger("0");
        DP[1] = new BigInteger("1");

        for (int i = 2; i <= n; i++) {
            DP[i] = DP[i - 1].add(DP[i - 2]);
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