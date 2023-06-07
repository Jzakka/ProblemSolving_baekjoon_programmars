import java.util.*;

class Solution {
    final long DIVIDER = 1_000_000_007L;
    public int solution(int m, int n, int[][] puddles) {
        long[][] DP = new long[n][m];
        Set<Integer> puddleSet = new HashSet<>();

        for (int[] puddle : puddles) {
            puddleSet.add(cantorPair(puddle[1] - 1, puddle[0] - 1));
        }

        boolean colBloked = false;
        for (int i = 0; i < n; i++) {
            if (puddleSet.contains(cantorPair(i, 0))) {
                colBloked = true;
            }
            if (!colBloked) {
                DP[i][0] = 1;
            }
        }

        boolean rowBlocked = false;
        for (int i = 0; i < m; i++) {
            if (puddleSet.contains(cantorPair(0, i))) {
                rowBlocked = true;
            }
            if (!rowBlocked) {
                DP[0][i] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (!puddleSet.contains(cantorPair(i, j))) {
                    DP[i][j] = DP[i - 1][j] + DP[i][j - 1];
                    if (DP[i][j] > DIVIDER) {
                        DP[i][j] %= DIVIDER;
                    }
                }
            }
        }


        return (int) (DP[n - 1][m - 1] % DIVIDER);
    }

    private int cantorPair(int i, int j) {
        return (i + j) * (i + j + 1) / 2 + j;
    }
}