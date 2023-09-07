import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1000000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] numbers = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(n, numbers);

        printRes();
    }

    private static void solution(int n, int[] numbers) {
        int right = numbers[n - 1];
        long[][] DP = new long[n][21];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, -1));

        long ans = dfs(DP, numbers, right, 0, numbers[0]);
        res.append(ans);
    }

    private static long dfs(long[][] dp, int[] numbers, final int right, int curIdx, int sum) {
        if (curIdx == numbers.length - 2) {
            return sum == right ? 1 : 0;
        }

        if (sum > 20 || sum < 0) {
            return 0;
        }

        if (dp[curIdx][sum] != -1) {
            return dp[curIdx][sum];
        }
        long cases = 0;
        cases += dfs(dp, numbers, right, curIdx + 1, sum + numbers[curIdx + 1]);
        cases += dfs(dp, numbers, right, curIdx + 1, sum - numbers[curIdx + 1]);

        dp[curIdx][sum] = cases;
        return cases;
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}