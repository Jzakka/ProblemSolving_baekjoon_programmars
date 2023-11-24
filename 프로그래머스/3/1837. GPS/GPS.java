import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    long[][] DP;
    List<Integer>[] graph;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        graph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> graph[i] = new ArrayList<>());
        for (int[] edge : edge_list) {
            int src = edge[0];
            int dest = edge[1];

            graph[src].add(dest);
            graph[dest].add(src);
        }

        DP = new long[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                DP[i][j] = Integer.MAX_VALUE;
            }
        }
        DP[1][gps_log[0]] = 0;

        for (int i = 2; i < DP.length; i++) {
            for (int j = 1; j < DP[0].length; j++) {
                DP[i][j] = min(i - 1, j) + (gps_log[i-1] == j ? 0 : 1);
            }
        }

        if (DP[k][gps_log[k - 1]] >= Integer.MAX_VALUE) {
            return -1;
        }
        return (int) DP[k][gps_log[k - 1]];
    }

    private long min(int time, int node) {
        long min = DP[time][node];
        for (Integer incident : graph[node]) {
            min = Math.min(min, DP[time][incident]);
        }
        return min;
    }
}