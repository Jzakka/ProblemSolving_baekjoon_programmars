import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static String str;

    public static void main(String[] args) throws Exception {
        br.readLine();

        int[] cards = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(cards);

        printRes();
    }

    private static void solution(int[] cards) {
        int n = cards.length;
        // DP[s][e] : [s, e) 범위에서 최소 마력소모량
        Long[][] DP = new Long[n + 1][n + 1];

        // resultCard[s][e] : [s, e) 범위만 합성을 진행할 때 결과 카드
        int[][] resultCards = new int[n + 1][n + 1];

        long ans = divideConquer(DP, resultCards, cards, 0, n);

        res.append(ans);
    }

    private static Long divideConquer(Long[][] DP, int[][] resultCards, int[] cards, int start, int end) {
        if (DP[start][end] != null) {
            return DP[start][end];
        }

        if (end == start) {
            return null;
        } else if (end - start == 1) {
            DP[start][end] = 0L;
            resultCards[start][end] = cards[start];
            return DP[start][end];
        } else if (end - start == 2) {
            DP[start][end] = (long) (cards[start] + cards[start + 1]);
            resultCards[start][end] = Math.max(cards[start], cards[start + 1]);
            return DP[start][end];
        }

        DP[start][end] = Long.MAX_VALUE;

        for (int i = start; i < end; i++) {
            Long left = divideConquer(DP, resultCards, cards, start, i);
            if (left != null) {
                Long right = divideConquer(DP, resultCards, cards, i, end);

                if (right != null) {
                    if (DP[start][end] > left + right + resultCards[start][i] + resultCards[i][end]) {
                        DP[start][end] = left + right + resultCards[start][i] + resultCards[i][end];
                        resultCards[start][end] = Math.max(resultCards[start][i], resultCards[i][end]);
                    }
                }
            }
        }

        return DP[start][end];
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
