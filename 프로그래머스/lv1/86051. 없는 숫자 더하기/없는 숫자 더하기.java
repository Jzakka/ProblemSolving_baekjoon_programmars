import java.util.*;

class Solution {
    public int solution(int[] numbers) {
        return Arrays.stream(numbers).reduce(45, (sum, num) -> sum - num);
    }
}