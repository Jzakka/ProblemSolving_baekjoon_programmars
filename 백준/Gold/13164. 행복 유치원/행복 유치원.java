import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] size = getInts();
        int k = size[1];
        int[] heights = getInts();

        solution(heights, k);

        printRes();
    }

    private static void solution(int[] heights, int k) {
        int n = heights.length;
        long[] diffs = new long[n - 1];
        for (int i = 0; i < n-1; i++) {
            diffs[i] = heights[i] - heights[i + 1];
        }

        Arrays.sort(diffs);

        long ans = Arrays.stream(diffs, 0, k - 1)
                .sum() + heights[n - 1] - heights[0];

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

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}