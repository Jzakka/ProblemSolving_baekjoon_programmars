import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        long[] DP = createTable();

        while (t-- > 0) {
            solution(DP, Integer.parseInt(br.readLine()));
        }

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
    private static void solution(long[] DP, int n) {
        res.append(DP[n]).append("\n");
    }

    private static long[] createTable() {
        long[] DP = new long[10_001];
        DP[0] = 1;

        Arrays.fill(DP, 1);

        for (int i = 2; i <= 3; i++) {
            for (int j = i; j <= 10_000; j++) {
                DP[j] += DP[j - i];
            }
        }

        return DP;
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