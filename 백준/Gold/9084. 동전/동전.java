import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            br.readLine();

            int[] coins = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int money = Integer.parseInt(br.readLine());

            solution(coins, money);
        }
        printRes();
    }

    private static void solution(int[] coins, int money) {
        Arrays.sort(coins);

        int[] DP = new int[money + 1];

        DP[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= money; i++) {
                DP[i] = DP[i - coin] + DP[i];
            }
        }

        res.append(DP[money]).append("\n");
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}