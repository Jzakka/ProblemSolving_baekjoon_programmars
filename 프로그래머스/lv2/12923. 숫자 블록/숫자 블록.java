import java.math.BigInteger;
import java.util.stream.LongStream;

class Solution {
    public int[] solution(long begin, long end) {
        return LongStream.range(begin, end + 1)
                .mapToInt(this::getMaxDivisor)
                .toArray();
    }

    public int getMaxDivisor(long num) {
//        System.out.println("num = " + num);
        if (num == 1) {
            return 0;
        }
        if (new BigInteger(String.valueOf(num)).isProbablePrime(10)) {
            return 1;
        }
        int candidate=0;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                candidate = i;
                if ((num / i) <= 10_000_000) {
                    return (int) (num / i);
                }
            }
        }
        return candidate;
    }
}