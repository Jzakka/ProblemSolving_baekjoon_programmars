import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution2(n);

        printRes();
    }

    // O(n^2) : 시간초과
    private static void solution(int n) {
        long[] DP = new long[n + 1];
        DP[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                DP[i] += (DP[j] * DP[i - j - 1]) % MOD;
                DP[i] %= MOD;
            }
        }

        res.append(DP[n]);
    }

    // n번째 카탈랑 수는 1/(1+n)*(2n C n)과 같음
    private static void solution2(int n) {
        BigInteger toDivide = IntStream.rangeClosed(1, n).mapToObj(i -> new BigInteger(String.valueOf(i)))
                .reduce(new BigInteger("1"), (result, current) -> result = result.multiply(current));

        BigInteger ways = IntStream.rangeClosed(n + 1, 2 * n).mapToObj(i -> new BigInteger(String.valueOf(i)))
                .reduce(new BigInteger("1"), (result, current) -> result = result.multiply(current))
                .divide(toDivide)
                .divide(new BigInteger(String.valueOf(1 + n)))
                .mod(new BigInteger(String.valueOf(MOD)));

        res.append(ways);
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