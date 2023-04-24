
import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int[] left = new int[10_001];
        int[] right = new int[10_001];

        int leftCnt = 0;
        Arrays.stream(topping).forEach(i -> right[i]++);
        int rightCnt = (int)Arrays.stream(right).filter(i -> i != 0).count();

        int res = 0;
        for (int num : topping) {
            if (++left[num] == 1) {
                leftCnt++;
            }
            if (--right[num] == 0) {
                rightCnt--;
            }
            if (leftCnt == rightCnt) {
                res++;
            }
        }

        return res;
    }
}