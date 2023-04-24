import java.util.stream.IntStream;
class Solution {
    public int solution(String t, String p) {
        int strLen = p.length();

        return (int)IntStream
                .range(0, t.length() - strLen + 1)
                .mapToObj(s -> t.substring(s, s + strLen))
                .filter(extracted -> extracted.compareTo(p) <= 0)
                .count();
    }
} 