import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int d = info[0];
        int n = info[1];
        int m = info[2];

        int[] stones = new int[n];
        for (int i = 0; i < n; i++) {
            stones[i] = Integer.parseInt(br.readLine());
        }

        solution(d, m, stones);

        printRes();
    }

    private static void solution(int d, int m, int[] stones) {
        Arrays.sort(stones);
        int lo = 0;
        int hi = d;

        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (check(stones, m, mid)) {
                lo = mid + 1;
                ans = Math.max(ans, mid);
            } else {
                hi = mid - 1;
            }
        }

        res.append(ans);
    }

    private static boolean check(int[] stones, int m, int dist) {
        int remain = m;
        int recentStart = 0;

        for (int stone : stones) {
            if (recentStart + dist <= stone) {
                recentStart = stone;
            } else if(--remain < 0) {
                return false;
            }
        }

        return true;
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