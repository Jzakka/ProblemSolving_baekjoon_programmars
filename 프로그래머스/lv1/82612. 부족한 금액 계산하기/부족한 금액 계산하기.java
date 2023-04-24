import java.util.stream.*;

class Solution {
    public long solution(int price, int money, int count) {
        return Math.max(LongStream.range(1, count + 1).sum() * price - money, 0);
    }
}