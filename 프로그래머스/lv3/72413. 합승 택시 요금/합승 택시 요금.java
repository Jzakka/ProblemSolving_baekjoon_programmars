class Solution {
    int[][] minCosts;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        minCosts = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    minCosts[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int[] fare : fares) {
            int src = fare[0];
            int dest = fare[1];
            int cost = fare[2];

            minCosts[src][dest] = minCosts[dest][src] = cost;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (minCosts[i][j] != Integer.MAX_VALUE && minCosts[i][j] != 0) {
                    for (int k = 1; k <= n; k++) {
                        if (minCosts[k][i] != Integer.MAX_VALUE && minCosts[k][i] + minCosts[i][j] < minCosts[k][j]) {
                            minCosts[k][j] = minCosts[k][i] + minCosts[i][j];
                        }
                    }
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int costA = minCosts[i][a];
            int costB = minCosts[i][b];
            int costS = minCosts[i][s];

            if (costA != Integer.MAX_VALUE && costB != Integer.MAX_VALUE && costS != Integer.MAX_VALUE) {
                minCost = Math.min(costA + costB + costS, minCost);
            }
        }

        return minCost;
    }
}