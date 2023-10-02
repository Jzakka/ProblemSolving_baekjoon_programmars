import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int m = Integer.parseInt(br.readLine());

            solution(coins, m);
        }

        printRes();
    }

    private static void solution(int[] coins, int m) {
        int[] DP = new int[m + 1];

        for (int i = 0; i < DP.length; i++) {
            DP[0] = 1;
        }

        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = coin; j <= m; j++) {
                DP[j] += DP[j - coin];
            }
        }

        res.append(DP[m]).append("\n");
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */