import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int m = info[1];

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solution(arr, m);

        printRes();
    }

    private static void solution(int[] arr, int m) {
        int n = arr.length;
        long[][] DP = new long[m][n];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Integer.MIN_VALUE));
        Arrays.fill(DP[0], 0);

        // m == 1
        DP[0][0] = arr[0];
        for (int j = 1; j < DP[0].length; j++) {
            DP[0][j] = Math.max(arr[j], DP[0][j-1] + arr[j]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 2*i; j < n; j++) {
                final int prevGroup = i - 1;
                long prevGroupSum = LongStream.rangeClosed(0, j - 2)
                        .map(seq -> DP[prevGroup][(int)seq])
                        .max()
                        .getAsLong();
                long currentGroupSum = DP[i][j - 1];
                DP[i][j] = Math.max(prevGroupSum, currentGroupSum) + arr[j];
            }
        }

        res.append(Arrays.stream(DP[DP.length - 1]).max().getAsLong());
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }

    /*
    12
    20
    32
    4
    52
    60
    72
    8
    92
    104
    112
    12
    132
    140
     */
}