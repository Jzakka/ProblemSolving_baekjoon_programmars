import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static Map<Character, Integer> dir = new HashMap<>();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    private static final int NOT_VISITED = 0;
    private static final int ESCAPABLE = 1;
    private static final int UNESCAPABLE = 2;

    public static void main(String[] args) throws Exception {
        dir.put('U', 0);
        dir.put('R', 2);
        dir.put('D', 3);
        dir.put('L', 1);

        int[] size = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = size[0];
        int[][] maze = new int[n][];

        for (int i = 0; i < n; i++) {
            maze[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(d -> dir.get(d.charAt(0)))
                    .toArray();
        }

        solution(maze);

        printRes();
    }

    private static void solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        int[][] DP = new int[n][m];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (escapable(DP, maze, i, j)) {
                    ans++;
                }
            }
        }

        res.append(ans);
    }

    private static boolean escapable(int[][] DP, int[][] maze, int x, int y) {
        if (DP[x][y] == ESCAPABLE) {
            return true;
        } else if (DP[x][y] == UNESCAPABLE) {
            return false;
        }

        DP[x][y] = UNESCAPABLE;
        int dir = maze[x][y];

        int nextX = x + dx[dir];
        int nextY = y + dy[dir];

        if (0 <= nextX && nextX < maze.length && 0 <= nextY && nextY < maze[0].length) {
            boolean result =  escapable(DP, maze, nextX, nextY);
            DP[x][y] = result ? ESCAPABLE : UNESCAPABLE;
            return result;
        }

        DP[x][y] = ESCAPABLE;
        return true;
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */