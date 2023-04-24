import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(String number, int k) {
        Stack<Character> stk = new Stack<>();

        int len = k;
        for (int i = 0; i < number.length(); i++) {
            char digit = number.charAt(i);

            while (!stk.empty() && k > 0 && stk.peek() < digit) {
                stk.pop();
                k--;
            }
            stk.push(digit);
        }

        return stk.subList(0,number.length()-len).stream().map(String::valueOf).collect(Collectors.joining());
    }
}
