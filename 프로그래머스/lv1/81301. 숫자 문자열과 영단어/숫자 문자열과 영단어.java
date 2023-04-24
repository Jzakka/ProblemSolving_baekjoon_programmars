import java.util.*;

class Solution {
    public int solution(String s) {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("zero", "0");
        dictionary.put("one", "1");
        dictionary.put("two", "2");
        dictionary.put("three", "3");
        dictionary.put("four", "4");
        dictionary.put("five", "5");
        dictionary.put("six", "6");
        dictionary.put("seven", "7");
        dictionary.put("eight", "8");
        dictionary.put("nine", "9");

        StringBuilder sb = new StringBuilder(s);
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            String spell = entry.getKey();

            while (sb.indexOf(spell) != -1) {
                sb.replace(sb.indexOf(spell), sb.indexOf(spell) + spell.length(), entry.getValue());
            }
        }
        return Integer.valueOf(sb.toString());
    }
}