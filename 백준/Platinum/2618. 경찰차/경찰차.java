import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int w = Integer.parseInt(br.readLine());
        int[][] coord = new int[w][];

        for (int i = 0; i < w; i++) {
            coord[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(n, coord);

        printRes();
    }

    private static void solution(int n, int[][] happening) {
        int[][] car1 = new int[happening.length + 1][];
        int[][] car2 = new int[happening.length + 1][];
        car1[0] = new int[]{1, 1};
        car2[0] = new int[]{n, n};

        for (int i = 0; i < happening.length; i++) {
            car1[i + 1] = car2[i + 1] = happening[i];
        }

        // DP[i][j] : 경찰차1이 i번 사건을 마지막으로 처리하고 경찰차2가 j번 사건을 마지막으로 처리했을 떄 최소 이동거리 합
        int[][] DP = new int[car1.length][car1.length];
        int[][][] trace = new int[car1.length][car1.length][2];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));

        DP[0][0] = 0;

        for (int i = 0; i < DP.length-1; i++) {
            for (int j = 0; j < DP.length-1; j++) {
                if (DP[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                int next = Math.max(i, j) + 1;
                if (DP[next][j] > DP[i][j] + distance(car1[i], car1[next])) {
                    DP[next][j] = DP[i][j] + distance(car1[i], car1[next]);
                    trace[next][j][0] = i;
                    trace[next][j][1] = j;
                }
                if (DP[i][next] > DP[i][j] + distance(car2[j], car2[next])) {
                    DP[i][next] = DP[i][j] + distance(car2[j], car2[next]);
                    trace[i][next][0] = i;
                    trace[i][next][1] = j;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        int x = -1;
        int y = -1;
        for (int i = 0; i <= happening.length; i++) {
            ans = Math.min(ans, Math.min(DP[happening.length][i], DP[i][happening.length]));

            if (ans == DP[happening.length][i]) {
                x = happening.length;
                y = i;
            } else if (ans == DP[i][happening.length]) {
                x = i;
                y = happening.length;
            }
        }

        int[] selected = new int[happening.length + 1];
        while (!(x == 0 && y == 0)) {
            if (x != 0) {
                selected[x] = 1;
            }
            if (y != 0) {
                selected[y] = 2;
            }
            int[] prev = trace[x][y];
            x = prev[0];
            y = prev[1];
        }

        res.append(ans).append("\n");
        for (int i = 1; i < selected.length; i++) {
            res.append(selected[i]).append("\n");
        }
    }

    private static int distance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}