import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        int[] inputs = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(inputs[0], inputs[1], inputs[2]);

        printRes();
    }

    private static void solution(int n, int L, int R) {
        // DP[i][l][r] : 가장높은 빌딩이 i일 떄, 왼쪽에서 l개가 보이고 오른쪽에서 r개가 보이는 가짓수
        long[][][] DP = new long[n + 1][n + 1][n + 1];

        DP[1][1][1] = 1;

        /**
         * i번째 빌딩을 세로 세우려하지 말고,
         * 기존 빌딩들이 높아진 후에 길이 1짜리 빌딩을 집어넣는 상황을 상상해라
         */
        for (int i = 1; i < DP.length - 1; i++) {
            for (int l = 1; l <= i; l++) {
                for (int r = 1; r <= i; r++) {
                    DP[i + 1][l + 1][r] = (DP[i + 1][l + 1][r] + DP[i][l][r])%MOD;
                    DP[i + 1][l][r + 1] = (DP[i + 1][l][r + 1] + DP[i][l][r])%MOD;
                    DP[i + 1][l][r] = (DP[i + 1][l][r] + (i - 1) * DP[i][l][r] % MOD) % MOD;
                }
            }
        }

        res.append(DP[n][L][R]);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}