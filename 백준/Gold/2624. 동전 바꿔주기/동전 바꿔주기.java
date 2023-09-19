import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[][] coins = new int[k][];
        for (int i = 0; i < k; i++) {
            coins[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(t, coins);

        printRes();
    }

    private static void solution(int t, int[][] coins) {
        int[][] DP = new int[coins.length + 1][t + 1];

        IntStream.range(0, DP.length).forEach(i -> DP[i][0] = 1);

        for (int i = 1; i < DP.length; i++) {
            int price = coins[i - 1][0];
            int count = coins[i - 1][1];

            for (int j = 1; j < DP[0].length; j++) {
                for (int k = 0; k <= count; k++) {
                    int newPrice = j - k * price;

                    if (newPrice >= 0) {
                        DP[i][j] += DP[i - 1][newPrice];
                    } else {
                        break;
                    }
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