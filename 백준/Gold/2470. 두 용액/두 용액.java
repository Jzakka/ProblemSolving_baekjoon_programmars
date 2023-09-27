import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        long[] liquids = Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong).toArray();

        solution(liquids);

        printRes();
    }

    private static void solution(long[] liquids) {
        Arrays.sort(liquids);
        long[] ans = {-1, -1};

        int r = liquids.length-1;
        int l = 0;
        long sum = 0;

        long minSum = Long.MAX_VALUE;
        while (l < r) {
            sum = liquids[l] + liquids[r];

            long absSum = Math.abs(sum);
            if (minSum > absSum) {
                minSum = absSum;
                ans[0] = liquids[l];
                ans[1] = liquids[r];
            }

            if (sum < 0) {
                l++;
            } else if (sum > 0) {
                r--;
            } else {
                break;
            }
        }

        Arrays.sort(ans);
        res.append(ans[0]).append(" ").append(ans[1]);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}