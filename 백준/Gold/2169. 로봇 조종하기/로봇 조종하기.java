import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] dx = {1, 0, 0};
    private static int[] dy = {0, 1, -1};

    public static void main(String[] args) throws Exception {
        int[] nm = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] mars = new int[nm[0]][];

        for (int i = 0; i < mars.length; i++) {
            mars[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(mars);

        printRes();
    }

    private static void solution(int[][] mars) {
        int[][] DP = new int[mars.length][mars[0].length];
        int[] right = new int[mars[0].length];
        int[] left = new int[mars[0].length];

        int sum = 0;
        for (int i = 0; i < DP[0].length; i++) {
            sum += mars[0][i];
            DP[0][i] = sum;
        }
        for (int i = 1; i < mars.length; i++) {
            right[0] = DP[i - 1][0] + mars[i][0];
            for (int j = 1; j < DP[0].length; j++) {
                right[j] = Math.max(DP[i - 1][j], right[j - 1]) + mars[i][j];
            }

            left[left.length - 1] = DP[i - 1][DP[0].length - 1] + mars[i][mars[0].length - 1];
            for (int j = DP[0].length-2; j >= 0; j--) {
                left[j] = Math.max(DP[i - 1][j], left[j + 1]) + mars[i][j];
            }

            for (int j = 0; j < mars[0].length; j++) {
                DP[i][j] = Math.max(left[j], right[j]);
            }
        }

        res.append(DP[DP.length-1][DP[0].length-1]);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}