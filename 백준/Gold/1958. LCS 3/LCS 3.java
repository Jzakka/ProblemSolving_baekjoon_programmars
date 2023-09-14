import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] dx = {1, 0, 0};
    private static int[] dy = {0, 1, -1};

    public static void main(String[] args) throws Exception {
        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();

        solution(str1, str2, str3);

        printRes();
    }

    private static void solution(String str1, String str2, String str3) {
        int[][][] DP = new int[str1.length() + 1][str2.length() + 1][str3.length() + 1];

        for (int i = 1; i < DP.length; i++) {
            for (int j = 1; j < DP[0].length; j++) {
                for (int k = 1; k < DP[0][0].length; k++) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1) && str3.charAt(k - 1) == str2.charAt(j - 1)) {
                        DP[i][j][k] = DP[i - 1][j - 1][k - 1] + 1;
                    } else {
                        DP[i][j][k] = Math.max(DP[i][j][k - 1], Math.max(DP[i][j - 1][k], DP[i - 1][j][k]));
                    }
                }
            }
        }

        res.append(DP[str1.length()][str2.length()][str3.length()]);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}