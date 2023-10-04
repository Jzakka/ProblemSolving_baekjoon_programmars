import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] costs = new int[n][];

        for (int i = 0; i < n; i++) {
            costs[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(costs);

        printRes();
    }

    private static void solution(int[][] costs) {
        int n = costs.length;

        int[][] DP = new int[n][1 << n];

        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE/2));

        for (int i = 0; i < n; i++) {
            DP[0][1 << i] = costs[0][i];
        }

        for (int i = 1; i < DP.length; i++) {
            for (int bits = 1; bits < DP[0].length; bits++) {
                for (int k = 0; k < n; k++) {
                    if (((bits >> k) & 1) == 1) {
                        DP[i][bits] = Math.min(DP[i][bits], DP[i - 1][bits ^ (1 << k)] + costs[i][k]);
                    }
                }
            }
        }

        res.append(DP[n - 1][DP[0].length - 1]);
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}