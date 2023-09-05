import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] files = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            solution(files);
        }

        printRes();
    }

    private static void solution(int[] C) {
        int[] files = new int[C.length + 1];
        IntStream.range(0, C.length).forEach(i -> files[i + 1] = C[i]);

        int[] sum = new int[files.length];
        int total = 0;
        for (int i = 0; i < files.length; i++) {
            total += files[i];
            sum[i] = total;
        }

        int[][] DP = new int[files.length][files.length];

        for (int i = 2; i < files.length; i++) {
            int[] coord = {1, i};
            while (coord[1] < files.length) {
                DP[coord[0]][coord[1]] = calc(DP, sum, coord);

                coord[0]++;
                coord[1]++;
            }
        }

        res.append(DP[1][DP.length - 1]).append("\n");
    }

    private static int calc(int[][] dp, int[] sum, int[] coord) {
        int x = coord[0];
        int y = coord[1];

        int min = Integer.MAX_VALUE;
        for (int k = x; k < y; k++) {
            int calcCnt = dp[x][k] + dp[k + 1][y] + sum[y] - sum[x - 1];
            min = Math.min(min, calcCnt);
        }
        return min;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}