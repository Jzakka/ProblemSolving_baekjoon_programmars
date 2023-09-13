import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }
    private static void solution(int n) {
        // DP[i][j][k] : 길이 i이고 마지막 숫자가 j이며 k상태에 쓰인 숫자들을 사용한 계단 수의 개수
        long[][][] DP = new long[n + 1][10][1024];

        for (int i = 1; i <= 9; i++) {
            DP[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < 1024; k++) { // k 는 길이 i-1의 상태비트
                    int state = k | (1 << j);   // state는 길이 i의 상태비트
                    if (j == 0) {
                        DP[i][j][state] += DP[i - 1][1][k];
                    } else if (j == 9) {
                        DP[i][j][state] += DP[i - 1][8][k];
                    } else {
                        DP[i][j][state] += (DP[i - 1][j - 1][k] + DP[i - 1][j + 1][k])%MOD;
                    }
                    DP[i][j][state] %= MOD;
                }
            }
        }

        long count = 0;
        for (int i = 0; i <= 9; i++) {
            count += DP[n][i][1023];
            count %= MOD;
        }

//        int hint = 0;
//        for (int i = 1; i <= 40; i++) {
//            for (int j = 0; j < 10; j++) {
//                hint += DP[i][j][1023];
//            }
//        }

        res.append(count);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}