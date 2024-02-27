import java.io.*;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int[] nm = getInts();
        int n = nm[0];
        String[][] world = new String[n][];
        for (int i = 0; i < n; i++) {
            world[i] = br.readLine().split("");
        }

        solution(world);

        printRes();
    }

    private static void solution(String[][] world) {
        Queue<int[]> sonicQ = new LinkedList<>();
        Queue<int[]> floodQ = new LinkedList<>();
        int n = world.length;
        int m = world[0].length;
        boolean[][] sonicVisit = new boolean[n][m];
        boolean[][] floodVisit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (world[i][j].equals("*")) {
                    floodQ.add(new int[]{i, j});
                    floodVisit[i][j] = true;
                } else if (world[i][j].equals("S")) {
                    sonicQ.add(new int[]{i, j});
                    sonicVisit[i][j] = true;
                }
            }
        }

        int ans = 0;
        while (!sonicQ.isEmpty()) {
            int sonicQLen = sonicQ.size();
            for (int i = 0; i < sonicQLen; i++) {
                int[] pos = sonicQ.poll();
                int x = pos[0];
                int y = pos[1];

                if (world[x][y].equals("*")) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (inRange(nx, ny, n, m) && (world[nx][ny].equals(".") || world[nx][ny].equals("D")) && !sonicVisit[nx][ny]) {
                        if (world[nx][ny].equals("D")) {
                            res.append(ans + 1);
                            return;
                        }
                        world[nx][ny] = "S";
                        sonicVisit[nx][ny] = true;
                        sonicQ.add(new int[]{nx, ny});
                    }
                }
            }

            int floodQLen = floodQ.size();
            for (int i = 0; i < floodQLen; i++) {
                int[] pos = floodQ.poll();
                int x = pos[0];
                int y = pos[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (inRange(nx, ny, n, m) && (world[nx][ny].equals(".") || world[nx][ny].equals("S")) && !floodVisit[nx][ny]) {
                        world[nx][ny] = "*";
                        floodVisit[nx][ny] = true;
                        floodQ.add(new int[]{nx, ny});
                    }
                }
            }
//            debug(world);
            ans++;
        }

        res.append("KAKTUS");
    }

    private static void debug(String[][] world) {
        for (String[] row : world) {
            for (String s : row) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println("==========");
    }

    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static boolean inRange(int x, int y, int n, int m) {
        return 0 <= x && x < n && 0 <= y && y < m;
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