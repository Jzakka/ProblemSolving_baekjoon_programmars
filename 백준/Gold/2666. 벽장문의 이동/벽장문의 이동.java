import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] bubble = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int use = Integer.parseInt(br.readLine());
        int[] toUse = new int[use];
        for (int i = 0; i < use; i++) {
            toUse[i] = Integer.parseInt(br.readLine());
        }

        solution(n, bubble, toUse);

        printRes();
    }

    private static void solution(int n, int[] bubble, int[] toUse) {
        int[][] DP = new int[toUse.length + 1][toUse.length + 1];

        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));

        int[] use1 = new int[toUse.length + 1];
        int[] use2 = new int[toUse.length + 1];
        use1[0] = bubble[0];
        use2[0] = bubble[1];
        for (int i = 0; i < toUse.length; i++) {
            use1[i + 1] =use2[i + 1] = toUse[i];
        }

        DP[0][0] = 0;

        for (int i = 0; i < DP.length - 1; i++) {
            for (int j = 0; j < DP.length - 1; j++) {
                if (DP[i][j] != Integer.MAX_VALUE) {
                    int next = Math.max(i, j) + 1;
                    DP[next][j] = Math.min(DP[next][j], DP[i][j] + distance(use1, i, next));
                    DP[i][next] = Math.min(DP[i][next], DP[i][j] + distance(use2, j, next));
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < DP.length; i++) {
            ans = Math.min(ans, Math.min(DP[i][DP.length-1], DP[DP.length-1][i]));
        }
        res.append(ans);
    }

    private static int distance(int[] use, int from, int to) {
        return Math.abs(use[from] - use[to]);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

// 0100010