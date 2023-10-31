import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_009;

    public static void main(String[] args) throws Exception {
        br.readLine();

        int[] seq = getInts();

        solution(seq);
        printRes();
    }

    private static void solution(int[] seq) {
        List<Integer> lis = new ArrayList<>();

        for (int num : seq) {
            if (!lis.isEmpty() && lis.get(lis.size() - 1) >= num) {
                int pos = upperBound(lis, num);
                lis.set(pos, num);
            } else {
                lis.add(num);
            }
        }

        res.append(lis.size());
    }

    private static int upperBound(List<Integer> lis, int num) {
        int lo = 0;
        int hi = lis.size() - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (lis.get(mid) < num) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
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