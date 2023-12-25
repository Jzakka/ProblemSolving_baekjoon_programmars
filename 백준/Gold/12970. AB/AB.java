import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    public static void main(String[] args) throws Exception {
        int n, k;
        int[] info = getInts();
        n = info[0];
        k = info[1];

        solution(n, k);

        printRes();
    }

    private static void solution(int n, int k) {
        int B = (int) Math.sqrt(k);

        if (n < 2 * B) {
            res.append(-1);
            return;
        }

        int[] A = new int[B + 1];
        A[B] = B;

        int remain = k - B * B;
        for (int i = B; i > 0; i--) {
            A[i] += remain / i;
            remain %= i;
        }

        int totalA = Arrays.stream(A).sum();
        if (totalA + B > n) {
            res.append(-1);
            return;
        }

        for (int i = 0; i < n - (totalA + B); i++) {
            res.append('B');
        }
        for (int i = B; i > 0; i--) {
            for (int j = 0; j < A[i]; j++) {
                res.append('A');
            }
            res.append('B');
        }
    }

    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static long[] getLongs() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}