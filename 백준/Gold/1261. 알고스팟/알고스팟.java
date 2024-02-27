import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int[] rc = getInts();
        int c = rc[0];
        int r = rc[1];
        int[][] board = new int[r][];

        for (int i = 0; i < r; i++) {
            board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        solution(r, c, board);

        printRes();
    }

    private static void solution(int r, int c, int[][] board) {
        int[][] distance = new int[r][c];
        Arrays.stream(distance).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));

        distance[0][0] = 0;
        // {x,y,dist}
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
        PQ.add(new int[]{0, 0, 0});

        while (!PQ.isEmpty()) {
            int[] info = PQ.poll();
            int x = info[0];
            int y = info[1];
            int dist = info[2];

            if (distance[x][y] < dist) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (inRange(nx, ny, r, c) && dist + board[nx][ny] < distance[nx][ny]) {
                    distance[nx][ny] = dist + board[nx][ny];
                    PQ.add(new int[]{nx, ny, distance[nx][ny]});
                }
            }
        }

        res.append(distance[r - 1][c - 1]);
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