import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        final int MIN_VAL = Arrays
                .stream(arr)
                .reduce(Integer.MAX_VALUE, (minVal, num) -> minVal = Math.min(minVal, num));
        int[] result = Arrays.stream(arr).filter(num -> num != MIN_VAL).toArray();
        return result.length == 0 ? new int[]{-1} : result;
    }
}