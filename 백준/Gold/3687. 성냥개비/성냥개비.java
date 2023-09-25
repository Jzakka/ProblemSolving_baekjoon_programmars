import java.io.*;
import java.math.BigInteger;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        // DP[i] : i개의 성냥개비로 만들 수 있는 가장 작은 수
        BigInteger[] DP = new BigInteger[101];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = new BigInteger(String.valueOf(Long.MAX_VALUE));
        }

        constructDP(DP);

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            solution(n, DP);
        }

        printRes();
    }

    private static void constructDP(BigInteger[] DP) {
        int[] num = {
                -1, -1,
                1,
                7,
                4,
                2,
                0,
                8
        };
        DP[2] = new BigInteger("1");
        DP[3] = new BigInteger("7");
        DP[4] = new BigInteger("4");
        DP[5] = new BigInteger("2");
        DP[6] = new BigInteger("6");
        DP[7] = new BigInteger("8");

        for (int i = 8; i < DP.length; i++) {
            // 새로운 자릿수에 j개의 성냥을 이용해 만들 수 있는 가장 작은 수
            for (int j = 2; j <= 7; j++) {
                if (DP[i - j].longValue() != Long.MAX_VALUE) {
                    BigInteger addedNum = DP[i - j].multiply(new BigInteger("10")).add(new BigInteger(String.valueOf(num[j])));
                    if (addedNum.compareTo(DP[i]) < 0) {
                        DP[i] = addedNum;
                    }
                }
            }
        }
        // DP[8] -> DP[6]*10 + 1, DP[5]*10 + 7, DP[4]*10 + 4, DP[3]*10 + 2, DP[2]*10 + 0, DP[1]*10 + 8
        //       -> 61            27
    }

    private static void solution(int n, BigInteger[] DP) {
        BigInteger bigNum;
        // 가장 큰 수
        if ((n & 1) == 1) {
            StringBuilder str = new StringBuilder("7");
            for (int i = 0; i < (n - 3) / 2; i++) {
                str.append("1");
            }
            bigNum = new BigInteger(str.toString());
        } else {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < n / 2; i++) {
                str.append("1");
            }
            bigNum = new BigInteger(str.toString());
        }

        res.append(DP[n]).append(" ").append(bigNum).append("\n");
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}