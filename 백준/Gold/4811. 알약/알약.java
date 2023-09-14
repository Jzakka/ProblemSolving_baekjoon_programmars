import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BigInteger[][] DP = solution();

        int n;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            res.append(DP[2 * n][n]).append("\n");
        }

        printRes();
    }

    private static BigInteger[][] solution() {
        BigInteger[][] DP = new BigInteger[61][31];

        for (int i = 1; i < DP[0].length; i++) {
            DP[1][i] = new BigInteger("0");
        }
        for (int i = 1; i < DP.length; i++) {
            DP[i][1] = new BigInteger("0");
        }
        DP[1][1] = DP[2][1] = new BigInteger("1");

        for (int i = 2; i < DP.length; i++) {
            for (int j = 2; j <DP[0].length ; j++) {
                if (i - j <= j) {
                    DP[i][j] = DP[i - 1][j - 1].add(DP[i - 1][j]);
                } else {
                    DP[i][j] = DP[i - 1][j - 1];
                }
            }
        }

        return DP;
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}