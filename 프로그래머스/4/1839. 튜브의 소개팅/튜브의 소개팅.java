import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    class Coord{
        int x,y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] solution(int m, int n, int s, int[][] time_map) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};
        final int MAX_PATH_LEN = m * n;
        long[][][] DP = new long[m][n][MAX_PATH_LEN + 1];
        for (int i = 0; i < m; i++) {
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 <= MAX_PATH_LEN; i2++) {
                    DP[i][i1][i2] = s + 1L;
                }
            }
        }

        DP[0][0][0] = 0;

        for (int k = 1; k <= MAX_PATH_LEN; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (time_map[i][j] != -1) {
                        for (int d = 0; d < 4; d++) {
                            int px = i + dx[d];
                            int py = j + dy[d];

                            if (available(px, py, m, n, time_map)) {
                                DP[i][j][k] = Math.min(DP[i][j][k], DP[px][py][k - 1] + time_map[i][j]);
                            }
                        }
                    }
                }
            }
        }

        int k = 0;
        for (; k <= MAX_PATH_LEN; k++) {
            if (DP[m - 1][n - 1][k] <= s) {
                break;
            }
        }

        return new int[]{k, (int) DP[m - 1][n - 1][k]};
    }

    private boolean available(int x, int y, int m, int n, int[][] time_map) {
        return 0 <= x && x < m && 0 <= y && y < n && time_map[x][y] != -1;
    }
}
