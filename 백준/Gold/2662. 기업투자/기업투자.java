import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = info[0];
        int m = info[1];

        int[][] rate = new int[n][];
        for (int i = 0; i < n; i++) {
            rate[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        solution(rate, m);

        printRes();
    }

    private static void solution(int[][] rate, int m) {
        int n = rate.length;

        int[][] profitRate = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                profitRate[i][j] = rate[j-1][i];
            }
        }

        // DP[i][j] : i번기업까지 투자할 수 있고, j원 투자했을 떄 최대 수익
        int[][] DP = new int[m + 1][n + 1];
        int[][][] trace = new int[m + 1][n + 1][2];

        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Integer.MIN_VALUE));
        DP[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                for (int k = 0; k <= j; k++) {
                    int investedProfit = DP[i - 1][k] + profitRate[i][j - k];
                    DP[i][j] = Math.max(DP[i][j], investedProfit);
                    trace[i][j][0] = investedProfit == DP[i][j] ? i - 1 : trace[i][j][0];
                    trace[i][j][1] = investedProfit == DP[i][j] ? k : trace[i][j][1];
                }

            }
        }

        int[] invests = new int[m + 1];
        int i = m;
        int j = n;

        while (i != 0 && j != 0) {
            int nexti = trace[i][j][0];
            int nextj = trace[i][j][1];

            if (nextj != j) {
                invests[i] = j - nextj;
            }

            i = nexti;
            j = nextj;
        }

        res.append(DP[m][n]).append("\n");
        IntStream.rangeClosed(1, m).forEach(company -> res.append(invests[company]).append(" "));
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}