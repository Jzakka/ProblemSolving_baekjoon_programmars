import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }


    /*
      0 1 2 3 4
    0 1
    1 1 1 1 1 1
    2 1 1 2 2 3
    3 1 1 2 3 4
     */

    // DP[j] = DP[j] + DP[j - num]
    private static void solution(int n) {
        long[] DP = new long[n + 1];
        DP[0] = 1;
        DP[1] = 1;

        for (int i = 2; i <= n; i++) {
            DP[i] = DP[i-1] + 2 * DP[i - 2];
        }

        long symetrics;
        long asymetrics;
        if ((n & 1) == 1) {
            symetrics = DP[(n - 1) / 2];
        } else {
            symetrics = DP[n / 2] + 2* DP[(n-2)/2];
        }
        asymetrics = DP[n] - symetrics;

        res.append(symetrics + asymetrics/2);
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*

00001
0001
001
01
10
01

 */