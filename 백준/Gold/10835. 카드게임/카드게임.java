import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] left = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] right = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(left, right);
        printRes();
    }

    private static void solution(int[] left, int[] right) {
        int n = left.length;
        IntStream.range(0, left.length/2)
                .forEach(i -> {
                    int temp = left[n - i -1];
                    left[n - i - 1] = left[i];
                    left[i] = temp;

                    temp = right[n - i - 1];
                    right[n - i - 1] = right[i];
                    right[i] = temp;
                });
        int[][] DP = new int[left.length + 1][right.length + 1];

        Arrays.stream(DP).forEach(row -> Arrays.fill(row, -1));
        DP[n][n] = 0;

        for (int i = n; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                if (DP[i][j] == -1) {
                    continue;
                }

                DP[i - 1][j] = Math.max(DP[i][j], DP[i - 1][j]);
                DP[i - 1][j - 1] = Math.max(DP[i - 1][j - 1], DP[i][j]);
                if (left[i - 1] > right[j - 1]) {
                    DP[i][j - 1] = Math.max(DP[i][j] + right[j - 1], DP[i][j - 1]);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = Math.max(ans, Math.max(DP[i][0], DP[0][i]));
        }
        res.append(ans);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}