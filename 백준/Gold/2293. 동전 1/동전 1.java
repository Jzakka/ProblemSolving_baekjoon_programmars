import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

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
        DP[0] = 1;


        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                DP[i] += DP[i - coin];
            }
        }

        res.append(DP[k]);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}