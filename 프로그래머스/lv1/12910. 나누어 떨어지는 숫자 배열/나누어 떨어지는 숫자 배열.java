import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] res = Arrays
                .stream(arr)
                .filter(num -> num % divisor == 0)
                .sorted().toArray();
        return res.length == 0 ? new int[]{-1} : res;
    }
}