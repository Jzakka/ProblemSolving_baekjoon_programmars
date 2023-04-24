import java.util.stream.Collectors;
import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int m = Arrays.stream(nums).boxed().collect(Collectors.toSet()).size();
        return m >= nums.length / 2 ? nums.length / 2 : m;
    }
}