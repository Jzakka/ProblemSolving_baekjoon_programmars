import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int n = info[0];
        char[][] maze = new char[n][];

        for (int i = 0; i < n; i++) {
            maze[i] = br.readLine().toCharArray();
        }

        int ans = solution(maze);

        res.append(ans == -1 ? "IMPOSSIBLE" : String.valueOf(ans));

        bw.write(res.toString());
        bw.close();
    }

    private static int solution(char[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] times = new int[n][m];
        Arrays.stream(times).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        Queue<Point> fires = new LinkedList<>();
        Queue<Point> jihoon = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 'F') {
                    fires.add(new Point(i, j));
                    times[i][j] = 0;
                } else if (maze[i][j] == 'J') {
                    jihoon.add(new Point(i, j));
                    visited[i][j] = true;
                } else if (maze[i][j] == '#') {
                    times[i][j] = -1;
                }
            }
        }

        int time = 0;
        while (!fires.isEmpty()) {
            int qLen = fires.size();
            for (int i = 0; i < qLen; i++) {
                Point fire = fires.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = fire.x + dx[d];
                    int ny = fire.y + dy[d];

                    if (inRange(nx, ny, n, m) && time + 1 < times[nx][ny]) {
                        times[nx][ny] = time + 1;
                        fires.add(new Point(nx, ny));
                    }
                }
            }
            time++;
        }

        int ans = 0;

        while (!jihoon.isEmpty()) {
            int qLen = jihoon.size();
            for (int i = 0; i < qLen; i++) {
                Point point = jihoon.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = point.x + dx[d];
                    int ny = point.y + dy[d];

                    if (!inRange(nx, ny, n, m)) {
                        return ans + 1;
                    } else if (!visited[nx][ny] && ans + 1 < times[nx][ny]) {
                        visited[nx][ny] = true;
                        jihoon.add(new Point(nx, ny));
                    }
                }
            }
            ans++;
        }
        return -1;
    }
    private static boolean inRange(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}

class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}