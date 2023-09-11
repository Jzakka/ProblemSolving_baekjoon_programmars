import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] weights = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        br.readLine();

        int[] beads = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        solutions(weights, beads);

        printRes();
    }

    private static void solutions(int[] w, int[] b) {
        boolean[][] DP = new boolean[w.length + 1][50_000];

        DP[0][0] = true;

        for (int i = 1; i < DP.length; i++) {
            for (int j = 0; j < DP[0].length; j++) {
                DP[i][j] = DP[i - 1][j]
                        || (DP[i - 1][Math.abs(j - w[i-1])])
                        || (j + w[i-1] < DP[0].length && DP[i - 1][j + w[i-1]]);
            }
        }

        for (int bead : b) {
            if (DP[DP.length - 1][bead]) {
                res.append("Y");
            } else {
                res.append("N");
            }
            res.append(" ");
        }
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}