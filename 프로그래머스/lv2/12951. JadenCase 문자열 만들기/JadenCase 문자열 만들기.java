import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(String s) {
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isAlphabetic(charArray[i]) && (i == 0 || Character.isSpaceChar(charArray[i-1]))) {
                charArray[i] = (char)(charArray[i] - ('a'-'A'));
            }
        }
        return String.valueOf(charArray);
    }
}