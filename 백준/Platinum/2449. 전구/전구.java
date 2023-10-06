import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int NE = 0;
    private static final int SE = 1;

    public static void main(String[] args) throws Exception {
        br.readLine();

        int[] bulbs = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(bulbs);

        printRes();
    }

    private static void solution(int[] bulbs) {
        // DP[i][j] : [i, j)의 전구들을 같은 색으로 맞추는 최소 변경 횟수
        int[][] DP = new int[bulbs.length][bulbs.length + 1];

        int ans = divideAndConquer(DP, bulbs, 0, bulbs.length);

        res.append(ans);
    }

    private static int divideAndConquer(int[][] DP, int[] bulbs, int start, int end) {
        if (end - start == 1) {
            return 0;
        }

        if (DP[start][end] != 0) {
            return DP[start][end];
        }

        int ans = Integer.MAX_VALUE;
        for (int i = start + 1; i < end; i++) {
            int left = divideAndConquer(DP, bulbs, start, i);
            int right = divideAndConquer(DP, bulbs, i, end);
            ans = Math.min(ans, left + right + (bulbs[i] != bulbs[start] ? 1 : 0));
        }
        DP[start][end] = ans;

        return ans;
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