import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        String[] serials = new String[n];

        for (int i = 0; i < n; i++) {
            serials[i] = br.readLine();
        }

        solution(serials);

        printRes();
    }

    private static void solution(String[] serials) {
        Arrays.sort(serials, (ser1, ser2)->{
            if (ser1.length() == ser2.length()) {
                int sum1 = 0;
                int sum2 = 0;

                for (int i = 0; i < ser1.length(); i++) {
                    if (Character.isDigit(ser1.charAt(i))) {
                        sum1 += ser1.charAt(i) - '0';
                    }
                }

                for (int i = 0; i < ser2.length(); i++) {
                    if (Character.isDigit(ser2.charAt(i))) {
                        sum2 += ser2.charAt(i) - '0';
                    }
                }

                if (sum1 == sum2) {
                    return ser1.compareTo(ser2);
                }
                return sum1 - sum2;
            }
            return ser1.length() - ser2.length();
        });

        Arrays.stream(serials)
                .forEach(serial -> res.append(serial).append("\n"));

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