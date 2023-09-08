import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);
        printRes();
    }

    static class Coordinate{
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void solution(int n) {
        int[][] DP = new int[2000][2000];
        for (int i = 0; i < DP.length; i++) {
            for (int j = 0; j < DP.length; j++) {
                DP[i][j] = Integer.MAX_VALUE;
            }
        }

        DP[1][0] = 0;
        bfs(DP);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < DP.length; i++) {
            ans = Math.min(DP[n][i], ans);
        }
        res.append(ans);
    }

    private static int bfs(int[][] DP) {
        Queue<Coordinate> Q = new LinkedList<>();
        Q.add(new Coordinate(1, 0));

        int level = 0;
        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                Coordinate cur = Q.poll();

                int x = cur.x;
                int y = cur.y;

                if (x == DP.length - 1) {
                    return level;
                }

                if (DP[x][x] > DP[x][y] + 1) {
                    DP[x][x] = DP[x][y] + 1;
                    Q.add(new Coordinate(x, x));
                }

                if (x + y < DP.length && DP[x + y][y] > DP[x][y] + 1) {
                    DP[x + y][y] = DP[x][y] + 1;
                    Q.add(new Coordinate(x + y, y));
                }

                if (x - 1 >= 0 && DP[x - 1][y] > DP[x][y] + 1) {
                    DP[x - 1][y] = DP[x][y] + 1;
                    Q.add(new Coordinate(x - 1, y));
                }
            }
            level++;
        }
        return -1;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}