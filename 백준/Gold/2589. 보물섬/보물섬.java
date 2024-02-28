import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int[] rc = getInts();
        String[] world = new String[rc[0]];

        for (int i = 0; i < world.length; i++) {
            world[i] = br.readLine();
        }

        solution(rc[0], rc[1], world);

        printRes();
    }

    private static void solution(int r, int c, String[] world) {
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (world[i].charAt(j) == 'L') {
                    ans = Math.max(bfs(r, c, world, i, j), ans);
                }
            }
        }
        res.append(ans);
    }

    private static int bfs(int r, int c, String[] world, int x, int y) {
        boolean[][] visited = new boolean[r][c];
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{x, y});
        visited[x][y] = true;
        int dist = 0;

        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                int[] pos = Q.poll();
                int _x = pos[0];
                int _y = pos[1];

                for (int d = 0; d < 4; d++) {
                    int nx = _x + dx[d];
                    int ny = _y + dy[d];

                    if (inRange(nx, ny, r, c) && world[nx].charAt(ny) == 'L' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        Q.add(new int[]{nx, ny});
                    }
                }
            }

            dist++;
        }

        return dist - 1;
    }

    private static boolean inRange(int x, int y, int r, int c) {
        return 0 <= x && x < r && 0 <= y && y < c;
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