import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int l = info[0];
        int k = info[1];
        int c = info[2];

        int[] cuttingPos = getInts();

        solution(l, k, c, cuttingPos);

        printRes();
    }

    static class CheckResult {
        int firstCut = 0;
        boolean available;

        public CheckResult(int firstCut, boolean available) {
            this.firstCut = firstCut;
            this.available = available;
        }
    }

    private static void solution(int l, int k, int c, int[] cuttingPos) {
        Arrays.sort(cuttingPos);

        int lo = 0;
        int hi = l;

        int longest = l + 1;
        int firstCut = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            CheckResult checkResult = check(l, cuttingPos, mid, c);
            if (checkResult.available) {
                hi = mid - 1;
                longest = Math.min(longest, mid);
                if (longest == mid) {
                    firstCut = checkResult.firstCut;
                }
            } else {
                lo = mid + 1;
            }
        }

        res.append(longest).append(" ").append(firstCut);
    }

    private static CheckResult check(int logLen, int[] cuttingPos, int maxLen, int cnt) {
        int firstCut;
        boolean available;

        int lastCutPoint = logLen;
        while (cnt-- > 0) {
            int boundary = lastCutPoint - maxLen;
            int lb = lowerBound(cuttingPos, boundary);
            lastCutPoint = lb;
        }
        firstCut = lastCutPoint;
        available =  lastCutPoint <= maxLen;

        return new CheckResult(firstCut, available);
    }

    private static int lowerBound(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;

        int ans = Integer.MAX_VALUE;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (arr[mid] >= target) {
                hi = mid - 1;
                ans = Math.min(ans, arr[mid]);
            } else {
                lo = mid + 1;
            }
        }
        return ans;
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
8 31
7 2
5 2
4 3
9 4
3 5
2 5
1 5
6 12

2 15
16 0
15 1

3 1
0 1
0 2
0 3
 */