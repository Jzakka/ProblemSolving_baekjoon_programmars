class Solution {
    long[][][] DP;
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};
    public int[] solution(int m, int n, int s, int[][] time_map) {
        DP = new long[m][n][m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n * m; k++) {
                    DP[i][j][k] = s + 1;
                }
            }
        }
        DP[0][0][0] = 0;

        for (int path = 1; path < n * m; path++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (time_map[i][j] == -1) {
                        continue;
                    }
                    long minVal = Integer.MAX_VALUE;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (available(nx, ny, m, n)) {
                            minVal = Math.min(minVal, DP[nx][ny][path - 1]);
                        }
                    }

                    DP[i][j][path] = Math.min(DP[i][j][path], minVal + time_map[i][j]);
                }
            }
        }

        for (int i = 0; i < n * m; i++) {
            if (DP[m - 1][n - 1][i] <= s) {
                return new int[]{i, (int) DP[m - 1][n - 1][i]};
            }
        }

        return null;
    }

    private boolean available(int x, int y, int m, int n) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }
}