import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int qNum = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        switch (qNum) {
            case 1:
                solution1(n);
                break;
            case 2:
                long k = Long.parseLong(br.readLine());
                solution2(n, k);
                break;
            default:
                String letter = br.readLine();
                solution3(n, letter);
        }

        printRes();
    }

    private static void solution3(int n, String letter) {
        long[] LEN = new long[n + 1];
        long[] X = new long[n + 1];
        long[] Y = new long[n + 1];
        long[] Z = new long[n + 1];
        Map<String, long[]> menu = new HashMap<>();
        menu.put("X", X);
        menu.put("Y", Y);
        menu.put("Z", Z);

        fillTables(LEN, X, Y, Z);

        res.append(menu.get(letter)[n]);
    }

    private static void solution2(int n, long k) {
        long[] LEN = new long[n + 1];
        long[] X = new long[n + 1];
        long[] Y = new long[n + 1];
        long[] Z = new long[n + 1];

        fillTables(LEN, X, Y, Z);

        char ans = trace(LEN, n, k-1);

        res.append(ans);
    }

    private static char trace(long[] LEN, int n, long k) {
        if (n == 1) {
            return 'X';
        } else if (n == 2) {
            return (k == 0) ? 'Y' : 'Z';
        } else if (n == 3) {
            return (k == 0) ? 'Z' : 'X';
        }

        if (LEN[n - 3] > k) {
            return trace(LEN, n - 3, k);
        }

        return trace(LEN, n - 2, k - LEN[n - 3]);
    }

    private static void solution1(int n) {
        long[] LEN = new long[n + 1];
        long[] X = new long[n + 1];
        long[] Y = new long[n + 1];
        long[] Z = new long[n + 1];

        fillTables(LEN, X, Y, Z);

        res.append(LEN[n]);
    }

    private static void fillTables(long[] LEN, long[] X, long[] Y, long[] Z) {
        LEN[1] = 1;
        X[1] = 1;

        for (int i = 2; i < LEN.length; i++) {
            LEN[i] = LEN[i - 1] + X[i - 1];
            X[i] = Z[i - 1];
            Y[i] = X[i - 1];
            Z[i] = X[i - 1] + Y[i - 1];
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

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
/*
4 11
100000000 1000000000 100000000 1000000000
 */
