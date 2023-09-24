import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000;

    public static void main(String[] args) throws Exception {
        int[][][] DP = new int[101][101][2];

        int t = Integer.parseInt(br.readLine());
        solution(DP);
        for (int i = 0; i < t; i++) {
            int[] nk = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int n = nk[0];
            int k = nk[1];

            res.append(DP[n][k][0] + DP[n][k][1]).append("\n");
        }

        printRes();
    }

    private static void solution(int[][][] DP) {
        DP[1][0][1] = 1;
        DP[1][0][0] = 1;

        for (int i = 2; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                DP[i][j][1] = DP[i - 1][j][0] + (j - 1 >= 0 ? DP[i - 1][j - 1][1] : 0);
                DP[i][j][0] = DP[i - 1][j][0] + DP[i - 1][j][1];
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}