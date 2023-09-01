import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String[] inputs = br.readLine().split("\\s+");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);

        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        solution(coins, k);

        printRes();
    }

    private static void solution(int[] coins, int k) {
        int[] DP = new int[k + 1];
        Arrays.fill(DP, Integer.MAX_VALUE);

        for (int coin : coins) {
            if (coin <= k) {
                DP[coin] = 1;
            }
        }

        for (int i = 1; i <= k; i++) {
            if (DP[i] != Integer.MAX_VALUE) {
                for (int coin : coins) {
                    if (i + coin <= k) {
                        DP[i + coin] = Math.min(DP[i + coin], DP[i] + 1);
                    }
                }
            }
        }

        res.append(DP[k] == Integer.MAX_VALUE ? -1 : DP[k]);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}