import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int t = info[1];

        int[][] chapters = new int[n][];
        for (int i = 0; i < n; i++) {
            chapters[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(chapters, t);
        printRes();
    }

    private static void solution(int[][] chapters, int t) {
        int[][] DP = new int[chapters.length + 1][t + 1];

        for (int i = 1; i < DP.length; i++) {
            int time = chapters[i - 1][0];
            int points = chapters[i - 1][1];

            for (int j = 0; j < DP[0].length; j++) {
                if (j < time) {
                    DP[i][j] = DP[i - 1][j];
                } else {
                    DP[i][j] = Math.max(DP[i-1][j], DP[i - 1][j - time] + points);
                }
            }
        }

        res.append(Arrays.stream(DP[DP.length - 1]).max().getAsInt());
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}