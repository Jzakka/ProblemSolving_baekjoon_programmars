import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(String p) {
        if (p.isEmpty()) {
            return "";
        }
        int idx = divide(p);
        String u = p.substring(0, idx);
        String v = "";
        if (idx < p.length()) {
             v = p.substring(idx);
        }

        if (correct(u)) {
            return u + solution(v);
        }

        String temp = '(' + solution(v) + ')';
        return temp + u.substring(1, u.length() - 1).chars().mapToObj(d -> {
            if (d == '(') {
                d = ')';
            } else {
                d = '(';
            }
            return String.valueOf((char) d);
        }).collect(Collectors.joining());

    }

    private boolean correct(String u) {
        Stack<Byte> stk = new Stack<>();

        for (int i = 0; i < u.length(); i++) {
            char c = u.charAt(i);
            if (c == '(') {
                stk.push((byte) 1);
            } else {
                if (stk.empty()) {
                    return false;
                }
                stk.pop();
            }
        }
        return stk.empty();
    }

    private int divide(String p) {
        int sum = 0;

        int idx = 0;
        do {
            if (p.charAt(idx) == '(') {
                sum--;
            } else {
                sum++;
            }
            idx++;
        } while (idx < p.length() && sum != 0);

        return idx;
    }
}