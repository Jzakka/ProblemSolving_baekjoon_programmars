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
        int n = Integer.parseInt(br.readLine());
        Character[][] area = new Character[n][];

        for (int i = 0; i < n; i++) {
            area[i] = Arrays.stream(br.readLine().split("")).map(str -> str.charAt(0)).toArray(Character[]::new);
        }

        solution(area);

        printRes();
    }

    private static void solution(Character[][] area) {
        Character[][] byungsin = Arrays.stream(area).map(line -> Arrays.stream(line).map(cell -> {
                    if (cell.equals('G')) {
                        cell = 'R';
                    }
                    return cell;
                }).toArray(Character[]::new))
                .toArray(Character[][]::new);

        int ans1 = count(area);
        int ans2 = count(byungsin);

        res.append(ans1).append(" ").append(ans2);
    }

    private static int count(Character[][] area) {
        int n = area.length;
        boolean[][] visited = new boolean[n][n];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    spread(area, i, j, visited);
                    ans++;
                }
            }
        }
        return ans;
    }

    private static void spread(Character[][] area, int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (available(nx, ny, area.length) && !visited[nx][ny] && area[nx][ny] == area[x][y]) {
                spread(area, nx, ny, visited);
            }
        }
    }

    private static boolean available(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
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