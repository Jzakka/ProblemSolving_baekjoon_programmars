import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws Exception {
        String[] nm = br.readLine().split("\\s+");
        int n = Integer.parseInt(nm[0]);

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        if ((n & 1) == 1) {
            res.append(0);
            return;
        }
        int[] DP = new int[31];
        DP[0] = 1;
        DP[2] = 3;

        for (int i = 4; i <= n; i+=2) {
            int sum = 0;
            for (int j = 0; j < i-2; j+=2) {
                sum += DP[j] * 2;
            }
            sum += DP[i-2] * 3;
            DP[i] = sum;
        }

        res.append(DP[n]);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}