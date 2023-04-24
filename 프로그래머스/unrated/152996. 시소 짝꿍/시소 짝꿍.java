import java.util.Arrays;

class Solution {
    long[] weightCount = new long[10_000];

    public long solution(int[] weights) {
        Arrays.stream(weights).forEach(w -> weightCount[w]++);

        long res = 0;

        for (int weight = 100; weight <= 1000; weight++) {
            long count = weightCount[weight];

            if (count >= 1) {
                if (weight % 3 == 0) {
                    res += weightCount[weight / 3 * 4] * count;
                }
                if (weight % 2 == 0) {
                    res += weightCount[weight / 2 * 3] * count;
                }
                if (count > 1) {
                    res += count * (count - 1) / 2;
                }
                if (weightCount[weight * 2] > 0) {
                    res += weightCount[weight * 2] * count;
                }
            }
        }

        return res;
    }
}