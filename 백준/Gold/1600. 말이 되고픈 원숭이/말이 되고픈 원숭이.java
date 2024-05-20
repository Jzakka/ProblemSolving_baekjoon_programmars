
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};
    private static final int[] jx = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] jy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(br.readLine());
        int[] info = getInts();
        int h = info[1];
        int[][] map = new int[h][];

        for (int i = 0; i < h; i++) {
            map[i] = getInts();
        }

        int ans = solution(k, map);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static int solution(int k, int[][] map) {
        int h = map.length;
        int w = map[0].length;
        // distances[x][y][i] : x,y를 i번 점프해서 도달할 수 있는 최소거리
        int[][][] distances = new int[h][w][k + 1];

        Arrays.stream(distances).forEach(row -> Arrays.stream(row).forEach(cells -> Arrays.fill(cells, Integer.MAX_VALUE)));
        distances[0][0][0] = 0;

        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{0, 0, 0});

        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                int[] info = Q.poll();
                int x = info[0];
                int y = info[1];
                int cost = info[2];

                // 상하좌우로
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (inRange(nx, ny, h, w) && map[x][y] == 0 && distances[nx][ny][cost] > distances[x][y][cost] + 1) {
                        distances[nx][ny][cost] = distances[x][y][cost] + 1;
                        Q.add(new int[]{nx, ny, cost});
                    }
                }

                // 점프
                if (cost + 1 <= k) {
                    for (int d = 0; d < 8; d++) {
                        int nx = x + jx[d];
                        int ny = y + jy[d];

                        if (inRange(nx, ny, h, w) && map[x][y] == 0 && distances[nx][ny][cost + 1] > distances[x][y][cost] + 1) {
                            distances[nx][ny][cost + 1] = distances[x][y][cost] + 1;
                            Q.add(new int[]{nx, ny, cost + 1});
                        }
                    }
                }
            }
        }

        int ans = Arrays.stream(distances[h - 1][w - 1]).min().getAsInt();
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static boolean inRange(int x, int y, int h, int w) {
        return 0 <= x && x < h && 0 <= y && y < w;
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

/*
3
5 5
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 1
0 0 0 1 0
 */