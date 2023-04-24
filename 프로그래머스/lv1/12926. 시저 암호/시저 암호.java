import java.util.stream.*;

class Solution {
    public String solution(String s, int n) {
        return s.chars().mapToObj(i -> {
            if (Character.isUpperCase((char) i)) {
                i += n;
                if (i > 'Z') {
                    i = (i - 'Z' - 1) + 'A';
                }
            } else if (Character.isLowerCase((char) i)) {
                i += n;
                if (i > 'z') {
                    i = (i - 'z' - 1) + 'a';
                }
            }
            return String.valueOf((char) i);
        }).collect(Collectors.joining());
    }
}