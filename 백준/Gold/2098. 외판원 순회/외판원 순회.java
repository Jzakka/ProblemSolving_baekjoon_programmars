import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(matrix);

        printRes();
    }

    private static void solution(int[][] matrix) {
        int n = matrix.length;
        int maxSet = (int) Math.pow(2, n);
        Integer[] order = IntStream.range(0, maxSet)
                .boxed()
                .sorted(Comparator.comparingInt(Main::countBits))
                .toArray(Integer[]::new);

        int[][] DP = new int[n][maxSet];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, INF));

        DP[0][0] = 0;
        for (int i = 1; i < n; i++) {
            DP[i][0] = matrix[i][0] == 0 ? INF :  matrix[i][0];
        }

        for (int i = 1; i < order.length; i++) {
            Integer set = order[i];

            if ((set & 1) == 1) {
                continue;
            }

            for (int j = 1; j < DP.length; j++) {
                if (((set >> j) & 1) != 1) {
                    updateDP(matrix, DP, j, set);
                }
            }
        }
        updateDP(matrix, DP, 0, maxSet - 2);

        res.append(DP[0][maxSet - 2]);
    }

    private static void updateDP(int[][] matrix, int[][] dp, int i, Integer set) {
        int cityNum = 1;

        while (1 << cityNum < (int) Math.pow(2, matrix.length)) {
            if ((set & (1 << cityNum)) != 0) {
                int mask = ~(1 << cityNum);
                if (matrix[i][cityNum] != 0) {
                    dp[i][set] = Math.min(dp[i][set], dp[cityNum][set & mask] + matrix[i][cityNum]);
                }
            }
            cityNum++;
        }

//        System.out.printf("DP[%d][%d] = %d%n", i, set, dp[i][set]);
    }

    private static int countBits(int num) {
        int cnt = 0;
        while (num > 0) {
            if ((num & 1) == 1) {
                cnt++;
            }
            num >>= 1;
        }

        return cnt;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}