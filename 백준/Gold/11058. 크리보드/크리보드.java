import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        BigInteger[] DP = new BigInteger[n + 1];
        IntStream.rangeClosed(0, n).forEach(i->DP[i] = new BigInteger("0"));

        for (int i = 0; i < DP.length; i++) {
            if (i + 1 < DP.length) {
                DP[i + 1] = DP[i + 1].max(DP[i].add(new BigInteger("1")));
            }

            BigInteger clipBoard = DP[i];
            int idx = i + 3;
            BigInteger screen = DP[i];
            while (idx< DP.length) {
                screen  = screen.add(clipBoard);
                DP[idx] = DP[idx].max(screen);
                idx++;
            }
        }

        res.append(DP[n]);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}