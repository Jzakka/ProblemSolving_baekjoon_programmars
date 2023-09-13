import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int[] tw = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] plumbs = new int[tw[0]];

        for (int i = 0; i < tw[0]; i++) {
            plumbs[i] = Integer.parseInt(br.readLine());
        }

        solution(plumbs, tw[1]);

        printRes();
    }
    private static void solution(int[] plumbs, int w) {
        int[][][] DP = new int[plumbs.length + 1][3][w + 1];

        for (int i = 0; i < DP.length-1; i++) {
            for (int j = 1; j <= 2; j++) {
                for (int k = 0; k <= w; k++) {
                    if (((j + k) & 1) == 1 && k <= i) {
                        // 안움직이기
                        DP[i + 1][j][k] = Math.max(DP[i + 1][j][k], DP[i][j][k] + (plumbs[i] == j ? 1 : 0));

                        // 움직이기
                        if (k < w) {
                            DP[i + 1][(j == 1 ? 2 : 1)][k + 1]
                                    = Math.max(DP[i + 1][(j == 1 ? 2 : 1)][k + 1], DP[i][j][k] + (plumbs[i] == (j == 1 ? 2 : 1) ? 1 : 0));
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= w; i++) {
            ans = Math.max(ans, Math.max(DP[DP.length - 1][1][i], DP[DP.length - 1][2][i]));
        }

        res.append(ans);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}