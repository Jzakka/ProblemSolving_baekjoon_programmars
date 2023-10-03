import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        br.readLine();

        int[] p = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int m = Integer.parseInt(br.readLine());

        solution(p, m);

        printRes();
    }

    private static void solution(int[] p, int m) {
        String[][] DP = new String[p.length][m + 1];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, ""));

        for (int i = p[0]; i <= m; i++) {
            DP[0][i] = repeat("0",i / p[0]);
        }

        for (int i = 1; i < p.length; i++) {
            int price = p[i];

            for (int j = price; j < DP[0].length; j++) {
                DP[i][j] = DP[i - 1][j];

                for (int k = 0; k <= i; k++) {
                    String appended = i + DP[k][j-price];
                    if (gte(DP[i][j], appended)) {
                        DP[i][j] = appended;
                    }
                }
            }
        }

        res.append(new BigInteger(
                Arrays.stream(DP).flatMap(Arrays::stream).reduce("", (max, cur) -> {
                    if (gte(max, cur)) {
                        return cur;
                    }
                    return max;
                }))
        );
    }

    private static String repeat(String digit, int count) {
        StringBuilder repeated = new StringBuilder();
        for (int i = 0; i < count; i++) {
            repeated.append(digit);
        }
        return repeated.toString();
    }

    private static boolean gte(String num1, String num2) {
        if (num1.equals("")) {
            return true;
        }
        if (num2.equals("")) {
            return false;
        }
        int result = new BigInteger(num1).compareTo(new BigInteger(num2));
        return result <= 0;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}