import java.util.*;
class Solution {
    public int solution(int[] d, int budget) {
        return Arrays
                .stream(d)
                .sorted()
                .mapToObj(i -> new int[]{i,0})
                .reduce(new int[]{budget,0}, (acc, cur) -> {
            if (acc[0] >= cur[0]) {
                acc[0] -= cur[0];
                acc[1]++;
            }
            return acc;
        })[1];
    }
}