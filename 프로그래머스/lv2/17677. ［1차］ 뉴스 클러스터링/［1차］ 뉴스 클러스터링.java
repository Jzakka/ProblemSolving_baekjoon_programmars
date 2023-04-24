import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Character.*;

class Solution {
    public int solution(String str1, String str2) {
        str1 = extract(str1);
        str2 = extract(str2);

        String[] set1 = split(str1);
        String[] set2 = split(str2);

        if (set1.length == 0 && set2.length == 0) {
            return 65536;
        }

        int commonSize = getCommonSize(set1, set2);
        int unionSize = set1.length + set2.length - commonSize;

        return (int) ((double) commonSize / unionSize * 65536);
    }

    private int getCommonSize(String[] set1, String[] set2) {
        LinkedList<String> operandSet = new LinkedList<>(Arrays.asList(set2));

        int commonSize = 0;
        for (String word : set1) {
            if (operandSet.contains(word)) {
                operandSet.remove(word);
                commonSize++;
            }
        }
        return commonSize;
    }

    private String[] split(String str) {
        ArrayList<String> set = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            set.add(str.substring(i, i + 2));
        }

        return set
                .stream()
                .filter(word -> isAlphabetic(word.charAt(0)) && isAlphabetic(word.charAt(1)))
                .toArray(String[]::new);
    }

    public String extract(String str) {
        return str
                .toUpperCase()
                .chars()
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
    }
}