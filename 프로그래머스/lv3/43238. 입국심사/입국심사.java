import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long min = Arrays.stream(times).min().orElseThrow();
        long max = Arrays.stream(times).max().orElseThrow();

        long left = min;
        long right = max * n;
        while (left < right) {
            long mid = (left + right) / 2;
            long availables = getSum(times, mid);

            if (availables >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    private long getSum(int[] times, long mid) {
        return Arrays.stream(times).mapToLong(time -> mid / time).sum();
    }
}