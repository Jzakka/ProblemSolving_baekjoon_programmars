import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        return Arrays.stream(numbers).map(Solution::f).toArray();
    }

    public static long f(long x) {
        if (x % 2 == 0) {
            return x + 1;
        }
        int i = 0;
        byte digit;
        while ((digit = (byte) (x % (1L << i + 1) / (1L << i))) == 1) {
            i++;
        }
        return x + (1L << i) - (1L << (i - 1));
    }
}