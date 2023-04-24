import java.util.*;

class Solution {
    Map<Character, Character> parenthesis = new HashMap<>();
    public int solution(String s) {

        parenthesis.put('(', ')');
        parenthesis.put('{', '}');
        parenthesis.put('[', ']');
        parenthesis.put(')', '(');
        parenthesis.put('}', '{');
        parenthesis.put(']', '[');

        StringBuilder sb = new StringBuilder(s);

        int rotated = 0;
        while (rotated++<s.length() && !valid(sb.toString())) {
            sb = new StringBuilder(sb.substring(1)).append(sb.charAt(0));
        }
        if (rotated >= s.length()) {
            return 0;
        }
        s = sb.toString();

        Stack<Character> stk = new Stack<>();

        int outerParen = 0;
        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);

            if (bracket == ')' || bracket == '}' || bracket == ']') {
                stk.pop();
                if (stk.empty()) {
                    outerParen++;
                }
            } else {
                stk.push(bracket);
            }
        }
        return outerParen;
    }

    private boolean valid(String s) {
        Stack<Character> stk = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);

            if (bracket == ')' || bracket == '}' || bracket == ']') {
                if (!stk.empty() && stk.peek() == parenthesis.get(bracket)) {
                    stk.pop();
                } else {
                    return false;
                }
            } else {
                stk.push(bracket);
            }
        }

        return stk.empty();
    }
}