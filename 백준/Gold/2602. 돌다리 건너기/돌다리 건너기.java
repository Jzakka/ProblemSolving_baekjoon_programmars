import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        String scroll = br.readLine();
        String devil = br.readLine();
        String angel = br.readLine();

        solution(scroll, devil, angel);

        printRes();
    }

    private static void solution(String scroll, String devil, String angel) {
        int[][][] DP = new int[scroll.length()][devil.length()][2];

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < devil.length(); i++) {
            if (scroll.charAt(0) == devil.charAt(i)) {
                sum1++;

            }
            if (scroll.charAt(0) == angel.charAt(i)) {
                sum2++;
            }
            DP[0][i][0] = sum1;
            DP[0][i][1] = sum2;
        }

        for (int i = 1; i < DP.length; i++) {
            for (int j = 1; j < DP[0].length; j++) {
                if (scroll.charAt(i) == devil.charAt(j)) {
                    DP[i][j][0] = DP[i][j - 1][0] + DP[i - 1][j - 1][1];
                } else {
                    DP[i][j][0] = DP[i][j - 1][0];
                }

                if (scroll.charAt(i) == angel.charAt(j)) {
                    DP[i][j][1] = DP[i][j - 1][1] + DP[i - 1][j - 1][0];
                } else {
                    DP[i][j][1] = DP[i][j - 1][1];
                }
            }
        }

        res.append(Arrays.stream(DP[DP.length - 1][DP[0].length - 1]).sum());
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

// 0100010