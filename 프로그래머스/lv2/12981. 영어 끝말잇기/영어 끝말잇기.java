import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] loser = new int[2];
        char suffix = words[0].charAt(0);
        Set<String> appeared = new HashSet<>();

        for (int i = 0, round = 1; i < words.length; i += n, round++) {
            for (int j = i; j < words.length && j < i + n; j++) {
                String word = words[j];
                if (suffix != word.charAt(0) || appeared.contains(word)) {
                    loser[1] = round;
                    loser[0] = j % n + 1;
                    return loser;
                }
                suffix = word.charAt(word.length() - 1);
                appeared.add(word);
            }
        }

        return loser;
    }
}
