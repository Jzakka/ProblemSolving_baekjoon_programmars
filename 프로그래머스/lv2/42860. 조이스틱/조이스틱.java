import java.util.*;

class Solution {
    public int solution(String name) {
        int convert = name.chars().reduce(0, (sum, character) -> sum += character - 'A' < 13 ? character - 'A' : 26 - (character - 'A'));
        Character[] charArr = name.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        return move(0, charArr, 0) + convert;
    }

    private int move(int cur, Character[] chars, int cnt) {
        if (!Arrays.stream(chars).anyMatch(c -> c != 'A')) {
            return cnt;
        }

        int lDelta = 1;
        while (lDelta < chars.length
                && chars[(cur + lDelta + chars.length) % chars.length] == 'A') {
            lDelta++;
        }
        int rDelta = lDelta;
        int right = (cur + rDelta + chars.length) % chars.length;
        lDelta = 1;
        while (lDelta < chars.length
                && chars[(cur - lDelta + chars.length) % chars.length] == 'A') {
            lDelta++;
        }
        int left = (cur - lDelta + chars.length) % chars.length;

        chars[cur] = 'A';
        int res = Math.min(move(left, chars, lDelta< chars.length? cnt+lDelta:cnt), move(right, chars,rDelta < chars.length?cnt+ rDelta:cnt));
        chars[cur] = 'B';
        return res;
    }
}