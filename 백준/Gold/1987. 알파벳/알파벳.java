import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int r = info[0];
        int c = info[1];
        String[] board = new String[r];

        for (int i = 0; i < r; i++) {
            board[i] = br.readLine();
        }

        solution(board);
        printRes();
    }

    private static void solution(String[] board) {
        boolean[] visited = new boolean[26];

        int ans = search(0, 0, visited, board);

        res.append(ans);
    }

    private static int search(int x, int y, boolean[] visited, String[] board) {
        char alphabet = board[x].charAt(y);
        visited[alphabet - 'A'] = true;

        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (available(nx, ny, board.length, board[0].length())
                    && !visited[board[nx].charAt(ny) - 'A']) {
                max = Math.max(max, search(nx, ny, visited, board));
            }
        }
        visited[alphabet - 'A'] = false;
        return max + 1;
    }

    private static boolean available(int x, int y, int r, int c) {
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