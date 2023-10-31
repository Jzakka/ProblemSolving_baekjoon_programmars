import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] jump = getInts();
        solution(jump);

        printRes();
    }

    private static void solution(int[] jump) {
        int n = jump.length;
        int[] DP = new int[jump.length];

        for (int i = n - 1; i >= 0; i--) {
            if (i + jump[i] + 1 >= n) {
                DP[i] = 1;
            } else {
                DP[i] = DP[i + jump[i] + 1] + 1;
            }
        }

        Arrays.stream(DP).forEach(i -> res.append(i).append(" "));

    }

    /*
    DP[i] = DP[i + jump[i] + 1] + jump[i] == 0 ? 1 : 0
     */

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