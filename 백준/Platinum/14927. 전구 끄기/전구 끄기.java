import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = getInts();
        }
        int ans = solution(matrix);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static int solution(int[][] matrix) {
        long maxFlip = (long) Math.pow(2, matrix.length);

        int ans = 10_000;
        for (long f = 0; f < maxFlip; f++) {
            int[][] copied = IntStream.range(0, matrix.length)
                    .mapToObj(i -> Arrays.copyOf(matrix[i], matrix.length))
                    .toArray(int[][]::new);

            int flipCnt = flip(copied, f);
            flipCnt += lookAbove(copied);

            ans = Math.min(flipCnt, ans);
        }

        return ans == 10_000 ? -1 : ans;
    }

    private static int lookAbove(int[][] matrix) {
        int flipCnt = 0;
        for (int r = 1; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r - 1][c] == 1) {
                    flip(matrix, r, c);
                    flipCnt++;
                }
            }
        }

        return allZero(matrix) ? flipCnt : 10_000;
    }

    private static int flip(int[][] arr, long flipCol) {
        int flipCnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if (((flipCol >> i) & 1) == 1) {
                flip(arr, 0, i);
                flipCnt++;
            }
        }
        return flipCnt;
    }

    private static void flip(int[][] matrix, int r, int c) {
        matrix[r][c] ^= 1;

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (inRange(matrix.length, nx, ny)) {
                matrix[nx][ny] ^= 1;
            }
        }
    }

    private static boolean inRange(int n, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static boolean allZero(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .noneMatch(i -> i == 1);
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
}