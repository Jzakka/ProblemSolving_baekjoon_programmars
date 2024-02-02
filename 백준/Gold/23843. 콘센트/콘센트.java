import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int n = info[0];
        int m = info[1];

        long[] t = getLongs();

        solution(t, m);

        printRes();
    }

    private static void solution(long[] t, int m) {
        Arrays.sort(t);
        Deque<Long> stk = new ArrayDeque<>();
        for (long ti : t) {
            stk.offerLast(ti);
        }

        long ans = 0;
        while (!stk.isEmpty()) {
            Long quota = stk.peekLast();
            ans += quota;

            for (int i = 0; i < m; i++) {
                long charge = 0;
                while (!stk.isEmpty() && charge < quota) {
                    charge += stk.pollLast();
                }
            }
        }
        res.append(ans);
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
/*
4
3
1
1
1
 */