import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        long[] nums = new long[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        BigInteger ans = solution(nums);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static BigInteger solution(long[] nums) {
        List<Long> groupedList = new ArrayList<>();
        int i=0;
        while (i < nums.length) {
            groupedList.add(nums[i]);
            int j = i;
            while (j < nums.length && nums[i] == nums[j]) {
                j++;
            }
            i = j;
        }
        long[] grouped = groupedList.stream().mapToLong(Long::longValue).toArray();

        BigInteger[] ans = {new BigInteger("0")};

        divideAndConquer(grouped,ans,0,grouped.length);

        return ans[0];
    }

    private static long divideAndConquer(long[] grouped, BigInteger[] ans, int s, int e) {
        if (e - s <= 1) {
            return grouped[s];
        } else if (e - s == 2) {
            return merge(ans, grouped[s], grouped[s + 1]);
        }

        int maxIdx = s;
        for (int i = s; i < e; i++) {
            if (grouped[i] > grouped[maxIdx]) {
                maxIdx = i;
            }
        }

        if (maxIdx == s) {
            long right = divideAndConquer(grouped, ans, s + 1, e);
            return merge(ans, grouped[maxIdx], right);
        } else if (maxIdx == e - 1) {
            long left = divideAndConquer(grouped, ans, s, e-1);
            return merge(ans, left, grouped[maxIdx]);
        }

        long left = divideAndConquer(grouped, ans, s, maxIdx);
        long right = divideAndConquer(grouped, ans, maxIdx+1, e);

        long leftMerged = merge(ans, left, grouped[maxIdx]);
        return merge(ans, leftMerged, right);
    }

    private static long merge(BigInteger[] ans, long left, long right) {
        long diff = Math.abs(right - left);
        ans[0] = ans[0].add(new BigInteger(String.valueOf(diff)));
        return Math.max(right, left);
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}

/*
7
1
3
2
1
3
1
2
 */