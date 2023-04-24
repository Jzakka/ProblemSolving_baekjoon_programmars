import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int[] rec = Arrays.stream(sizes).reduce(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}, (pair, size) -> {
            pair[0] = Math.max(pair[0], Math.max(size[0], size[1]));
            pair[1] = Math.max(pair[1], Math.min(size[0], size[1]));
            return pair;
        });
        return rec[0] * rec[1];
    }
}