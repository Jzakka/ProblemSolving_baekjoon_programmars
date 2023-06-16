import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] resultTable = new int[n + 1][n + 1];

        for (int[] result : results) {
            int winner = result[0];
            int loser = result[1];
            resultTable[winner][loser] = 1;
            resultTable[loser][winner] = -1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int result = resultTable[j][i];
                if (result == 1) {
                    for (int k = 1; k <= n; k++) {
                        if (resultTable[i][k] == 1) {
                            resultTable[j][k] = 1;
                            resultTable[k][j] = -1;
                        }
                    }
                }
            }
        }

        int confirmed = 0;
        for (int i = 1; i <= n; i++) {
            long count = Arrays.stream(resultTable[i]).filter(res -> res != 0).count();
            if (count == n - 1) {
                confirmed++;
            }
        }

        return confirmed;
    }
}