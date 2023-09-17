import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] house = new int[n][];

        for (int i = 0; i < n; i++) {
            house[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(house);

        printRes();
    }

    private static void solution(int[][] house) {
        int n = house.length;

        long[][][] DP = new long[n][n][3];

        DP[0][1][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (available(house, n, i, j + 1)) {
                    DP[i][j + 1][0] += DP[i][j][0] + DP[i][j][1];
                }
                if (available(house, n, i, j + 1)
                        && available(house, n, i+1, j + 1)
                        && available(house, n, i+1, j)) {
                    DP[i + 1][j + 1][1] += DP[i][j][0] + DP[i][j][1] + DP[i][j][2];
                }
                if (available(house, n, i + 1, j)) {
                    DP[i + 1][j][2] += DP[i][j][2] + DP[i][j][1];
                }
            }
        }

        res.append(Arrays.stream(DP[n - 1][n - 1]).sum());
    }

    private static boolean available(int[][] house, int n, int x, int y) {
        return x < n && y < n && house[x][y] == 0;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}