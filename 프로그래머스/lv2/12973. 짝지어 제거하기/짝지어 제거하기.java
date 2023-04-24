import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = -1;
        int strLen = s.length();
        Stack<Character> stk = new Stack<>();

        for (int i = 0; i < strLen; i++) {
            Character letter = s.charAt(i);
            if (stk.empty() || stk.peek() != letter) {
                stk.push(letter);
            } else {
                stk.pop();
            }
        }

        return stk.empty() ? 1 : 0;
    }
}