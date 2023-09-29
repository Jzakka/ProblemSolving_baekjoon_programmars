import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        String info;
        while (!(info = br.readLine()).equals("0 0.00")) {
            String[] sangeun = info.split("\\s+");
            int n = Integer.parseInt(sangeun[0]);
            String[] money = sangeun[1].split("\\.");
            int m = Integer.parseInt(money[0]) * 100 + Integer.parseInt(money[1]);


            int[][] candies = new int[n][];
            for (int i = 0; i < n; i++) {
                String[] candy = br.readLine().split("\\s+");
                int c = Integer.parseInt(candy[0]);
                String[] price = candy[1].split("\\.");
                int p = Integer.parseInt(price[0]) * 100 + Integer.parseInt(price[1]);

                candies[i] = new int[]{c, p};
            }

            solution(n, m, candies);
        }

        printRes();
    }

    private static void solution(int n, int m, int[][] candies) {
        long[] DP = new long[m + 1];

        for (int i = 1; i <= n; i++) {
            int calory = candies[i - 1][0];
            int price = candies[i - 1][1];

            for (int j = price; j <= m; j++) {
                DP[j] = Math.max(DP[j], DP[j - price] + calory);
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