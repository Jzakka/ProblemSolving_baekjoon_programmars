import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};
    private static final int AIR = 2;
    private static final int CHEESE = 1;
    private static final int SPACE = 0;

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int n = info[0];
        int m = info[1];

        int[][] board = new int[n][];
        for (int i = 0; i < n; i++) {
            board[i] = getInts();
        }

        int ans = solution(board);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static int solution(int[][] board) {
        airInject(board, 0, 0);

        int ans = 0;
        List<int[]> melt = new ArrayList<>();
        while (!(melt = getMeltCheese(board)).isEmpty()) {
            for (int[] meltPos : melt) {
                int x = meltPos[0];
                int y = meltPos[1];

                board[x][y] = AIR;
            }

            airInject(board);
            ans++;
        }

        return ans;
    }

    private static void airInject(int[][] board) {
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == AIR) {
                    airInject(board, i, j);
                }
            }
        }
    }

    private static List<int[]> getMeltCheese(int[][] board) {
        int n = board.length;
        int m = board[0].length;

        List<int[]> melt = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == CHEESE && adjacentTwoAir(board, i, j)) {
                    melt.add(new int[]{i, j});
                }
            }
        }
        return melt;
    }

    private static boolean adjacentTwoAir(int[][] board, int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (board[nx][ny] == AIR && ++cnt >= 2) {
                return true;
            }
        }
        return false;
    }

    private static void airInject(int[][] board, int x, int y) {
        board[x][y] = AIR;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (inRange(nx, ny, board.length, board[0].length) && board[nx][ny] == SPACE) {
                airInject(board, nx, ny);
            }
        }
    }

    private static boolean inRange(int x, int y, int n, int m) {
        return 0 <= x && x < n && 0 <= y && y < m;
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