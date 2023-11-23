import java.util.*;

class Solution {
    int MOD = 20170805;
    int[][][] DP;
    final int DOWN = 0;
    final int RIGHT = 1;

    public int solution(int m, int n, int[][] cityMap) {
        DP = new int[m][n][2];

        for (int i = 0; i < n && cityMap[0][i] != 1; i++) {
            DP[0][i][RIGHT] = 1;
        }

        for (int i = 0; i < m && cityMap[i][0] != 1; i++) {
            DP[i][0][DOWN] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i][j] != 1) {
                    DP[i][j][DOWN] = move(cityMap, i - 1, j, DOWN);
                    DP[i][j][RIGHT] = move(cityMap, i, j - 1, RIGHT);
                }
            }
        }

        return Arrays.stream(DP[m - 1][n - 1]).sum() % MOD;
    }

    private int move(int[][] cityMap, int x, int y, int direction) {
        if (cityMap[x][y] == 0) {
            return (DP[x][y][DOWN] + DP[x][y][RIGHT]) % MOD;
        } else if (cityMap[x][y] == 2) {
            return DP[x][y][direction];
        }

        return 0;
    }
}