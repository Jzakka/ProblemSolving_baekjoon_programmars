import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] forests = new int[n][];

        for (int i = 0; i < n; i++) {
            forests[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(forests);

        printRes();
    }

    private static void solution(int[][] forests) {
        int n = forests.length;

        int[][] DP = new int[n][n];

        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (DP[i][j] == 0) {
                    int len = dfs(forests, DP,  i, j, 0);
                    maxLen = Math.max(len, maxLen);
                } else {
                    maxLen = Math.max(DP[i][j], maxLen);
                }
            }
        }

        res.append(maxLen);
    }

    private static int dfs(int[][] forests, int[][] dp,  int x, int y, int len) {
        int maxLen = len + 1;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (available(forests.length, nextX, nextY) && forests[x][y] < forests[nextX][nextY]) {
                if (dp[nextX][nextY] != 0) {
                    maxLen = Math.max(maxLen, len + 1 + dp[nextX][nextY]);
                } else {
                    maxLen = Math.max(maxLen, dfs(forests, dp,nextX, nextY, len + 1));
                }
            }
        }

        dp[x][y] = maxLen - len;
        return maxLen;
    }

    private static boolean available(int n, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}