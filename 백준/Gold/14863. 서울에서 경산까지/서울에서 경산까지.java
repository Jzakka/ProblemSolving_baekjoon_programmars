import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static String str;

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = info[0];
        int k = info[1];

        int[][] sections = new int[n][];

        for (int i = 0; i < n; i++) {
            sections[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(sections, k);

        printRes();
    }

    private static void solution(int[][] sections, int k) {
        int n = sections.length;
        long[][] DP = new long[n + 1][k + 1];

        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Long.MIN_VALUE));
        DP[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            int _i = i - 1;
            int walkTime = sections[_i][0];
            int walkCost = sections[_i][1];
            int bikeTime = sections[_i][2];
            int bikeCost = sections[_i][3];

            for (int j = 0; j <= k; j++) {
                long walkTotal = Long.MIN_VALUE;
                long bikeTotal = Long.MIN_VALUE;

                if (j - walkTime >= 0) {
                     walkTotal = DP[i - 1][j - walkTime] + walkCost;
                }
                if (j - bikeTime >= 0) {
                     bikeTotal = DP[i - 1][j - bikeTime] + bikeCost;
                }

                DP[i][j] = Math.max(walkTotal, bikeTotal);
            }
        }

        res.append(Arrays.stream(DP[n]).max().getAsLong());
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
