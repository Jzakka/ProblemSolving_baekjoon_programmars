import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int m = info[1];

        long[] trees = Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();

        solution(trees, m);

        printRes();
    }

    private static void solution(long[] trees, long m) {
        long hi = Arrays.stream(trees).max().getAsLong() + 1;
        long lo = -1;

        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            long total = Arrays.stream(trees)
                    .map(height -> height - mid)
                    .filter(height -> height > 0)
                    .sum();
            if (total >= m) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        res.append(lo);
    }

    /**
     * 애들 수 - 연속된 최대 증가 순열의 길이 = 최소로 애들을 움직이는 수
     */
    private static void solution(int[] children) {
        // DP[i] : i번 어린이까지 연속되게 증가하는 최대 증가수열의 길이
        int[] DP = new int[children.length + 1];
        int[] idx = new int[children.length + 1];
        idx[0] = Integer.MAX_VALUE;

        for (int i = 0; i < children.length; i++) {
            int child = children[i];
            idx[child] = i;
        }

        int maxContinuousLisLen = 0;
        for (int child : children) {
            if (idx[child] > idx[child - 1]) {
                DP[child] = DP[child - 1] + 1;
            } else {
                DP[child] = 1;
            }

            maxContinuousLisLen = Math.max(maxContinuousLisLen, DP[child]);
        }

        res.append(children.length - maxContinuousLisLen);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/**
 *
 * DP[i-1][j-1][0]  |   DP[i-1][j][0]   |   DP[i-1][j+1][0]
 * DP[i-1][j-1][1]  |   DP[i-1][j][1]   |   DP[i-1][j+1][1]
 * ============================================================
 * DP[i][j-1][0]    |   DP[i][j][0]     |   DP[i][j+1][0]
 * DP[i][j-1][1]    |   DP[i][j][1]     |   DP[i][j+1][1]
 *
 * DP[i][j][0] = DP[i][j-1][1] + 1
 * DP[i][j][1] = DP[][][]
 */