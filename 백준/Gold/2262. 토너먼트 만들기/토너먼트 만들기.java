import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int ans=0;
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] ranks = getInts();

        solution(n, ranks);

        printRes();
    }

    private static void solution(int n, int[] ranks) {
        getMinDiffSum(0, n, ranks);

        res.append(ans);
    }

    /*
    s:start inclusive
    e:end exclusive
     */
    private static int getMinDiffSum(int s, int e, int[] ranks) {
        if (e <= s) {
            return -1;
        } else if (e - s == 1) {
            return ranks[s];
        }

        int minIdx = s;
        for (int i = s; i < e; i++) {
            if (ranks[i] < ranks[minIdx]) {
                minIdx = i;
            }
        }

        int left = getMinDiffSum(s, minIdx, ranks);
        if (left == -1) {
            left = ranks[minIdx];
        }
        int right = getMinDiffSum(minIdx + 1, e, ranks);
        if (right == -1) {
            right = ranks[minIdx];
        }

        ans += left - ranks[minIdx] + right - ranks[minIdx];

        return ranks[minIdx];
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