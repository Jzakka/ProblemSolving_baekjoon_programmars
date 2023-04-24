import java.util.stream.*;

class Solution {
    public long solution(int a, int b) {
        return LongStream.range(Math.min(a, b), Math.max(a+1, b+1)).reduce((sum, num) -> sum + num).getAsLong();
    }
}