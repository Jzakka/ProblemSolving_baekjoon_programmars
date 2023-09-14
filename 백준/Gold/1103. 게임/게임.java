import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};
    
    public static void main(String[] args) throws Exception {
        int[] size = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        int n = size[0];
        int m = size[1];

        String[] matrix = new String[n];
        
        for (int i = 0; i < n; i++) {
            matrix[i] = br.readLine();
        }

        solution(n, m, matrix);

        printRes();
    }

    private static void solution(int n, int m, String[] matrix) {
        boolean[][] visited = new boolean[n][m];
        int[][] DP = new int[n][m];
        int ans = dfs(n, m, DP, matrix, visited, 0, 0);

        res.append(ans == -1 ? ans : ans - 1);
    }

    private static int dfs(int n, int m, int[][] DP,String[] matrix, boolean[][] visited, int x, int y) {
        if (!available(n, m, x, y) || matrix[x].charAt(y) == 'H') {
            return 1;
        }

        if (DP[x][y] != 0) {
            return DP[x][y];
        }

        if (visited[x][y]) {
            return -1;
        }

        visited[x][y] = true;

        int delta = matrix[x].charAt(y) - '0';

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i] * delta;
            int nextY = y + dy[i] * delta;
            int res = dfs(n, m, DP, matrix, visited, nextX, nextY);

            if (res == -1) {
                return res;
            }
            ans = Math.max(ans, res);
        }
        visited[x][y] = false;


        DP[x][y] = ans + 1;
        return DP[x][y];
    }

    private static boolean available(int n, int m, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}