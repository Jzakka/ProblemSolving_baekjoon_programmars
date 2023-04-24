import java.util.*;

class Solution {
    private boolean[][] visited;
    private int[][] picture;
    int m, n;

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
        this.m = m;
        this.n = n;
        this.picture = picture;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int dot = picture[i][j];
                if (dot != 0 && !visited[i][j]) {
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, dfs(i, j, 0));
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int dfs(int i, int j, int area) {
        visited[i][j] = true;
        area++;
        List<int[]> next = getNext(i, j);

        for (int[] xy : next) {
            int x = xy[0];
            int y = xy[1];

            if (!visited[x][y]) {
                area=dfs(x, y, area);
            }
        }
        return area;
    }

    private List<int[]> getNext(int i, int j) {
        ArrayList<int[]> nexts = new ArrayList<>();

        //e
        if (j + 1 < n && !visited[i][j + 1] && picture[i][j] == picture[i][j + 1]) {
            nexts.add(new int[]{i, j + 1});
        }
        //w
        if (j - 1 >= 0 && !visited[i][j - 1] && picture[i][j] == picture[i][j - 1]) {
            nexts.add(new int[]{i, j - 1});
        }
        //s
        if (i + 1 < m && !visited[i + 1][j] && picture[i][j] == picture[i + 1][j]) {
            nexts.add(new int[]{i + 1, j});
        }
        //n
        if (i - 1 >= 0 && !visited[i - 1][j] && picture[i][j] == picture[i - 1][j]) {
            nexts.add(new int[]{i - 1, j});
        }

        return nexts;
    }
}