import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        Set<String> words = new HashSet<>();
        words.add("aya");
        words.add("ye");
        words.add("woo");
        words.add("ma");

        int result = 0;
        for (String comb : babbling) {
            if(isSequenceOfWords("", comb, words, 0)){
                result++;
            }
        }
        return result;
    }

    boolean isSequenceOfWords(String prevPron, String combination, Set<String> words, int pos){
        if (pos >= combination.length()) {
            return true;
        }
        String sub = extract(combination, pos, 2);
        if(words.contains(sub) && !sub.equals(prevPron)){
            return isSequenceOfWords(sub, combination, words, pos + 2);
        }
        sub = extract(combination, pos, 3);
        if (words.contains(sub) && !sub.equals(prevPron)) {
            return isSequenceOfWords(sub, combination, words, pos + 3);
        }
        return false;
    }

    String extract(String string, int start, int len){
        if (start + len > string.length()) {
            len = string.length() - start;
        }
        return string.substring(start, start + len);
    }
}