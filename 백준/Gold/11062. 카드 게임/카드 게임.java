import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] cards = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            solution(cards);
        }

        printRes();
    }

    private static void solution(int[] cards) {
        int[] sum = new int[cards.length + 1];
        int[][] DP = new int[cards.length + 1][cards.length + 1];

        for (int i = 0; i < cards.length; i++) {
            sum[i + 1] = sum[i] + cards[i];
        }

        int ans = geunwoo(DP, cards, sum, 0, cards.length);

        res.append(ans).append("\n");
    }

    private static int geunwoo(int[][] DP, int[] cards, int[] sum, int start, int end) {
        if (start == end) {
            return 0;
        }
        if (DP[start][end] != 0) {
            return DP[start][end];
        }

        int firstSelected = cards[start] + sum[end]-sum[start + 1]-myungwoo(DP,cards, sum, start + 1, end);
        int lastSelected = cards[end - 1] + sum[end - 1]-sum[start]-myungwoo(DP,cards, sum, start, end - 1);

        DP[start][end] = Math.max(firstSelected, lastSelected);
        return DP[start][end];
    }

    private static int myungwoo(int[][] DP, int[] cards, int[] sum, int start, int end) {
        if (start == end) {
            return 0;
        }
        if (DP[start][end] != 0) {
            return DP[start][end];
        }

        int firstSelected = cards[start] + sum[end]-sum[start + 1]-geunwoo(DP, cards, sum, start + 1, end);
        int lastSelected = cards[end - 1] + sum[end - 1]-sum[start]-geunwoo(DP, cards, sum, start, end - 1);

        DP[start][end] = Math.max(firstSelected, lastSelected);
        return DP[start][end];
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}