import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static long MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        long[][][] DP = new long[n][4][4];

        DP[0][0][0] = 1;
        DP[0][1][1] = 1;
        DP[0][3][0] = 1;
        DP[0][0][3] = 1;
        DP[0][3][3] = 1;

        for (int i = 1; i < DP.length; i++) {
            DP[i][1][1] = ((((DP[i - 1][1][1] + DP[i - 1][2][2]) % MOD + DP[i - 1][2][3]) % MOD + DP[i - 1][3][2]) % MOD + DP[i-1][3][3])%MOD;
            DP[i][0][0] = DP[i][1][1];
            DP[i][0][3] = DP[i][1][1];
            DP[i][3][0] = DP[i][1][1];
            DP[i][3][3] = DP[i][1][1];
            DP[i][2][2] = DP[i - 1][0][0];
            DP[i][2][3] = DP[i][2][0] = (DP[i - 1][0][2] + DP[i - 1][0][3])%MOD;
            DP[i][0][2] =DP[i][3][2] = (DP[i - 1][2][0] + DP[i - 1][3][0])%MOD;
        }

        long ans = ((((DP[n - 1][1][1] + DP[n - 1][2][2])%MOD + DP[n - 1][2][3])%MOD + DP[n - 1][3][2])%MOD + DP[n-1][3][3])%MOD;
        res.append(ans);
    }

    // 아래는 시간초과 풀이
    private static void solution2(int n) {
        long[] DP = new long[(int)(n + 1)];
        long ans = s(DP, n);
        res.append(ans);
    }

    private static long s(long[] DP, long n) {
        if (DP[(int) n] != 0) {
            return DP[(int) n];
        }
        long sum = atomic(n);
        for (int i = 1; i < n; i++) {
            sum += (atomic(i) * s(DP, n - i)) % MOD;
            sum %= MOD;
        }

        DP[(int)n] = sum;
        return sum;
    }

    private static long atomic(long n) {
        return n == 2 ? 3 : 2;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}