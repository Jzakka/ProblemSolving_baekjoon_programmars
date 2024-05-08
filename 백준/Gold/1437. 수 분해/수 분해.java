import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int ans = solution(n);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static int solution(int n) {
        if (n < 3) {
            return n;
        }
        return (int) calculate(n);
    }

    private static long calculate(long num) {
        long base = 3;
        long q = num / base;
        long remain = num - base * q;

        if (remain == 1) {
            return (power(base, (q - 1)) * 4) % MOD;
        } else if (remain == 2) {
            return power(base, q) * remain % MOD;
        }

        return power(base, q);
    }

    public static long power(long base, long exp) {
        if (exp == 1) {
            return base % MOD;
        } else if (exp == 0) {
            return 1;
        }

        if ((exp & 1) == 1) {
            long subPowered = power(base, (exp - 1) / 2);
            return ((subPowered * subPowered) % MOD * base) % MOD;
        }

        long subPowered = power(base, exp / 2);
        return (subPowered * subPowered) % MOD;
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}