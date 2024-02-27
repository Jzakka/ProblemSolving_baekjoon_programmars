import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int[] rc = getInts();
        int r = rc[0];
        int c = rc[1];

        int[][] cheese = new int[r][];
        for (int i = 0; i < r; i++) {
            cheese[i] = getInts();
        }

        solution(r, c, cheese);

        printRes();
    }

    private static void solution(int r, int c, int[][] cheese) {
        int cnt = 0;
        List<int[]> melt;
        int lastCnt = 0;

        while (!(melt = getMelt(r,c,cheese)).isEmpty()) {
            cnt++;
            for (int[] pos : melt) {
                cheese[pos[0]][pos[1]] = 0;
            }
            lastCnt = melt.size();
        }

        res.append(cnt).append("\n").append(lastCnt);
    }

    private static List<int[]> getMelt(int r, int c, int[][] cheese) {
        List<int[]> melt = new ArrayList<>();

        boolean[][] visited = new boolean[r][c];
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                int[] pos = Q.poll();
                int x = pos[0];
                int y = pos[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (inRange(nx, ny, r, c) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        if (cheese[nx][ny] == 1) {
                            melt.add(new int[]{nx, ny});
                        } else {
                            Q.add(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        return melt;
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