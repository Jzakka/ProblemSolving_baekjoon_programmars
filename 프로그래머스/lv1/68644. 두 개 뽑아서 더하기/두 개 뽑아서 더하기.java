import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> twoSums = new TreeSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                twoSums.add(numbers[i] + numbers[j]);
            }
        }
        return twoSums.stream().mapToInt(Integer::intValue).toArray();
    }
}