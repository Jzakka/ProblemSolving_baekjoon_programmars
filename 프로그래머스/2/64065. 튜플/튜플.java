import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    int[] counts = new int[100_001];
    public int[] solution(String s) {
        getCounts(s);

        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 100_000; i++) {
            if (counts[i] > 0) {
                ans.add(i);
            }
        }

        Collections.sort(ans, (a,b) -> counts[b] - counts[a]);


        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void getCounts(String s) {
        int cursor = 0;

        int number = 0;
        while (cursor < s.length()) {
            if (Character.isDigit(s.charAt(cursor))) {
                number = number * 10 + (s.charAt(cursor) - '0');
                if (cursor + 1 == s.length() || !Character.isDigit(s.charAt(cursor + 1))) {
                    counts[number]++;
                    number = 0;
                }
            }

            cursor++;
        }
    }
}