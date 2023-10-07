import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 9901;

    public static void main(String[] args) throws Exception {
        int[] size = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(size[0], size[1]);

        printRes();
    }

    private static void solution(int n, int m) {
        // DP[i][bits]: i열의 상태가 bits가 되는 경우의 수
        int[][] DP = new int[m + 1][(1 << n)];
        DP[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int bits = 0; bits < DP[0].length; bits++) {
                if (DP[i][bits] > 0) {
                    combinativeBuild(DP, i, bits, 0, 0, n);
                }
            }
        }

        res.append(DP[m][0]);
    }

    private static void combinativeBuild(int[][] DP, int i, int bits, int nextBits, int shifter, final int n) {
        if (shifter == n) {
            DP[i + 1][nextBits] += DP[i][bits];
            DP[i+1][nextBits] %= MOD;
            return;
        }

        if (((bits >> shifter) & 1) == 0) { // 가로로 삽입
            combinativeBuild(DP, i, bits, nextBits | (1 << shifter), shifter + 1, n);

            if (shifter < n - 1 && ((bits >> (shifter + 1)) & 1) == 0) { // 세로로 삽입
                combinativeBuild(DP, i, bits, nextBits, shifter + 2, n);
            }
        } else { // 놓을 수 없는 위치인 경우 다음칸으로 이동
            combinativeBuild(DP, i, bits, nextBits , shifter + 1, n);
        }
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}