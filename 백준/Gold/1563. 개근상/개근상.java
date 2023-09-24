import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        long[][][] DP = new long[n + 1][2][3];
        DP[1][0][0] = 1;
        DP[1][1][0] = 1;
        DP[1][0][1] = 1;

        for (int i = 2; i < DP.length; i++) {
            for (int j = 0; j < 2; j++) {
                DP[i][j][0] = Arrays.stream(DP[i - 1][j]).reduce(0, (sum, e)->{
                    sum += e;
                    sum %= MOD;
                    return sum;
                });

                if (j >= 1) {
                    DP[i][j][0] += Arrays.stream(DP[i - 1][j - 1]).reduce(0, (sum, e)->{
                        sum += e;
                        sum %= MOD;
                        return sum;
                    });
                    DP[i][j][0] %= MOD;
                }
                for (int k = 1; k < 3; k++) {
                    DP[i][j][k] = DP[i - 1][j][k - 1];
                }
            }
        }

        long ans = Arrays.stream(DP[n]).flatMapToLong(Arrays::stream).reduce(0, (sum, e)->{
            sum += e;
            sum %= MOD;
            return sum;
        });

        res.append(ans);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}