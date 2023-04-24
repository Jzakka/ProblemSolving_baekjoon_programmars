import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        int lastNum = -1;
        ArrayList<Integer> nums = new ArrayList<>();
        for (int num : arr) {
            if (lastNum != num) {
                nums.add(num);
                lastNum = num;
            }
        }
        return nums.stream().mapToInt(Integer::intValue).toArray();
    }
}