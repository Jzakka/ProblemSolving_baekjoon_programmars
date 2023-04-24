class Solution {
    public int solution(int N, int[][] road, int K) {
        int[][] distance = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int[] edge : road) {
            int src = edge[0];
            int dest = edge[1];
            int dist = edge[2];
            if (distance[src][dest] > dist) {
                distance[src][dest] = distance[dest][src] = dist;
            }
        }

        for (int i = 1; i <= N; i++) {
            distance[i][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j && distance[i][j] != Integer.MAX_VALUE) {
                    for (int k = 1; k <= N; k++) {
                        if (distance[k][i] != Integer.MAX_VALUE && distance[k][i] + distance[i][j] < distance[k][j]) {
                            distance[k][j] = distance[j][k] = distance[k][i] + distance[i][j];
                        }
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (distance[1][i] <= K) {
                cnt++;
            }
        }

        return cnt;
    }
}