import java.util.*;

public class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Byte> opened = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                opened.add((byte) 0);
            } else {
                if (opened.empty()) {
                    return false;
                } else {
                    opened.pop();
                } 
            }
        }

        return opened.empty();
    }
}
