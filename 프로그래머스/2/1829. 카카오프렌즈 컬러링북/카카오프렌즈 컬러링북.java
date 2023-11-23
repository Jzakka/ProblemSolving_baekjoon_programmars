class Solution {
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};

    public int[] solution(int m, int n, int[][] picture) {
        boolean[][] visited = new boolean[m][n];

        int areaCnt = 0;
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    areaCnt++;
                    int area = dfs(picture, visited, i, j);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }

        return new int[]{areaCnt, maxArea};
    }

    private int dfs(int[][] picture, boolean[][] visited, int x, int y) {
        visited[x][y] = true;

        int area = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (available(nx, ny, picture.length, picture[0].length)
                    && !visited[nx][ny] && picture[nx][ny] == picture[x][y]) {
                area += dfs(picture, visited, nx, ny);
            }
        }

        return area;
    }

    private boolean available(int x, int y, int m, int n) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }
}