import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};


    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int n = info[0];
        int m = info[1];
        int[][] antarctic = new int[n][];
        for (int i = 0; i < n; i++) {
            antarctic[i] = getInts();
        }

        solution(n, m, antarctic);

        printRes();
    }

    private static void solution(int n, int m, int[][] antarctic) {
        int cnt;

        int year = 0;
        while ((cnt = getGlacierCount(n, m, antarctic)) == 1) {
            int[][] meltDelta = getMeltDelta(n, m, antarctic);
            melt(n, m, antarctic, meltDelta);
            year++;
        }

        res.append(cnt > 1 ? year : 0);
    }

    private static void melt(int n, int m, int[][] antarctic, int[][] meltDelta) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                antarctic[i][j] = Math.max(0, antarctic[i][j] - meltDelta[i][j]);
            }
        }
    }

    private static int[][] getMeltDelta(int n, int m, int[][] antarctic) {
        boolean[][] visited = new boolean[n][m];
        int[][] meltDelta = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && antarctic[i][j] > 0) {
                    travel(i, j, antarctic, visited, (x,y)->setMeltDelta(x,y,meltDelta,antarctic));
                }
            }
        }
        return meltDelta;
    }

    private static void setMeltDelta(int x, int y, int[][] meltDelta, int[][] antarctic) {
        int delta = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inRange(nx, ny, antarctic.length, antarctic[0].length) && antarctic[nx][ny] == 0) {
                delta++;
            }
        }
        meltDelta[x][y] = delta;
    }

    private static int getGlacierCount(int n, int m, int[][] antarctic) {
        boolean[][] visited = new boolean[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && antarctic[i][j] > 0) {
                    cnt++;
                    travel(i, j, antarctic, visited, (x,y)->{});
                }
            }
        }
        return cnt;
    }

    private static void travel(int x, int y, int[][] antarctic, boolean[][] visited, BiConsumer<Integer, Integer> callback) {
        visited[x][y] = true;
        callback.accept(x,y);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (inRange(nx, ny, antarctic.length, antarctic[0].length)
                    && !visited[nx][ny] && antarctic[nx][ny] > 0) {
                travel(nx, ny, antarctic, visited, callback);
            }
        }
    }

    private static boolean inRange(int x, int y, int n, int m) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static long[] getLongs() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}