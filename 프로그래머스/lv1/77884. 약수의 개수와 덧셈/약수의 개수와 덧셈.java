import java.util.stream.*;

class Solution {
    public int solution(int left, int right) {
        return IntStream
                .range(left, right + 1)
                .reduce(0, (sum, i) -> sum + ((Math.floor(Math.sqrt(i)) != Math.sqrt(i)) ? i : -i));
    }
}