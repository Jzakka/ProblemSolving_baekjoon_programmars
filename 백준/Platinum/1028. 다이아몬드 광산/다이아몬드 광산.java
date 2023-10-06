import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int NE = 0;
    private static final int SE = 1;

    public static void main(String[] args) throws Exception {
        int[] size = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int r = size[0];

        int[][] mine = new int[r][];

        for (int i = 0; i < r; i++) {
            mine[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(mine);

        printRes();
    }

    private static void solution(int[][] mine) {
        int r = mine.length;
        int c = mine[0].length;
        int[][][] DP = new int[r][c][2];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                DP[i][j][NE] = DP[i][j][SE] = (mine[i][j] == 1 ? 1 : 0);
            }
        }

        for (int j = c - 2; j >= 0; j--) {
            for (int i = 0; i < r; i++) {
                if (mine[i][j] == 1) {
                    if (i - 1 >= 0) {
                        DP[i][j][NE] += DP[i - 1][j + 1][NE];
                    }
                    if (i + 1 < r) {
                        DP[i][j][SE] += DP[i + 1][j + 1][SE];
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (mine[i][j] == 1) {
                    int len = Math.min(DP[i][j][NE], DP[i][j][SE]);

                    while (len > 0) {
                        int x1 = i - (len - 1);
                        int y1 = j + (len - 1);
                        int x2 = i + (len - 1);
                        int y2 = j + (len - 1);

                        if (available(mine, x1, y1) && available(mine, x2, y2)) {
                            if (DP[x1][y1][SE] >= len && DP[x2][y2][NE] >= len) {
                                ans = Math.max(ans, len);
                                break;
                            }
                        }
                        len--;
                    }
                }
            }
        }

        res.append(ans);
    }

    private static boolean available(int[][] mine, int x, int y) {
        return 0 <= x && x < mine.length && 0 <= y && y < mine[0].length
                && mine[x][y] == 1;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*

00001
0001
001
01
10
01

 */