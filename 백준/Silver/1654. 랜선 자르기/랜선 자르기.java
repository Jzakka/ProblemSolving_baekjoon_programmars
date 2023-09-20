import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        int[] kn = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        long[] LAN = new long[kn[0]];

        for (int i = 0; i < kn[0]; i++) {
            LAN[i] = Long.parseLong(br.readLine());
        }

        solution(LAN, kn[1]);

        printRes();
    }

    private static void solution(long[] LAN, int n) {
        long right =  Arrays.stream(LAN).max().getAsLong() + 1;
        long left = 1;

        while (left < right) {
            long mid = (right + left) / 2;
            final long lanLen = mid;
            long lanCount = Arrays.stream(LAN).map(lan -> lan / lanLen).sum();

            if (lanCount >= n) {
                left = lanLen + 1;
            } else {
                right = lanLen;
            }
        }

        res.append(left - 1);
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