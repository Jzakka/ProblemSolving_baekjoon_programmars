import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        solution(n, k);

        printRes();
    }

    private static void solution(int n, int k) {
        // DP[i][j] : i개의 색이 있을 때, j개의 색을 선택한 경우의 수
        int[][] DP = new int[n + 1][k + 1];

        for (int i = 2; i < DP.length; i++) {
            DP[i][1] = i;
        }

        for (int i = 2; i < DP.length; i++) {
            for (int j = 2; j < DP[0].length; j++) {
                DP[i][j] = (DP[i - 1][j] + DP[i - 2][j - 1])%MOD;
            }
        }

        res.append(DP[n][k]);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}