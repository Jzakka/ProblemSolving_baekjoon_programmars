import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        String str1 = br.readLine();
        String str2 = br.readLine();

        solution(str1, str2);

        printRes();
    }

    private static void solution(String str1, String str2) {
        int[][] DP = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i < DP.length; i++) {
            for (int j = 1; j < DP[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    DP[i][j] =DP[i - 1][j - 1] + 1;
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j - 1], Math.max(DP[i][j - 1], DP[i - 1][j]));
                }
            }
        }

        res.append(DP[DP.length - 1][DP[0].length - 1]);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}