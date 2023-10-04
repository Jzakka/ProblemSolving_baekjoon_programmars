import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int m = info[1];
        int[] bids = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(bids, m);

//        int[] bids = new int[300];
//        Random random = new Random();
//
//        for (int i = 0; i < bids.length; i++) {
//            bids[i] = random.nextInt(100) + 1;
//        }
//
//        solution(bids, 150);

        printRes();
    }

    private static void solution(int[] bids, int m) {
        int n = bids.length;

        int[][] DP = new int[m][n];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        int[][] groupCounts = new int[m][n];

        int firstRow = 0;
        for (int i = 0; i <= n - m; i++) {
            firstRow += bids[i];
            DP[0][i] = firstRow;
            groupCounts[0][i] = i + 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = i; j <= n - (m - i); j++) {
                int k = j - 1;
                int curGroupSum = bids[j];
                while (0 <= k && DP[i - 1][k] != Integer.MAX_VALUE) {
                    DP[i][j] = Math.min(DP[i][j], Math.max(DP[i - 1][k], curGroupSum));
                    if (DP[i][j] == Math.max(DP[i - 1][k], curGroupSum)) {
                        groupCounts[i][j] = j - k;
                    }
                    curGroupSum += bids[k--];
                }
            }
        }

        res.append(DP[m - 1][n - 1]).append("\n");

        int[] groupCountAns = new int[m];
        int j = n - 1;
        for (int i = m-1; i >= 0 ; i--) {
            groupCountAns[i] = groupCounts[i][j];
            j -= groupCounts[i][j];
        }
        Arrays.stream(groupCountAns).forEach(count -> res.append(count).append(" "));
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}