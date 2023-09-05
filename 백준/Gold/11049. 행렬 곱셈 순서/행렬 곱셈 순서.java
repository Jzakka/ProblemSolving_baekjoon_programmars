import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] matrices = new int[n][];

        for (int i = 0; i < n; i++) {
            matrices[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(matrices);

        printRes();
    }

    private static void solution(int[][] matrices) {
        int[] p = new int[matrices.length + 1];
        for (int i = 0; i < matrices.length; i++) {
            p[i] = matrices[i][0];
        }
        p[p.length - 1] = matrices[matrices.length - 1][1];

        int[][] DP = new int[p.length][p.length];

        for (int i = 0; i < DP.length; i++) {
            DP[i][i] = 0;
        }

        for (int i = 2; i < DP.length; i++) {
            int[] coord = {1, i};
            while (coord[1] < DP.length) {
                DP[coord[0]][coord[1]] = calc(DP, p, coord);
                coord[0] ++;
                coord[1] ++;
            }
        }

        res.append(DP[1][DP.length - 1]);
    }

    private static int calc(int[][] DP, int[] p, int[] coord) {
        int x = coord[0];
        int y = coord[1];

        int minVal = Integer.MAX_VALUE;

        for (int k = x; k < y; k++) {
            int calcCnt = DP[x][k] + DP[k + 1][y] + p[x - 1] * p[k] * p[y];

            minVal = Math.min(calcCnt, minVal);
        }

        return minVal;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}