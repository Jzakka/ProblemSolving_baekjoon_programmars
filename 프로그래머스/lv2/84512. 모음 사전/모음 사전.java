import java.util.*;

class Solution {
    public int solution(String word) {
        Map<Character, Integer> mul = new HashMap<>();
        mul.put('A', 0);
        mul.put('E', 1);
        mul.put('I', 2);
        mul.put('O', 3);
        mul.put('U', 4);
        int[] constMul = {781, 156, 31, 6, 1};

        int cnt = 0;
        for (int i = 0; i < word.length(); i++) {
            cnt++;
            cnt += mul.get(word.charAt(i)) * constMul[i];
        }
        return cnt;
    }
}