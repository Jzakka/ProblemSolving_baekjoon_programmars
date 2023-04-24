import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        return NBE(numbers);
    }

    private int[] NBE(int[] numbers) {
        int[] nbe = new int[numbers.length];
        Stack<Integer> stk = new Stack<>();

        for (int i = numbers.length - 1; i >= 0; i--) {
            while (!stk.empty() && stk.peek() <= numbers[i]) {
                stk.pop();
            }
            if (stk.empty()) {
                nbe[i] = -1;
            } else {
                nbe[i] = stk.peek();
            }
            stk.push(numbers[i]);
        }
        return nbe;
    }
}