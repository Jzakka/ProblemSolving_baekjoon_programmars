import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) != '#') {
                    board[i][j] = line.charAt(j) - '0';
                } else {
                    board[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        solution(board);

        printRes();
    }

    private static void solution(int[][] board) {
        int ans = 0;
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 8 && !zeroContains(i, j, board)) {
                    ans++;
                    decrease(i, j, board);
                }
            }
        }

        res.append(ans);
    }

    private static void decrease(int i, int j, int[][] board) {
        for (int d = 0; d < 8; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];

            if (available(nx, ny, board.length)) {
                board[nx][ny]--;
            }
        }
    }

    private static boolean zeroContains(int i, int j, int[][] board) {
        for (int d = 0; d < 8; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];

            if (available(nx, ny, board.length) && board[nx][ny] == 0) {
                return true;
            }
        }
        return false;
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
/*
4
3
1
1
1
 */