import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int[] nm = getInts();
        int n = nm[0];
        int[][] area = new int[n][];

        for (int i = 0; i < n; i++) {
            area[i] = getInts();
        }

        solution(area);

        printRes();
    }

    private static void solution(int[][] area) {
        int n = area.length;
        int m = area[0].length;

        long ans = 0;
        for (int i = 0; i < n * m; i++) {
            int[] pos1 = getPos(i, m);
            if (area[pos1[0]][pos1[1]] != 0) {
                continue;
            }
            for (int j = i + 1; j < n * m; j++) {
                int[] pos2 = getPos(j, m);
                if (area[pos2[0]][pos2[1]] != 0) {
                    continue;
                }
                for (int k = j + 1; k < n * m; k++) {
                    int[] pos3 = getPos(k, m);
                    if (area[pos3[0]][pos3[1]] != 0) {
                        continue;
                    }
                    int[][] deepCopied = Arrays.stream(area).map(line -> Arrays.copyOf(line, line.length)).toArray(int[][]::new);
                    deepCopied[pos1[0]][pos1[1]] = 1;
                    deepCopied[pos2[0]][pos2[1]] = 1;
                    deepCopied[pos3[0]][pos3[1]] = 1;
                    long cnt = spread(deepCopied);
                    ans = Math.max(ans, cnt);

                }
            }
        }
        res.append(ans);
    }

    private static long spread(int[][] area) {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[0].length; j++) {
                if (area[i][j] == 2) {
                    spread(area, i, j);
                }
            }
        }
        return Arrays.stream(area).flatMapToInt(Arrays::stream).filter(i -> i == 0).count();
    }

    private static void debug(int[][] area) {
        for (int[] line : area) {
            for (int e : line) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void spread(int[][] area, int x, int y) {
        area[x][y] = -1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(available(nx, ny, area.length, area[0].length) && area[nx][ny] == 0){
                spread(area, nx, ny);
            }
        }
    }

    private static boolean available(int x, int y, int n, int m) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private static int[] getPos(int seq, int m) {
        int q = seq / m;
        return new int[]{q, seq - q * m};
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