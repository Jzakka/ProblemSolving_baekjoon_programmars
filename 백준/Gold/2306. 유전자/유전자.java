import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static String str;

    public static void main(String[] args) throws Exception {
        str = br.readLine();

        solution();

        printRes();
    }

    private static void solution() {
        int[][] DP = new int[str.length() + 1][str.length() + 1];

        Arrays.stream(DP).forEach(row -> Arrays.fill(row, -1));

        int ans = koi(DP, str, 0, str.length());

        res.append(ans);
    }

    private static int koi(int[][] dp, final String str, int start, int end) {
        if (end - start <= 1) {
            return 0;
        }
        if (dp[start][end] != -1) {
            return dp[start][end];
        }

        if (isSideKOI(str, start, end)) {
            dp[start][end] = koi(dp, str, start + 1, end - 1) + 2;
        }

        for (int k = start + 1; k < end; k++) {
            int left = koi(dp, str, start, k);
            int right = koi(dp, str, k, end);

            dp[start][end] = Math.max(dp[start][end], left + right);
        }

        return dp[start][end];
    }

    private static boolean isSideKOI(final String str, int start, int end) {
        return (str.charAt(start) == 'a' && str.charAt(end - 1) == 't')
                || (str.charAt(start) == 'g' && str.charAt(end - 1) == 'c');
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
