import java.util.*;

class Solution {
    String sentence;
    int[] lastIdxOf = new int[26];
    boolean[] isUsed = new boolean[26];
    Stack<Character> usedLetterHistory = new Stack<>();

    boolean nestedRuleFlag = false;

    public String solution(String sentence) {
        if (!init(sentence)) {
            return "invalid";
        }

        return recursive(0, new ArrayList<>(), 0);
    }

    String recursive(int idx, List<String> words, int leftBoundary) {
        if (idx >= sentence.length()) {
            return String.join(" ", words);
        }

        // rule3 만들기
        StringBuilder rule3 = new StringBuilder();
        while (idx < sentence.length() && isUppercase(sentence.charAt(idx))) {
            rule3.append(sentence.charAt(idx));
            idx++;
        }

        if (idx == sentence.length()) {
            words.add(rule3.toString());
            return recursive(idx, words, leftBoundary);
        }

        char lowerCase = sentence.charAt(idx);

        // 소문자가 아니거나 이미 쓰였던 소문자가 또 쓰이면 invalid
        if (!isLowercase(lowerCase) || isUsed(lowerCase)) {
            return "invalid";
        }
        int lastIndex = lastIndexOf(lowerCase);

        // rule1인 단어
        String rule1 = rule1(idx, lastIndex, leftBoundary, false);

        String res = "invalid";
        // rule1인 단어가 invalid가 아니면 idx를 진행시킴
        if (!rule1.equals("invalid")) {
            boolean rule3Added = false;
            if (rule3.length() > 0) {
                String substring = rule3.substring(0, rule3.length() - 1);
                if (substring.length() > 0) {
                    rule3Added = true;
                    words.add(substring);
                }
            }

            words.add(rule1);
            res = recursive(lastIndex + 2, words, lastIndex + 2);
            words.remove(words.size() - 1);
            if (rule3Added) {
                words.remove(words.size() - 1);
            }
        }

        useReset(usedLetterHistory.pop()); // 재귀적으로 트리구조로 찾기 때문에 상태를 복구해야함

        if (!res.equals("invalid")) {
            return res;
        }

        // rule2인 단어
        String rule2 = rule2(idx, lastIndex);
        if (!rule2.equals("invalid")) {
            boolean rule3Added = false;
            if (rule3.length() > 0) {
                rule3Added = true;
                words.add(rule3.toString());
            }
            words.add(rule2);
            res = recursive(lastIndex + 1, words, lastIndex + 1);
            words.remove(words.size() - 1);
            if (rule3Added) {
                words.remove(words.size() - 1);
            }
        }

        useReset(usedLetterHistory.pop());
        if (nestedRuleFlag) {
            useReset(usedLetterHistory.pop());
            nestedRuleFlag = false;
        }

        return res;
    }

    public boolean init(String sentence) {
        this.sentence = sentence;
        for (int i = 0; i < sentence.length(); i++) {
            char letter = sentence.charAt(i);
            if (isLowercase(letter)) {
                lastIdxOf[letter - 'a'] = i;
            } else if (!isUppercase(letter)) {
                return false;
            }
        }
        return true;
    }

    String rule1(int s, int e, int leftBoundary, boolean nested) {
        char usedLowercase = sentence.charAt(s);

        // 소문자가 아니거나 이미 쓰였던 소문자가 또 쓰이면 invalid
        if (!isLowercase(usedLowercase) || isUsed(usedLowercase)) {
            return "invalid";
        }

        if (nested) {
            nestedRuleFlag = true;
        }
        use(usedLowercase);
        if ((((e - s) & 1) == 1) || s - 1 < leftBoundary || e + 1 == sentence.length()) {
            return "invalid";
        }

        int idx = 0;
        StringBuilder word = new StringBuilder();
        for (int i = s - 1; i <= e + 1; i++, idx++) {
            char letter = sentence.charAt(i);
            if ((idx & 1) == 0) { // 짝수번 문자, 모두 대문자여야함
                if (isUppercase(letter)) {
                    word.append(letter);
                } else {
                    return "invalid";
                }
            } else if (letter != usedLowercase) { // 홀수번 문자
                return "invalid";
            }
        }
        return word.toString();
    }

    String rule2(int s, int e) {
        char usedLowercase = sentence.charAt(s);

        // 이미 쓰였던 소문자가 또 쓰이면 invalid
        if (!isLowercase(usedLowercase) || isUsed(usedLowercase)) {
            return "invalid";
        }
        use(usedLowercase);

        if (s == e) {
            return "invalid";
        }

        // s부터 e까지 모두 대문자일까
        StringBuilder sb = new StringBuilder();
        for (int i = s + 1; i < e; i++) {
            if (isUppercase(sentence.charAt(i))) {
                sb.append(sentence.charAt(i));
            } else {
                // 모두 대문자가 아니라면 내부에서 규칙1을 만족할까?
                if (e - s >= 4) {
                    String extracted = rule1(s + 2, e - 2, s + 1, true);
                    return extracted;
                }
                return "invalid";
            }
        }

        return sb.length() == 0 ? "invalid" : sb.toString();
    }

    boolean isUppercase(char letter) {
        return 'A' <= letter && letter <= 'Z';
    }

    boolean isLowercase(char letter) {
        return 'a' <= letter && letter <= 'z';
    }

    int lastIndexOf(char letter) {
        return lastIdxOf[letter - 'a'];
    }

    boolean isUsed(char letter) {
        return isUsed[letter - 'a'];
    }

    void use(char letter) {
        usedLetterHistory.push(letter);
        isUsed[letter - 'a'] = true;
    }

    void useReset(char letter) {
        isUsed[letter - 'a'] = false;
    }
}