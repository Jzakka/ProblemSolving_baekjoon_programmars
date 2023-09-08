import java.io.*;
import java.util.Arrays;

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

        int ans = 0;

        for (int i = 1; i < DP.length; i++) {
            for (int j = 1; j < DP[0].length; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                    ans = Math.max(ans, DP[i][j]);
                }
            }
        }

        res.append(ans);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}