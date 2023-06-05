class Solution {
    boolean[][] edges = new boolean[201][201];
    boolean[] visited = new boolean[201];

    public int solution(int n, int[][] computers) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (computers[i-1][j-1] == 1) {
                    edges[i][j] = true;
                }
            }
        }

        int components = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, n);
                components++;
            }
        }
        return components;
    }

    void dfs(int node, final int n) {
        visited[node] = true;

        for (int i = 1; i <= n; i++) {
            if (edges[node][i] && !visited[i]) {
                dfs(i, n);
            }
        }
    }
}