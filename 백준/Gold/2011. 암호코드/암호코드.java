import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1000000;

    public static void main(String[] args) throws Exception {
        String code = br.readLine();

        solution(code);

        printRes();
    }

    private static void solution(String code) {
        int[] DP = new int[code.length()];
        Arrays.fill(DP, -1);

        int ans = 0;
        if (!(code.charAt(0) == '0')) {
            ans += dfs(DP, code, 0);
            ans %= MOD;
        }

        int skip;
        if (2 <= code.length() &&  (skip = Integer.parseInt(code.substring(0, 2))) <= 26 && skip >= 10) {
            ans += dfs(DP, code, 1);
            ans %= MOD;
        }
        res.append(ans);
    }

    private static int dfs(int[] dp, String code, int i) {
        if (i == code.length()) {
            return 1;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int ans = 0;
        if (!(i < code.length() - 1 && code.charAt(i + 1) == '0')) {
            ans += dfs(dp, code, i + 1);
        }
        ans %= MOD;

        int skip;
        if (i < code.length() - 2 && (skip = Integer.parseInt(code.substring(i + 1, i + 3))) <= 26 && skip >= 10) {
            ans += dfs(dp, code, i + 2);
            ans %= MOD;
        }

        dp[i] = ans;

        return ans;
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}